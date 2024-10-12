package com.example.umg_moto_xpress_android.models.data;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

public class CarouselHomeData {
    private String title;
    private String description;
    private CarouselItem carouselItem;

    public CarouselHomeData(String title, String description, CarouselItem carouselItem) {
        this.title = title;
        this.description = description;
        this.carouselItem = carouselItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CarouselItem getCarouselItem() {
        return carouselItem;
    }

    public void setCarouselItem(CarouselItem carouselItem) {
        this.carouselItem = carouselItem;
    }
}
