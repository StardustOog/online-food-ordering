package dev.stardustoog.controller;

import dev.stardustoog.domain.Customer;
import dev.stardustoog.domain.Food;
import dev.stardustoog.domain.FoodCenter;
import dev.stardustoog.domain.Order;
import dev.stardustoog.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/food-centers")
    public List<FoodCenter> getAvailableFoodCenters() {
        return service.getAvailableFoodCenters();
    }

    @GetMapping("/food-centers/{foodCenterId}/menu")
    public List<Food> getFoodCenterMenu(@PathVariable long foodCenterId){
        return service.getFoodCenterMenu(foodCenterId);
    }

    @GetMapping("/customer/order/{customerId}")
    public List<Order> getCustomerOrders(@PathVariable long customerId) {
        return service.getCustomerOrders(customerId);
    }

    @DeleteMapping("/customer/{customerId}/order/{orderId}")
    public void cancelOrder(@PathVariable long customerId, @PathVariable long orderId) {
        service.cancelOrder(customerId, orderId);
    }

    @DeleteMapping("/customer/{customerId}")
    public void removeCustomer(@PathVariable long customerId) {
        service.removeCustomer(customerId);
    }

    @PutMapping("/customer/order/{orderId}")
    public void updateOrderComment(@PathVariable long orderId, @RequestParam String comment) {
        service.updateOrderComment(orderId, comment);
    }

    @PostMapping("/customer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) {
        service.addCustomer(customer);
    }

    @PostMapping("/customer/{id}/order")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addOrderToCustomer(@PathVariable long id, @Valid @RequestBody final Order order) {
        service.addOrderToCustomer(id, order);
    }


}
