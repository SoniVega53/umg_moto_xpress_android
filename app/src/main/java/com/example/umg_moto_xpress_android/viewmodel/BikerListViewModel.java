package com.example.umg_moto_xpress_android.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;
import com.example.umg_moto_xpress_android.models.data.CarouselHomeData;
import com.example.umg_moto_xpress_android.tools.DriveUrlConverter;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<CarouselHomeData> createListCarouselHome(Context context){
        List<CarouselHomeData> carouselItems = new ArrayList<>();

        carouselItems.add(new CarouselHomeData(
                context.getString(R.string.title_home),
                context.getString(R.string.descrip_home),
                new CarouselItem(
                        R.drawable.biker_icon_option2
                )
        ));

        carouselItems.add(new CarouselHomeData(
                "Honda CBR 600RR",
                "La Honda CBR 600RR es una moto deportiva de alto rendimiento, ideal para quienes buscan velocidad y agilidad. ",
                new CarouselItem(
                        DriveUrlConverter.convertDriveUrl("https://drive.google.com/file/d/1NOt_alBkYi_500JewzFklbdkPhk7Tv5j/view?usp=drive_link")
                )
        ));

        carouselItems.add(new CarouselHomeData(
                "Kawasaki Ninja 400",
                "La Kawasaki Ninja 400 destaca por su excelente balance entre rendimiento y accesibilidad.",
                new CarouselItem(
                        DriveUrlConverter.convertDriveUrl("https://drive.google.com/file/d/1YOHlDIcySyvptKhS6AB7f8WHBKvrbmXP/view?usp=sharing")
                )
        ));

        carouselItems.add(new CarouselHomeData(
                "Yamaha MT-07",
                "Con un diseño dinámico y agresivo, la Yamaha MT-07 combina ligereza y potencia. ",
                new CarouselItem(
                        DriveUrlConverter.convertDriveUrl("https://drive.google.com/file/d/1gySbtdlzJFJehkeIlF1hpJoWTjsHIDF9/view?usp=drive_link")
                )
        ));

        carouselItems.add(new CarouselHomeData(
               "BMW S1000RR",
                "La BMW S1000RR es sinónimo de potencia bruta y tecnología de vanguardia. " +
                        "Con un motor de 999 cc y más de 200 caballos de fuerza,",
                new CarouselItem(
                        DriveUrlConverter.convertDriveUrl("https://drive.google.com/file/d/1c8NasZBRawWdhWf50xcLnvjFJum0sCz6/view?usp=sharing")
                )
        ));

        return carouselItems;
    }

    public List<BikerItemModel> listBikerAll(){
        List<BikerItemModel> bikerList = new ArrayList<>();
        bikerList.add(new BikerItemModel("Biker 1","","200",0));
        bikerList.add(new BikerItemModel("Biker 2","","200",0));
        bikerList.add(new BikerItemModel("Biker 3","","200",0));
        bikerList.add(new BikerItemModel("Biker 3","","200",0));
        bikerList.add(new BikerItemModel("Biker 3","","200",0));
        bikerList.add(new BikerItemModel("Biker 3","","200",0));
        bikerList.add(new BikerItemModel("Biker 3","","200",0));
        bikerList.add(new BikerItemModel("Biker 3","","200",0));

        bikerList.add(new BikerItemModel("Biker 3","","200",1));
        bikerList.add(new BikerItemModel("Biker 3","","200",1));
        bikerList.add(new BikerItemModel("Biker 3","","200",1));

        bikerList.add(new BikerItemModel("Biker 3","","200",2));
        bikerList.add(new BikerItemModel("Biker 3","","200",2));

        return bikerList;
    }

    public List<BikerItemModel> getListBikerByType(List<BikerItemModel> list, int type){
        try {
            return list.stream().filter(bikerItemModel -> bikerItemModel.getType() == type).collect(Collectors.toList());
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
