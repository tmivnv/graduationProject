package com.topjava.graduationproject.service.impl;

import com.topjava.graduationproject.entities.Dish;
import com.topjava.graduationproject.repositories.DishRepository;
import com.topjava.graduationproject.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository repository;

    @Override
    public List<Dish> getAllDishes() {
        return repository.findAll();
    }

    @Override
    public Dish getDishById(Long id) {
        return repository.findById(id).get();
    }

}
