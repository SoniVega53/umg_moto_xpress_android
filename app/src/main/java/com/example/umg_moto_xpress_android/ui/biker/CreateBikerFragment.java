package com.example.umg_moto_xpress_android.ui.biker;

import static com.example.umg_moto_xpress_android.R.drawable.image_solid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentCreateBikerBinding;
import com.example.umg_moto_xpress_android.tools.DriveUrlConverter;
import com.example.umg_moto_xpress_android.tools.LocalStorageBase64;
import com.example.umg_moto_xpress_android.tools.SimpleTextWatcher;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;
import com.example.umg_moto_xpress_android.ui.carousel.CarouselFragment;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.gson.Gson;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreateBikerFragment extends BaseFragment {

    private FragmentCreateBikerBinding binding;
    private static final int PICK_IMAGE_REQUEST = 1;
    private List<String> listImagesAdd;
    private List<CarouselItem> listCarouselItem;
    private CarouselFragment fragmentCarousel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateBikerBinding.inflate(inflater,container,false);
        listImagesAdd = new ArrayList<>();
        listCarouselItem = new ArrayList<>();
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());

        createInstanceManager(savedInstanceState != null);
        visibleImage();

        binding.btnAddImage.setOnClickListener(view -> {
            openFileChooser();
        });

        binding.includeImage.btnDelete.setOnClickListener(view -> {
            removeImage(0);
            visibleImage();
        });

        binding.btnContinue.setOnClickListener(view -> {
            navigation(view,R.id.action_createBikerFragment_to_addBikerInventaryFragment);
        });

        binding.componentName.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentde.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentCili.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentCapaci.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentNameMo.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentYear.getEditText().addTextChangedListener(simpleTextWatcher());
        binding.componentMar.getEditText().addTextChangedListener(simpleTextWatcher());

        return binding.getRoot();
    }

    private SimpleTextWatcher simpleTextWatcher(){
        return new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);
                enableButton();
            }
        };
    }

    private void enableButton(){
        binding.btnContinue.setEnabled(
                binding.componentName.isEmptyText() && binding.componentde.isEmptyText() && binding.componentCili.isEmptyText()
                        && binding.componentCapaci.isEmptyText() && binding.componentNameMo.isEmptyText() && binding.componentYear.isEmptyText()
                        && binding.componentMar.isEmptyText()
        );
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            String image = LocalStorageBase64.getInstance().getBaseSelectStorage(requireActivity(),imageUri);
            listImagesAdd.add(image);
            listCarouselItem.add(new CarouselItem(image));

            actionChangeImage();
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);
    }

    private void actionChangeImage(){
        visibleImage();
        createCarouselAllImages();
    }

    private void createCarouselAllImages(){
        if (listImagesAdd.size() > 1){
            fragmentCarousel = (CarouselFragment) getChildFragmentManager().findFragmentById(binding.frameCarousel.getId());
            if (fragmentCarousel != null) {
                fragmentCarousel.updateData(listCarouselItem);
            } else {
                createInstanceManager(true);
            }
        }else {
            Bitmap bitmap = LocalStorageBase64.getInstance().displayBase64Image(listImagesAdd.get(0));
            binding.includeImage.imgFont.setImageBitmap(bitmap);
            binding.includeImage.btnDelete.setVisibility(View.VISIBLE);
        }
    }

    //Elimina Imagen
    private void removeImage(int i){
        if (listImagesAdd.size() > i && i > -1){
            listImagesAdd.remove(i);
            listCarouselItem.remove(i);
        }
    }

    private void visibleImage(){
        binding.conUncle.setVisibility(listImagesAdd.size() == 1 ? View.VISIBLE :View.GONE);
        binding.imgEmpty.setVisibility(listImagesAdd.size() == 0 ? View.VISIBLE:View.GONE);
        binding.frameCarousel.setVisibility(listImagesAdd.size() > 1 ? View.VISIBLE:View.GONE);
    }

    private void createInstanceManager(boolean isValid){
        if (isValid){
            fragmentCarousel = new CarouselFragment(true, true,listCarouselItem,i -> {
                removeImage(i);
                fragmentCarousel.updateData(listCarouselItem);
                actionChangeImage();
            });
            addChildFragmentManager(fragmentCarousel,binding.frameCarousel.getId());
        }
    }
}