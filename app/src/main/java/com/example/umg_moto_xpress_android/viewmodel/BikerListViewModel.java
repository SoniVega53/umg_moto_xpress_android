package com.example.umg_moto_xpress_android.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.models.data.BikerItemModel;
import com.example.umg_moto_xpress_android.models.data.CarouselHomeData;
import com.example.umg_moto_xpress_android.models.data.PersonData;
import com.example.umg_moto_xpress_android.models.data.UserDataModel;
import com.example.umg_moto_xpress_android.models.response.Login.LoginResponse;
import com.example.umg_moto_xpress_android.models.response.usuario.UserPersonaResponse;
import com.example.umg_moto_xpress_android.repositories.StatusResponse;
import com.example.umg_moto_xpress_android.repositories.user.UserRepository;
import com.example.umg_moto_xpress_android.tools.DriveUrlConverter;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BikerListViewModel extends ViewModel {
    private MutableLiveData<List<BikerItemModel>> bikerListDisponibles;
    private MutableLiveData<List<BikerItemModel>> bikerListReservadas;

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

    public MutableLiveData<List<BikerItemModel>> getBikerListDisponibles() {
        return bikerListDisponibles;
    }

    public MutableLiveData<List<BikerItemModel>> getBikerListReservadas() {
        return bikerListReservadas;
    }

    public MutableLiveData<List<BikerItemModel>> getBikerListDisponibles(boolean isCreate) {
        bikerListDisponibles = new MutableLiveData<>();
        List<BikerItemModel> bikerList =  new ArrayList<>();
        if (isCreate)
            bikerList.add(new BikerItemModel("Honda CB500F","La Honda CB500F es una motocicleta ágil y estilizada, " +
                    "perfecta para recorridos urbanos y trayectos en carretera.","200",0));

        bikerList.addAll(listBikerAll());
        bikerListDisponibles.postValue(bikerList);
        return bikerListDisponibles;
    }

    public MutableLiveData<List<BikerItemModel>> getBikerListReservadas(boolean isCreate) {
        bikerListReservadas = new MutableLiveData<>();
        List<BikerItemModel> bikerList =  new ArrayList<>();
        if (isCreate)
            bikerList.add(new BikerItemModel("Honda CB500F","La Honda CB500F es una motocicleta ágil y estilizada, " +
                    "perfecta para recorridos urbanos y trayectos en carretera.","200",0));

        bikerList.addAll(listBikerReservation());
        bikerListReservadas.postValue(bikerList);
        return bikerListReservadas;
    }

    public void getBikerListReservadasCancel() {
        bikerListDisponibles = new MutableLiveData<>();
        List<BikerItemModel> bikerList =  new ArrayList<>();
        bikerList.add(new BikerItemModel("Honda CB500F","La Honda CB500F es una motocicleta ágil y estilizada, " +
                "perfecta para recorridos urbanos y trayectos en carretera.","200",0));
        bikerList.addAll(listBikerAll());
        bikerListDisponibles.postValue(bikerList);

        bikerListReservadas = new MutableLiveData<>();
        List<BikerItemModel> bikerListRe =  new ArrayList<>();
        bikerListRe.addAll(listBikerReservation2());
        bikerListReservadas.postValue(bikerListRe);
    }


    public List<BikerItemModel> listBikerAll(){
        List<BikerItemModel> bikerList = new ArrayList<>();


        bikerList.add(new BikerItemModel("Yamaha MT-07"," La Yamaha MT-07 es conocida por su gran potencia y maniobrabilidad. Equipado con un motor de 689 cc" +
                " y un peso ligero, este modelo es ideal para los amantes de la velocidad y la adrenalina.","300",0));

        bikerList.add(new BikerItemModel("Kawasaki Ninja 400","Con un diseño deportivo y aerodinámico, la Kawasaki Ninja 400 combina estilo y rendimiento. ","200",0));
        bikerList.add(new BikerItemModel("Ducati Scrambler Icon","La Ducati Scrambler Icon es una motocicleta de estilo retro que no pasa desapercibida. Su motor de 803 cc, junto con su peso ligero y " +
                "chasis compacto, brindan una experiencia de conducción ágil y divertida. ","200",0));

        bikerList.add(new BikerItemModel("BMW R 1250 GS","La BMW R 1250 GS es una de las mejores opciones" +
                " para aventuras de larga distancia. Equipada con un motor bóxer de 1254 cc","500",1));
        bikerList.add(new BikerItemModel("Ducati Scrambler Icon","La Ducati Scrambler Icon es una motocicleta de estilo retro que no pasa desapercibida. Su motor de 803 cc","200",1));

        bikerList.add(new BikerItemModel("Harley-Davidson Iron 883","La Iron 883 de Harley-Davidson es un ícono del estilo cruiser." +
                " Con su motor de 883 cc y su diseño robusto, esta moto ofrece un recorrido suave y estable.","500",2));

        return bikerList;
    }

    public List<BikerItemModel> listBikerReservation(){
        List<BikerItemModel> bikerList = new ArrayList<>();
        bikerList.add(new BikerItemModel("Harley-Davidson Iron 883","La Iron 883 de Harley-Davidson es un ícono del estilo cruiser." +
                " Con su motor de 883 cc y su diseño robusto, esta moto ofrece un recorrido suave y estable.","500",0));

        bikerList.add(new BikerItemModel("BMW R 1250 GS","La BMW R 1250 GS es una de las mejores opciones" +
                " para aventuras de larga distancia. Equipada con un motor bóxer de 1254 cc","500",1));

        return bikerList;
    }

    public List<BikerItemModel> listBikerReservation2(){
        List<BikerItemModel> bikerList = new ArrayList<>();
        bikerList.add(new BikerItemModel("Harley-Davidson Iron 883","La Iron 883 de Harley-Davidson es un ícono del estilo cruiser." +
                " Con su motor de 883 cc y su diseño robusto, esta moto ofrece un recorrido suave y estable.","500",0));

        bikerList.add(new BikerItemModel("BMW R 1250 GS","La BMW R 1250 GS es una de las mejores opciones" +
                " para aventuras de larga distancia. Equipada con un motor bóxer de 1254 cc","500",1));

        bikerList.add(new BikerItemModel("Honda CB500F","La Honda CB500F es una motocicleta ágil y estilizada, " +
                "perfecta para recorridos urbanos y trayectos en carretera.","200",1));

        return bikerList;
    }


    public List<BikerItemModel> getListBikerByType(List<BikerItemModel> list, int type){
        try {
            return list.stream().filter(bikerItemModel -> bikerItemModel.getType() == type).collect(Collectors.toList());
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public List<BikerItemModel> getFilterListBikerByType(List<BikerItemModel> listBiker, String search){
        if (search != null && !search.isEmpty()){
            List<BikerItemModel> list = new ArrayList<>();
            for (BikerItemModel item:listBiker) {
                if (item.getTitle().toLowerCase().contains(search.toLowerCase().trim()))
                    list.add(item);
            }
            return list;
        }else{
            return listBiker;
        }
    }


}
