package com.example.bmicalc_v2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.util.ArrayList;
import java.util.List;

public class BMIChartActivity extends AppCompatActivity {

    private LineChart bmiChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_chart);

        bmiChart = findViewById(R.id.bmiChart);
        Button backButton = findViewById(R.id.backButton);

        setupChart();
        loadMockData();

        backButton.setOnClickListener(v -> finish());
    }

    private void setupChart() {
        bmiChart.getDescription().setEnabled(false);
        bmiChart.setDrawGridBackground(false);
        bmiChart.setDrawBorders(false);
        bmiChart.setTouchEnabled(true);
        bmiChart.setDragEnabled(true);
        bmiChart.setScaleEnabled(true);
        bmiChart.setPinchZoom(true);
        bmiChart.setBackgroundColor(Color.WHITE);

        XAxis xAxis = bmiChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = bmiChart.getAxisLeft();
        leftAxis.setAxisMinimum(15f);
        leftAxis.setAxisMaximum(35f);
        leftAxis.setDrawGridLines(true);

        bmiChart.getAxisRight().setEnabled(false);
        bmiChart.getLegend().setEnabled(true);

        final String[] months = new String[] { "Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec" };
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
    }

    private void loadMockData() {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 24.5f));
        entries.add(new Entry(1, 25.1f));
        entries.add(new Entry(2, 24.8f));
        entries.add(new Entry(3, 23.9f));
        entries.add(new Entry(4, 23.2f));
        entries.add(new Entry(5, 22.7f));

        LineDataSet dataSet = new LineDataSet(entries, "BMI w czasie");
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawCircleHole(true);
        dataSet.setValueTextSize(10f);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(Color.parseColor("#4D92C5FD"));

        LineData lineData = new LineData(dataSet);
        bmiChart.setData(lineData);
        bmiChart.invalidate();
    }
}