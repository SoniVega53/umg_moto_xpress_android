package com.example.umg_moto_xpress_android.ui.base;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.dialog.message.DetailsMessageInf;
import com.google.android.material.textfield.TextInputEditText;

public class BaseFragment extends Fragment {
    private InterfaceMainAction interfaceMainAction;
    private OnBackPressedCallback callback;
    private boolean callbackEnabled = true;
    protected boolean focusFragment = false;

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


    protected void onBackPressedCall(OnBackPressedCall onBackPressedCall){
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
        callback.remove();
        callbackEnabled = false;
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
}