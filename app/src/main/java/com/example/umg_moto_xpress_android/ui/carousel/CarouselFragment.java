package com.example.umg_moto_xpress_android.ui.carousel;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentCarouselBinding;
import com.example.umg_moto_xpress_android.databinding.ItemCardBikerBinding;
import com.example.umg_moto_xpress_android.databinding.ItemCarouselBinding;
import com.example.umg_moto_xpress_android.databinding.ItemCarouselHomeBinding;
import com.example.umg_moto_xpress_android.models.data.CarouselHomeData;
import com.example.umg_moto_xpress_android.tools.DriveUrlConverter;
import com.example.umg_moto_xpress_android.tools.LocalStorageBase64;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;

import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselGravity;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.model.CarouselType;
import org.imaginativeworld.whynotimagecarousel.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class CarouselFragment extends BaseFragment {

    private FragmentCarouselBinding binding;
    private boolean isEditor = false;
    private List<CarouselItem> listCarouselItem;
    private List<CarouselHomeData> carouselHomeDataList;
    private ActionButtonInterface anInterface;
    private boolean isBase64 = false;
    private boolean isHome = false;

    public CarouselFragment() {
    }

    public CarouselFragment(boolean isEditor,boolean isBase64,List<CarouselItem> listCarouselItem,ActionButtonInterface anInterface) {
        this.isEditor = isEditor;
        this.listCarouselItem = listCarouselItem;
        this.anInterface = anInterface;
        this.isBase64 = isBase64;
    }

    public CarouselFragment(boolean isHome,boolean isEditor,boolean isBase64,List<CarouselHomeData> carouselHomeDataList,ActionButtonInterface anInterface) {
        this.isEditor = isEditor;
        this.isHome = isHome;
        this.carouselHomeDataList = carouselHomeDataList;
        listCarouselItem = new ArrayList<>();
        for (CarouselHomeData item:carouselHomeDataList) {
            listCarouselItem.add(item.getCarouselItem());
        }
        this.anInterface = anInterface;
        this.isBase64 = isBase64;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCarouselBinding.inflate(inflater,container,false);

        binding.carousel.registerLifecycle(requireActivity());

        if (listCarouselItem != null){
            if (!isEditor){
                binding.carousel.setAutoPlay(true);
                binding.carousel.setAutoPlayDelay(5000);
                binding.carousel.start();
            }else {
                binding.carousel.setCarouselGravity(CarouselGravity.START);
            }

            binding.carousel.setInfiniteCarousel(true);
            binding.carousel.setData(listCarouselItem);
            if (!isHome)
                binding.carousel.setCarouselListener(new CarouselAdapter());
            else
                binding.carousel.setCarouselListener(new CarouselAdapterHome());
        }

        return binding.getRoot();
    }

    public void updateData(List<CarouselItem> listCarouselItem){
        this.listCarouselItem = listCarouselItem;
        binding.carousel.setData(listCarouselItem);
    }


    private class CarouselAdapter implements CarouselListener {

        @Override
        public void onBindViewHolder(@NonNull ViewBinding viewBinding, @NonNull CarouselItem carouselItem, int i) {
            ItemCarouselBinding currentBinding = (ItemCarouselBinding) viewBinding;
            currentBinding.btnDelete.setVisibility(isEditor ? View.VISIBLE : View.GONE);
            currentBinding.btnDelete.setOnClickListener(view -> {
                if (anInterface != null)
                    anInterface.remove(i);
            });

            if (isBase64){
                Bitmap bitmap = LocalStorageBase64.getInstance().displayBase64Image(carouselItem.getImageUrl());
                currentBinding.imgFont.setImageBitmap(bitmap);
            }else {
                Utils.setImage(currentBinding.imgFont, carouselItem);
            }
        }

        @Nullable
        @Override
        public ViewBinding onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
            return ItemCarouselBinding.inflate(layoutInflater,viewGroup,false);
        }

        @Override
        public void onClick(int i, @NonNull CarouselItem carouselItem) {

        }

        @Override
        public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

        }
    }

    private class CarouselAdapterHome implements CarouselListener {

        @Override
        public void onBindViewHolder(@NonNull ViewBinding viewBinding, @NonNull CarouselItem carouselItem, int i) {
            ItemCarouselHomeBinding currentBinding = (ItemCarouselHomeBinding) viewBinding;
            if (carouselHomeDataList != null){
               CarouselHomeData item = carouselHomeDataList.get(i);
               currentBinding.txtTitle.setText(item.getTitle());
               currentBinding.txtDes.setText(item.getDescription());
            }


            Utils.setImage(currentBinding.includeImage.imgFont, carouselItem);
        }

        @Nullable
        @Override
        public ViewBinding onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
            return ItemCarouselHomeBinding.inflate(layoutInflater,viewGroup,false);
        }

        @Override
        public void onClick(int i, @NonNull CarouselItem carouselItem) {

        }

        @Override
        public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

        }
    }

    public interface ActionButtonInterface{
        void remove(int i);
    }
}