package com.topjava.graduationproject.controllers;

import com.topjava.graduationproject.dto.MenuUpdate;
import com.topjava.graduationproject.entities.Restaurant;
import com.topjava.graduationproject.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class RestaurantController {

    @Autowired
    RestaurantService service;

    @RequestMapping(value = "restaurants")
    @ResponseBody
    ResponseEntity<Object> getAllRestaurants() {
        List<Restaurant> restaurantList = service.getAllRestaurants();
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);

    }

    @RequestMapping(value = "restaurants/{id}")
    @ResponseBody
    ResponseEntity<Object> getRestaurant(@PathVariable Long id) {

        Restaurant restaurant = service.getRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @RequestMapping(value = "restaurants/update", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    ResponseEntity<Object> updateMenu(@RequestBody MenuUpdate menuUpdate) {
        Restaurant restaurant = service.updateMenu(menuUpdate.getRestId(), menuUpdate.getMenu());
        return restaurant != null ? new ResponseEntity<>(restaurant, HttpStatus.OK) : new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);

    }



}
