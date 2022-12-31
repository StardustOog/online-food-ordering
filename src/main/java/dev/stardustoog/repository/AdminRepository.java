package dev.stardustoog.repository;

import dev.stardustoog.domain.Delivery;
import dev.stardustoog.domain.Food;
import dev.stardustoog.domain.FoodCenter;
import dev.stardustoog.exceptions.DelivererNotFoundException;
import dev.stardustoog.exceptions.FoodCenterNotFoundException;
import dev.stardustoog.exceptions.FoodNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class AdminRepository {

    @PersistenceContext
    private EntityManager em;

    public void addFoodCenter(FoodCenter foodCenter) {
        em.persist(foodCenter);
        log.info("New food center added");
    }

    public void removeFoodCenter(long foodCenterId){
        FoodCenter foodCenter = em.find(FoodCenter.class, foodCenterId);
        if(foodCenter == null) {
            log.error("error during removing -> such food center with id {} doesn't exist", foodCenterId);
            throw new FoodCenterNotFoundException();
        }
        em.remove(foodCenter);
        log.debug("food center with {} id is removed", foodCenterId);
    }

    public void addFoodToFoodCenter(long foodCenterId, Food food) {
        FoodCenter foodCenter = em.find(FoodCenter.class, foodCenterId);
        if(foodCenter == null) {
            log.error("error during adding food to food center -> such food center with id {} doesn't exist", foodCenterId);
            throw new FoodCenterNotFoundException();
        }
        em.persist(food);
        foodCenter.getFood().add(food);
        em.merge(foodCenter);
        log.debug("food added to food center with id {}", foodCenterId);
    }

    public void addNewDeliverer(Delivery delivery) {
        em.persist(delivery);
        log.info("new deliverer added");
    }

    public void removeDeliverer(long delivererId) {
        Delivery delivery = em.find(Delivery.class, delivererId);
        if(delivery == null) {
            log.error("couldn't remove deliverer with id {} because it doesn't exist", delivererId);
            throw  new DelivererNotFoundException();
        }
        em.remove(delivery);
        log.debug("deliverer with id {} is removed", delivererId);
    }

    public List<Delivery> deliveryServices() {
        log.info("Get request on deliveryservices are being called");
        return em.createQuery("select d from Delivery d")
                .getResultList();
    }

    public void removeFoodFromFoodCenter(long foodCenterId, long foodId) {
        FoodCenter foodCenter = em.find(FoodCenter.class, foodCenterId);
        if(foodCenter == null) {
            log.error("error occured during removing food from food center because food center with id {} doesn't exist",
                    foodCenterId);
            throw new FoodCenterNotFoundException();
        }
        Food food = em.find(Food.class, foodId);
        if(food == null) {
            log.error("error occured during removing food from food center because food  with id {} doesn't exist",
                    foodId);
            throw new FoodNotFoundException();
        }
        em.remove(food);
        foodCenter.getFood().remove(food);
        em.merge(foodCenter);
        log.debug("food with id {} is removed from foodcenter with id {}", foodId, foodCenterId);
    }


}
