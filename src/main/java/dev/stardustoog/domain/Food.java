package dev.stardustoog.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq")
    @SequenceGenerator(name = "food_seq", sequenceName = "food_geq")
    @EqualsAndHashCode.Include
    @Column(name = "food_id")
    private long id;

    @NotBlank
    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Min(1)
    @Max(1000)
    @Column(name = "food_price", nullable = false)
    private int foodPrice;

}
