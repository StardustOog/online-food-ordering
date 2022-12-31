package dev.stardustoog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food_center")
public class FoodCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_center_id_seq")
    @SequenceGenerator(name = "food_center_id_seq", sequenceName = "food_center_geq")
    @EqualsAndHashCode.Include
    @Column(name = "food_center_id")
    private long id;

    @Max(30)
    @NotBlank
    @Column(name = "food_center_name", nullable = false)
    private String name;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "food_center_stars", nullable = false)
    private Integer stars;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "food_center_menu")
    private List<Food> food;

    @OneToMany(mappedBy = "foodCenter")
    @JsonIgnore
    private List<Order> orders;

}
