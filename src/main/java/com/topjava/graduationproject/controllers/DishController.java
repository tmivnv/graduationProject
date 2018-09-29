package com.topjava.graduationproject.controllers;

import com.topjava.graduationproject.entities.Dish;
import com.topjava.graduationproject.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class DishController {

    @Autowired
    DishService service;


    @RequestMapping(value = "dishes")
    @ResponseBody
    ResponseEntity<Object> getAllDishes() {
        List<Dish> dishList = service.getAllDishes();
        return new ResponseEntity<>(dishList, HttpStatus.OK);

    }

    @RequestMapping(value = "dishes/{id}")
    @ResponseBody
    ResponseEntity<Object> getDish(@PathVariable Long id) {

        Dish dish = service.getDishById(id);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }



}
