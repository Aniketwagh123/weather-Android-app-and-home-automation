package com.example.techno.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.techno.R;
import com.example.techno.model.SensorData;
import com.example.techno.model.Users;
import com.example.techno.retrofit.RetrofitService;
import com.example.techno.retrofit.UserApi;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kotlinx.coroutines.AwaitKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Humidity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private AppCompatButton datePickerBtn;
    private LineChart mpLineChart;
    ArrayList<Entry>dataValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity);

        initDatePicker();
        datePickerBtn = findViewById(R.id.date_picker_button);
        datePickerBtn.setText(getTodaysDate());
        loadChart();

    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = makeDateString(day,month,year);
                datePickerBtn.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month)+" "+day+" "+year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)return "JAN";
        else if (month == 2)return "FEB";
        else if (month == 3)return "MAR";
        else if (month == 4)return "APR";
        else if (month == 5)return "MAY";
        else if (month == 6)return "JUN";
        else if (month == 7)return "JUL";
        else if (month == 8)return "AUG";
        else if (month == 9)return "SEP";
        else if (month == 10)return "OCT";
        else if (month == 11)return "NOV";
        else  return "DEC";

    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private ArrayList<Entry> dataValues1(){
        ArrayList<Entry> dataValues = new ArrayList<Entry>();
        ArrayList<Entry> tempo = new ArrayList<Entry>();
        ArrayList<Integer>humidityData = new ArrayList<>();




        Log.v("letist",humidityData+"humidityData affff");
        {
        dataValues.add(new Entry(0,60));
        dataValues.add(new Entry(1,20));
        dataValues.add(new Entry(2,0));
        dataValues.add(new Entry(3,30));
        dataValues.add(new Entry(4,-20));
        dataValues.add(new Entry(5,40));
        dataValues.add(new Entry(6,38));
//        dataValues.add(new Entry(7,28));
//        dataValues.add(new Entry(8,8));
//        dataValues.add(new Entry(9,21));
//        dataValues.add(new Entry(10,31));
//        dataValues.add(new Entry(11,37));
//        dataValues.add(new Entry(12,22));
//        dataValues.add(new Entry(13,30));
//        dataValues.add(new Entry(14,28));
//        dataValues.add(new Entry(15,38));
//        dataValues.add(new Entry(16,13));
//        dataValues.add(new Entry(17,28));
//        dataValues.add(new Entry(18,32));
//        dataValues.add(new Entry(19,21));
//        dataValues.add(new Entry(20,20));
//        dataValues.add(new Entry(21,24));
//        dataValues.add(new Entry(22,2));
//        dataValues.add(new Entry(23,10));
//        dataValues.add(new Entry(24,28));
//        dataValues.add(new Entry(25,18));
//        dataValues.add(new Entry(26,38));
//        dataValues.add(new Entry(27,28));
//        dataValues.add(new Entry(28,8));
//        dataValues.add(new Entry(29,21));
//        dataValues.add(new Entry(30,20));
//        dataValues.add(new Entry(31,24));
//        dataValues.add(new Entry(32,2));
//        dataValues.add(new Entry(33,10));
//        dataValues.add(new Entry(34,28));
//        dataValues.add(new Entry(35,18));
//        dataValues.add(new Entry(36,38));
//        dataValues.add(new Entry(37,28));
//        dataValues.add(new Entry(38,8));
//        dataValues.add(new Entry(39,21));
//        dataValues.add(new Entry(40,20));
//        dataValues.add(new Entry(41,24));
//        dataValues.add(new Entry(42,2));
//        dataValues.add(new Entry(43,38));
//        dataValues.add(new Entry(44,34));
//        dataValues.add(new Entry(45,39));
//        dataValues.add(new Entry(46,40));
//        dataValues.add(new Entry(47,28));
//        dataValues.add(new Entry(48,38));
//        dataValues.add(new Entry(49.4f,50));
        }

        mpLineChart.getXAxis().setAxisMaximum(dataValues.size());
        return dataValues;
    }

    private void designChart(){
        mpLineChart.getXAxis().setAxisMaximum(dataValues.size());
        mpLineChart.setVisibleXRangeMaximum(5);
        mpLineChart.moveViewToX(Integer.MAX_VALUE); // set the left edge of the chart to x-index 1
//        mpLineChart.animateXY(5000,5000); //Animation
        mpLineChart.getAxisLeft().setDrawAxisLine(false);
        mpLineChart.getAxisRight().setDrawAxisLine(false);
        mpLineChart.setPinchZoom(true);
        mpLineChart.setDoubleTapToZoomEnabled(true);
        mpLineChart.getDescription().setEnabled(false);


        LineDataSet lineDataSet1 = new LineDataSet(dataValues,"");
        ArrayList<ILineDataSet>dataSet = new ArrayList<ILineDataSet>();


        //Edit line design and view
        lineDataSet1.setLineWidth(3);  //width of graph line
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet1.setDrawFilled(true);
        lineDataSet1.setColor(Color.parseColor("#FF248BD1"));
        lineDataSet1.setHighLightColor(Color.WHITE);
        lineDataSet1.enableDashedHighlightLine(1,1,1);
        lineDataSet1.setFillDrawable(ContextCompat.getDrawable(this, R.drawable.humidity_chart_shape));
        lineDataSet1.setDrawValues(true);  // Y values on line

        //add line to chart
        dataSet.add(lineDataSet1);
        LineData data = new LineData(dataSet);
        mpLineChart.setData(data);

        XAxis xAxis = mpLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);  // x label val increase by 1
        xAxis.setValueFormatter(new LineChartXAxisValueFormatter());
        YAxis left = mpLineChart.getAxisRight();
        left.setDrawLabels(false);
        xAxis.setLabelRotationAngle(20);

        //Reload or launch chart
        mpLineChart.invalidate();
    }
    public void loadChart(){
        mpLineChart = findViewById(R.id.humidity_line_chart);
        dataValues = new ArrayList<>();

//        dataValues.add(new Entry(0,30));
//        dataValues.add(new Entry(1,20));
//        dataValues.add(new Entry(2,0));
//        dataValues.add(new Entry(3,30));
//        dataValues.add(new Entry(4,-20));
//        dataValues.add(new Entry(5,40));
//        dataValues.add(new Entry(6,38));
//        dataValues.add(new Entry(7,60));
//        dataValues.add(new Entry(8,20));
//        dataValues.add(new Entry(9,0));
//        dataValues.add(new Entry(10,30));
//        dataValues.add(new Entry(11,-20));
//        dataValues.add(new Entry(12,40));
//        dataValues.add(new Entry(13,38));
//        dataValues.add(new Entry(14,48));
//        dataValues.add(new Entry(15,35));
//        dataValues.add(new Entry(16,58));

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        userApi.findByUsername("Aniket")
                .enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        List<SensorData> data = response.body().getSensor_data();
                        Log.v("humidity",data.toString());
//                        ArrayList<Integer>humidity = new ArrayList<>();
                        for (int i = 0 ; i<data.size();i++){
                            dataValues.add(new Entry(i,data.get(i).getHumidity()));
                        }


//                        dataValues.add(new Entry(0,30));
//                        dataValues.add(new Entry(1,20));
//                        dataValues.add(new Entry(2,0));
//                        dataValues.add(new Entry(3,30));
//                        dataValues.add(new Entry(17,45));
//                        dataValues.add(new Entry(18,68));
//                        dataValues.add(new Entry(19,45));
//                        dataValues.add(new Entry(20,68));
                        designChart();
                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {

                    }
                });
        refresh(5000);
    }
    private void refresh(int milliSeconds) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable(){
            @Override
            public void run(){
                loadChart();
            }
        };
        handler.postDelayed(runnable,milliSeconds);
    }

    public class LineChartXAxisValueFormatter extends IndexAxisValueFormatter {

        @Override
        public String getFormattedValue(float value) {

            // Convert float value to date string
            // Convert from seconds back to milliseconds to format time  to show to the user
            long emissionsMilliSince1970Time = ((long) value) * 1000;

            // Show time in local version
            Date timeMilliseconds = new Date(emissionsMilliSince1970Time);
            DateFormat dateTimeFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());

            return dateTimeFormat.format(timeMilliseconds);
        }
    }
}