package dev.stardustoog.repository;

import dev.stardustoog.domain.Customer;
import dev.stardustoog.domain.Delivery;
import dev.stardustoog.exceptions.CustomerNotFoundException;
import dev.stardustoog.exceptions.DelivererNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {

    @PersistenceContext
    private EntityManager em;

    public void serveCustomer(long delivererId, long customerId) {
        Customer customer = em.find(Customer.class, customerId);
        Delivery delivery = em.find(Delivery.class, delivererId);
        if(customer == null || customer.isDelivered()) {
            throw new CustomerNotFoundException();
        }
        if(delivery == null || delivery.getCustomer() != null) {
            throw new DelivererNotFoundException();
        }
        delivery.setCustomer(customer);
        customer.setOrderTook(true);
        em.merge(customer);
        em.merge(delivery);
    }

    public void setDelivered(long delivererId) {
        Delivery delivery = em.find(Delivery.class, delivererId);
        if(delivery == null || delivery.getCustomer() == null) {
            throw new DelivererNotFoundException();
        }
        Customer c = delivery.getCustomer();
        c.setDelivered(true);
        em.merge(c);
        delivery.setCustomer(null);
        em.merge(delivery);
    }

    public List<Customer> seeActiveCustomers() {
        return em.createQuery("select c from Customer c where c.delivered = false AND c.orderTook = false")
                .getResultList();
    }

}
