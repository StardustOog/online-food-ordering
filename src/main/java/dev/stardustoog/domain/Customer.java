package dev.stardustoog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_geq")
    @EqualsAndHashCode.Include
    @Column(name = "customer_id")
    private long id;

    @Max(30)
    @NotBlank
    @Column(name = "order_address", nullable = false)
    private String address;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Order> orders;

    @JsonIgnore
    @Column(name = "order_took")
    private boolean orderTook;

    @JsonIgnore
    @Column(name = "delivered")
    private boolean delivered;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    private Delivery delivery;
}
