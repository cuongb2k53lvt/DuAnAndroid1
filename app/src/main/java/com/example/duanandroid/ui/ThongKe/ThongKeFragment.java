package com.example.duanandroid.ui.ThongKe;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanandroid.R;
import com.example.duanandroid.Sql.HoaDonSql;
import com.example.duanandroid.Sql.Sqlite;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class ThongKeFragment extends Fragment {
    BarChart barChart;

    public ThongKeFragment() {
    }


    public static ThongKeFragment newInstance(String param1, String param2) {
        ThongKeFragment fragment = new ThongKeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

        barChart = view.findViewById(R.id.barChart);

        List<BarEntry> barEntries = new ArrayList<>();
//        barEntries.add(new BarEntry(1, 420));

        Sqlite sqlite = new Sqlite(getActivity());
        HoaDonSql hoaDonSql = new HoaDonSql(sqlite);

        for (int i = 1; i <= 12; i++) {
            double doanhthu = 0;
            if (i < 10) {
                doanhthu = hoaDonSql.LayDoanhThuTheoThang("0" + Integer.toString(i));
            } else {
                doanhthu = hoaDonSql.LayDoanhThuTheoThang(Integer.toString(i));
            }
            barEntries.add(new BarEntry(i, (float) doanhthu));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Tháng");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Doanh thu");
        barChart.animateY(2000);
        return view;
    }
}