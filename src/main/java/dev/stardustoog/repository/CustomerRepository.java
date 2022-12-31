package dev.stardustoog.repository;

import dev.stardustoog.domain.Customer;
import dev.stardustoog.domain.Food;
import dev.stardustoog.domain.FoodCenter;
import dev.stardustoog.domain.Order;
import dev.stardustoog.exceptions.CustomerNotFoundException;
import dev.stardustoog.exceptions.FoodCenterNotFoundException;
import dev.stardustoog.exceptions.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Slf4j
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public List<FoodCenter> getAvailableFoodCenters() {
        log.info("Get request on available food centers are called");
        return em.createQuery("select f from FoodCenter f").getResultList();
    }

    public Optional<List<Food>> getFoodCenterMenu(long foodCenterId){
        FoodCenter foodCenter = em.find(FoodCenter.class, foodCenterId);
        if(foodCenter == null) {
            log.error("Error during getting food center menu because food center with id {} doesn't exist", foodCenterId);
            throw new FoodCenterNotFoundException();
        }
        log.debug("Getting food center menu with food center id of {}", foodCenterId);
        return Optional.of(foodCenter.getFood());
    }

    public Optional<List<Order>> getCustomerOrders(long customerId) {
        Customer customer = em.find(Customer.class, customerId);
        if(customer == null) {
            log.error("customer with id {} couldn't be found", customerId);
            throw new CustomerNotFoundException();
        }
        log.debug("Getting orders of customer with id {} ", customerId);
        return Optional.of(customer.getOrders());
    }

    public void cancelOrder(long customerId, long orderId) {
        Customer customer = em.find(Customer.class, customerId);
        if(customer == null) {
            throw new CustomerNotFoundException();
        }
        Order order = em.find(Order.class, orderId);
        if(order == null) {
            throw new OrderNotFoundException();
        }
        em.remove(order);
        customer.getOrders().remove(order);
        em.merge(customer);
    }

    public void removeCustomer(long customerId) {
        Customer customer = em.find(Customer.class, customerId);
        if(customer == null) {
            throw new CustomerNotFoundException();
        }
        em.remove(customer);
    }

    public void updateOrderComment(long orderId, String comment) {
        Order order = em.find(Order.class, orderId);
        if(order == null) {
            throw new OrderNotFoundException();
        }
        order.setComment(comment);
        em.merge(order);
    }

    public void addCustomer(Customer customer) {
        em.persist(customer);
    }

    public void addOrderToCustomer(long id, Order order) {
        Customer customer = em.find(Customer.class, id);
        if(customer == null) {
            throw new CustomerNotFoundException();
        }
        customer.getOrders().add(order);
        em.persist(order);
        em.merge(customer);
    }

}
