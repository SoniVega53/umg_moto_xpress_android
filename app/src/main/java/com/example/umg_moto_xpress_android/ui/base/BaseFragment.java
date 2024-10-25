package com.example.umg_moto_xpress_android.ui.base;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.dialog.message.DetailsMessageInf;
import com.example.umg_moto_xpress_android.models.data.UserDecodeData;
import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.StringTool;
import com.example.umg_moto_xpress_android.viewmodel.BikerListViewModel;
import com.example.umg_moto_xpress_android.viewmodel.LoginViewModel;
import com.example.umg_moto_xpress_android.viewmodel.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class BaseFragment extends Fragment {
    private InterfaceMainAction interfaceMainAction;
    private OnBackPressedCallback callback;
    private boolean callbackEnabled = true;
    protected boolean focusFragment = false;

    protected LoginViewModel loginViewModel;
    protected UserViewModel userViewModel;
    protected BikerListViewModel bikerListViewModel;

    protected void initViewModel(){
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        bikerListViewModel = new ViewModelProvider(requireActivity()).get(BikerListViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof InterfaceMainAction){
            interfaceMainAction = (InterfaceMainAction)  context;
        }
    }

    protected void hiddenShowNavBar(boolean isHidden){
        try {
            interfaceMainAction.hiddenShowNavbar(isHidden);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void loadingShow(boolean isShow){
        try {
            interfaceMainAction.loadingShow(isShow);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void logout(View.OnClickListener listener){
        try {
            interfaceMainAction.logout(listener);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected UserDecodeData getUserDecodeData(){
        return SharedPreferencesTool.readSecureUser(requireActivity(), StringTool.LOGIN_USER,"");
    }

    protected void navigation(View view, int id){
        try {
            Navigation.findNavController(view).navigate(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void navigation(View view, int id, Bundle bundle){
        try {
            Navigation.findNavController(view).navigate(id,bundle);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void navPopBackStack(View view, int id){
        try {
            Navigation.findNavController(view).popBackStack(id,false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void navPopBackStack(View view){
        try {
            Navigation.findNavController(view).popBackStack();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void onBackPressedCall(OnBackPressedCall onBackPressedCall){
        callbackEnabled = true;
        callback = new OnBackPressedCallback(callbackEnabled) {
            @Override
            public void handleOnBackPressed() {
                if (onBackPressedCall != null)
                    onBackPressedCall.handleOnBackPressed();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

    protected void clearOnBackPressedCall(){
        try {
            callback.remove();
            callbackEnabled = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void functionFocusFragment(View view){
        view.getViewTreeObserver().addOnWindowFocusChangeListener(hasFocus -> {
            focusFragment = hasFocus;
        });
    }

    protected void dialogMessage(String title,String message,int type){
        if (focusFragment){
            DetailsMessageInf eliDia= new DetailsMessageInf(title,message,type);
            eliDia.show(getChildFragmentManager(), DetailsMessageInf.TAG);
            focusFragment = false;
        }
    }

    protected void dialogMessage(String title,String message,int type, View.OnClickListener listener){
        if (focusFragment){
            DetailsMessageInf eliDia = new DetailsMessageInf(title,message,type);
            eliDia.setLis(listener::onClick);
            eliDia.show(getChildFragmentManager(), DetailsMessageInf.TAG);
            focusFragment = false;
        }
    }

    protected void setListenerTextWatcher(TextInputEditText input, TextChangedListener textChangedListener) {
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No hay evento, solo se coloca esta nota por requerimiento de SONAR.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (textChangedListener != null)
                    textChangedListener.onTextChanged(charSequence,i,i1,i2);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No hay evento, solo se coloca esta nota por requerimiento de SONAR.
            }
        });
    }

    private void replaceLogin(){
        getParentFragmentManager().popBackStack();
        Fragment mainFragment = new HomeFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.navHostFragment, mainFragment);
        transaction.commit();
    }


    protected interface OnBackPressedCall{
        void handleOnBackPressed();
    }

    protected interface TextChangedListener{
        void onTextChanged(CharSequence charSequence, int i, int i1, int i2);
    }

    protected void logoutLogin(View viewFragment){
        logout(view -> {
            focusFragment = true;
            dialogMessage(getString(R.string.logout),getString(R.string.logout_message),0,view1 -> {
                clearOnBackPressedCall();
                SharedPreferencesTool.writeSecureString(requireActivity(), StringTool.LOGIN_SESSION,"");
                navPopBackStack(viewFragment,R.id.loginFragment);
            });
        });
    }
    protected void addChildFragmentManager(Fragment fragment, int idComponent){
        Fragment fragExs = getChildFragmentManager().findFragmentById(idComponent);
        if (fragment != null && fragExs == null) {
            getChildFragmentManager().beginTransaction()
                    .add(idComponent, fragment) // Usa el ID de tu FrameLayout
                    .commit();
        }
    }

    protected void addChildFragmentManagerDelete(Fragment fragment, int idComponent) {
        Fragment fragExs = getChildFragmentManager().findFragmentById(idComponent);
        if (fragExs != null) {
            // Si el fragmento ya existe, elimínalo primero
            getChildFragmentManager().beginTransaction()
                    .remove(fragExs)
                    .commit();
        }

        // Agrega el nuevo fragmento si es válido
        if (fragment != null) {
            getChildFragmentManager().beginTransaction()
                    .add(idComponent, fragment) // Usa el ID de tu FrameLayout
                    .commit();
        }
    }

    protected void sleepService(Runnable runnable, int mili){
        new Handler(Looper.getMainLooper()).postDelayed(runnable, mili);
    }
}