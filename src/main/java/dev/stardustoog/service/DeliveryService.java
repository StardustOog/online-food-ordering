package dev.stardustoog.service;

import dev.stardustoog.domain.Customer;
import dev.stardustoog.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void serveCustomer(long delivererId, long customerId) {
        deliveryRepository.serveCustomer(delivererId, customerId);
    }

    public void setDelivered(long delivererId) {
        deliveryRepository.setDelivered(delivererId);
    }

    public List<Customer> seeActiveCustomers() {
        return deliveryRepository.seeActiveCustomers();
    }

}
