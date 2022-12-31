package dev.stardustoog.service;

import dev.stardustoog.domain.Delivery;
import dev.stardustoog.domain.Food;
import dev.stardustoog.domain.FoodCenter;
import dev.stardustoog.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void addFoodCenter(FoodCenter foodCenter) {
        adminRepository.addFoodCenter(foodCenter);
    }

    public void removeFoodCenter(long foodCenterId){
        adminRepository.removeFoodCenter(foodCenterId);
    }

    public void addFoodToFoodCenter(long foodCenterId, Food food) {
        adminRepository.addFoodToFoodCenter(foodCenterId, food);
    }

    public void addNewDeliverer(Delivery delivery) {
        adminRepository.addNewDeliverer(delivery);
    }

    public void removeDeliverer(long delivererId) {
        adminRepository.removeDeliverer(delivererId);
    }

    public List<Delivery> deliveryServices() {
        return adminRepository.deliveryServices();
    }

    public void removeFoodFromFoodCenter(long foodCenterId, long foodId) {
        adminRepository.removeFoodFromFoodCenter(foodCenterId, foodId);
    }

}
