package com.example.umg_moto_xpress_android.dialog;

import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.example.umg_moto_xpress_android.tools.FormatCurrency;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class BaseDialog extends DialogFragment {

    protected boolean focusFragment = false;

    protected void functionFocusFragment(View view){
        view.getViewTreeObserver().addOnWindowFocusChangeListener(hasFocus -> {
            focusFragment = hasFocus;
        });
    }
    protected String formatCurrency(double value){
        return new FormatCurrency().formatCurrency(String.valueOf(value));
    }

    protected String formatCurrency(String value){
        return new FormatCurrency().formatCurrency(value);
    }

    protected String getFormDecimal(double num){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##",symbols);

        return df.format(num);
    }

    public interface OnCheckClick{
        void onCheck(boolean b);
    }

    public interface OnClick{
        void onClick(View view);
    }

    public interface OnClickType{
        void onClick(int num);
    }

    public interface OnClickValues{
        void onClick(String[] val);
        default void defaultAction(){}
    }

    public interface OnClickUpdateDelete{
        void onDelete();
        void onUpdate();
    }
}
