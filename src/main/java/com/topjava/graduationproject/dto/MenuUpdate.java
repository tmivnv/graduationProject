package com.topjava.graduationproject.dto;

import com.topjava.graduationproject.entities.Dish;

import java.util.Set;

public class MenuUpdate {

    private Long restId;
    private Set<Dish> menu;

    public Long getRestId() {
        return restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    public Set<Dish> getMenu() {
        return menu;
    }

    public void setMenu(Set<Dish> menu) {
        this.menu = menu;
    }
}
