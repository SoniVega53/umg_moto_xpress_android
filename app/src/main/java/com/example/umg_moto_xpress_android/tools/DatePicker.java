package com.example.umg_moto_xpress_android.tools;

import android.app.DatePickerDialog;
import android.content.Context;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePicker {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


    public static String parseDate(int value) {
        if (value > 0 && value < 10) {
            return "0" + value;
        }
        return String.valueOf(value);
    }

    public void getDate(Context context, TextInputEditText editText, String string, String minDate){
        Calendar c = Calendar.getInstance();

        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context,
                (datePicker, year, month, day) -> {
                    final int m = month + 1;
                    if (string.equals("0")) editText.setText(day + "/" + m + "/" + year);
                    else editText.setText(parseDate(day) + "/" + parseDate(m) + "/" + year);
                }, mYear, mMonth, mDay);

        try{
            if (minDate.isEmpty()){
                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
            }else{
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                cal.setTime(sdf.parse(minDate));

                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
            }
        }catch (Exception pe){
            pe.printStackTrace();
        }


        if (!String.valueOf(editText.getText()).trim().equals("")) {
            try {
                c.setTime(formatDate(String.valueOf(editText.getText())));
                dialog.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dialog.show();
    }

    public void getDate(Context context, TextInputEditText editText, String string, boolean limit,boolean minAmount) {
        Calendar c = Calendar.getInstance();

        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context,
                (datePicker, year, month, day) -> {
                    final int m = month + 1;
                    if (string.equals("0")) editText.setText(day + "/" + m + "/" + year);
                    else editText.setText(parseDate(day) + "/" + parseDate(m) + "/" + year);
                }, mYear, mMonth, mDay);

        if (limit) {
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            c.add(Calendar.MONTH, -6);
            dialog.getDatePicker().setMinDate(c.getTimeInMillis());
        }
        if (minAmount){
            c.add(Calendar.MONTH, 0);
            dialog.getDatePicker().setMinDate(c.getTimeInMillis());
        }

        if (!String.valueOf(editText.getText()).trim().equals("")) {
            try {
                c.setTime(formatDate(String.valueOf(editText.getText())));
                dialog.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dialog.show();
    }

    /*
     * set limit between to dates
     * params:
     *   @editext = from
     *   @edittext2 = to
     *   @isFrom = valida if edittext is from
     * */

    public void getDate(Context context, TextInputEditText editText, TextInputEditText editText2, Boolean isFrom) {
        Calendar c = Calendar.getInstance();

        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context,
                (datePicker, year, month, day) -> {
                    final int m = month + 1;
                    editText.setText(day + "/" + m + "/" + year);
                }, mYear, mMonth, mDay);

        dialog.getDatePicker().setMinDate(c.getTimeInMillis());

        if (!editText.getText().toString().trim().equals("")) {
            Date dateValue = formatDate(editText.getText().toString());
            c.setTime(dateValue);
            dialog.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        }

        if (!editText2.getText().toString().trim().equals("")) {
            try {

                Date date = formatDate(editText2.getText().toString());

                if(Boolean.TRUE.equals(isFrom)){
                    dialog.getDatePicker().setMaxDate(date.getTime());
                }else{
                    dialog.getDatePicker().setMinDate(date.getTime());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        dialog.show();
    }

    public void getDateNotifications(Context context, TextInputEditText editText, TextInputEditText editText2, Boolean isFrom){
        Calendar c = Calendar.getInstance();

        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context,
                (datePicker, year, month, day) -> {
                    final int m = month + 1;
                    editText.setText(parseDate(day) + "/" + parseDate(m) + "/" + year);
                }, mYear, mMonth, mDay);

        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());

        if (!editText.getText().toString().trim().equals("")) {
            Date dateValue = formatDate(editText.getText().toString());
            c.setTime(dateValue);
            dialog.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        }

        if (!editText2.getText().toString().trim().equals("")) {
            try {

                Date date = formatDate(editText2.getText().toString());

                if(Boolean.TRUE.equals(isFrom)){
                    dialog.getDatePicker().setMaxDate(date.getTime());
                }else{
                    dialog.getDatePicker().setMinDate(date.getTime());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        dialog.show();
    }

    public void getDate(Context context, TextInputEditText editText, int plusMonths) {
        Calendar c = Calendar.getInstance();

        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context,
                (datePicker, year, month, day) -> {
                    final int m = month + 1;
                    String dayString = Integer.toString(day);
                    String monthString =Integer.toString(month);

                    if (dayString.length() == 1 && monthString.length() == 1) {
                        editText.setText("0" + day + "/0" + m + "/" + year);
                    } else if(monthString.length() == 1) {
                        editText.setText(day + "/0" + m + "/" + year);
                    } else if(dayString.length() == 1) {
                        editText.setText("0" + day + "/" + m + "/" + year);
                    } else {
                        editText.setText(day + "/" + m + "/" + year);
                    }
                }, mYear, mMonth, mDay);

        dialog.getDatePicker().setMinDate(c.getTimeInMillis());

        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.MONTH, plusMonths);
        dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

        if (!String.valueOf(editText.getText()).trim().equals("")) {
            try {
                c.setTime(formatDate(String.valueOf(editText.getText())));
                dialog.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dialog.show();
    }

    private Date formatDate(String base) {
        try {
            return dateFormat.parse(String.valueOf(base).replace("/", "-"));
        } catch (Exception e) {
            return null;
        }
    }
}
