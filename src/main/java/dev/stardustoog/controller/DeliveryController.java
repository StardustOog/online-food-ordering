package dev.stardustoog.controller;

import dev.stardustoog.domain.Customer;
import dev.stardustoog.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    private final DeliveryService service;

    @Autowired
    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    @PutMapping("/{delivererId}/serve-customer/{customerId}")
    public void serveCustomer(@PathVariable long delivererId, @PathVariable long customerId) {
        service.serveCustomer(delivererId, customerId);
    }

    @PutMapping("/{delivererId}/set-delivered")
    public void setDelivered(long delivererId) {
        service.setDelivered(delivererId);
    }

    @GetMapping("orders-to-take")
    public List<Customer> seeActiveCustomers() {
        return service.seeActiveCustomers();
    }

}
