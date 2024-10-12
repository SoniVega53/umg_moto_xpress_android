package com.example.umg_moto_xpress_android.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.umg_moto_xpress_android.tools.DriveUrlConverter;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class BikerListViewModel extends ViewModel {


    public List<CarouselItem> createListCarousel(){
        List<CarouselItem> carouselItems = new ArrayList<>();
        carouselItems.add(new CarouselItem(
                DriveUrlConverter.convertDriveUrl("https://drive.google.com/file/d/1NOt_alBkYi_500JewzFklbdkPhk7Tv5j/view?usp=drive_link")
        ));

        carouselItems.add(new CarouselItem(
                DriveUrlConverter.convertDriveUrl("https://drive.google.com/file/d/1YOHlDIcySyvptKhS6AB7f8WHBKvrbmXP/view?usp=sharing")
        ));


        carouselItems.add(new CarouselItem(
                DriveUrlConverter.convertDriveUrl("https://drive.google.com/file/d/1gySbtdlzJFJehkeIlF1hpJoWTjsHIDF9/view?usp=drive_link")
        ));

        carouselItems.add(new CarouselItem(
                DriveUrlConverter.convertDriveUrl("https://drive.google.com/file/d/1NF4AskIkao6O-UopPMjVf0ss5HAo_YoR/view?usp=sharing")
        ));

        carouselItems.add(new CarouselItem(
                DriveUrlConverter.convertDriveUrl("https://drive.google.com/file/d/1c8NasZBRawWdhWf50xcLnvjFJum0sCz6/view?usp=sharing")
        ));


        return carouselItems;
    }
}
