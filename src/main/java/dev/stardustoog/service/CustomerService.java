package dev.stardustoog.service;

import dev.stardustoog.domain.Customer;
import dev.stardustoog.domain.Food;
import dev.stardustoog.domain.FoodCenter;
import dev.stardustoog.domain.Order;
import dev.stardustoog.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    @Autowired
    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<FoodCenter> getAvailableFoodCenters() {
        return repo.getAvailableFoodCenters();
    }

    public List<Food> getFoodCenterMenu(long foodCenterId){
        return repo.getFoodCenterMenu(foodCenterId).orElse(Collections.emptyList());
    }

    public List<Order> getCustomerOrders(long customerId) {
        return repo.getCustomerOrders(customerId).orElse(Collections.emptyList());
    }

    public void cancelOrder(long customerId, long orderId) {
        repo.cancelOrder(customerId, orderId);
    }

    public void removeCustomer(long customerrId) {
        repo.removeCustomer(customerrId);
    }

    public void updateOrderComment(long orderId, String comment) {
        repo.updateOrderComment(orderId, comment);
    }

    public void addCustomer(Customer customer) {
        repo.addCustomer(customer);
    }

    public void addOrderToCustomer(long id, Order order) {
        repo.addOrderToCustomer(id, order);
    }
}
