package com.example.umg_moto_xpress_android.tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FormatCurrency {
    public String formatCurrency(String value){
        String newValue = !value.equals("0.0") ? value : "0";
        if (!newValue.isEmpty() && !newValue.equals("0")){
            Double de = Double.parseDouble(value);
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat formatDe = new DecimalFormat("#.##",symbols);
            return "Q "+formatDe.format(de);
        }
        return "GTQ 0.00";
    }
}
