package dev.stardustoog.controller;

import dev.stardustoog.domain.Delivery;
import dev.stardustoog.domain.Food;
import dev.stardustoog.domain.FoodCenter;
import dev.stardustoog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("food-center")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addFoodCenter(@Valid @RequestBody FoodCenter foodCenter) {
        adminService.addFoodCenter(foodCenter);
    }

    @DeleteMapping("food-center/{foodCenterId}")
    public void removeFoodCenter(@PathVariable long foodCenterId){
        adminService.removeFoodCenter(foodCenterId);
    }

    @PostMapping("food-center/{foodCenterId}/food")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addFoodToFoodCenter(@PathVariable long foodCenterId, @Valid @RequestBody final Food food) {
        adminService.addFoodToFoodCenter(foodCenterId, food);
    }

    @PostMapping("deliverer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addNewDeliverer(@RequestBody final Delivery delivery) {
        adminService.addNewDeliverer(delivery);
    }

    @DeleteMapping("deliverer/{delivererId}")
    public void removeDeliverer(@PathVariable long delivererId) {
        adminService.removeDeliverer(delivererId);
    }

    @GetMapping("deliverer")
    public List<Delivery> deliveryServices() {
        return adminService.deliveryServices();
    }

    @DeleteMapping("food-center/{foodCenterId}/food/{foodId}")
    public void removeFoodFromFoodCenter(@PathVariable long foodCenterId, @PathVariable long foodId) {
        adminService.removeFoodFromFoodCenter(foodCenterId, foodId);
    }

}
