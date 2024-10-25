package com.example.umg_moto_xpress_android.ui.bitacora;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentInformeBikerBinding;
import com.example.umg_moto_xpress_android.ui.base.BaseFragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class InformeBikerFragment extends BaseFragment {

    private FragmentInformeBikerBinding binding;
    private static final int REQUEST_WRITE_PERMISSION = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInformeBikerBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());


        generateDataGraficaMotosDisponibles();
        generateDataGraficaReservacionesActivas();
        generateCircleGraficaMasSolicitada();

        binding.btnDowPdf.setOnClickListener(view -> {
        });

        return binding.getRoot();
    }

    private void generateDataGraficaMotosDisponibles(){
        // Gráfico 1: Reporte General de Motocicletas Disponibles (por zona)
        ArrayList<BarEntry> barEntries1 = new ArrayList<>();
        barEntries1.add(new BarEntry(1, 30));  // Zona 1
        barEntries1.add(new BarEntry(2, 50));  // Zona 2
        barEntries1.add(new BarEntry(3, 20));  // Zona 3
        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "Motocicletas Disponibles por Zona");
        barDataSet1.setColors(Color.BLUE);
        BarData barData1 = new BarData(barDataSet1);
        binding.barChart1.setData(barData1);
        binding.barChart1.animateY(1000);
    }

    private void generateDataGraficaReservacionesActivas(){
        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        barEntries2.add(new BarEntry(1, 15));  // Categoría 1
        barEntries2.add(new BarEntry(2, 25));  // Categoría 2
        barEntries2.add(new BarEntry(3, 10));  // Categoría 3
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "Reservaciones Activas por Categoría");
        barDataSet2.setColors(Color.GREEN);
        BarData barData2 = new BarData(barDataSet2);
        binding.barChart2.setData(barData2);
        binding.barChart2.animateY(1000);
    }

    private void generateCircleGraficaMasSolicitada(){
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(40f, "Motocicleta A"));
        pieEntries.add(new PieEntry(30f, "Motocicleta B"));
        pieEntries.add(new PieEntry(20f, "Motocicleta C"));
        pieEntries.add(new PieEntry(10f, "Motocicleta D"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Motocicletas Más Solicitadas");
        pieDataSet.setColors(new int[] {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN});
        PieData pieData = new PieData(pieDataSet);
        binding.pieChart.setData(pieData);
        binding.pieChart.animateY(1000);
    }



}