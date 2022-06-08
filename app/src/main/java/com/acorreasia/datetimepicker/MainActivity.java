package com.acorreasia.datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText startDate, endDate, startTime, endTime, interval;
    ListView listView;
    List<String> slots;
    Button generate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startDate = findViewById(R.id.start_date);
        endDate = findViewById(R.id.end_date);
        startTime = findViewById(R.id.start_time);
        endTime = findViewById(R.id.end_time);
        interval =findViewById(R.id.interval);
        listView = findViewById(R.id.list);
        generate = findViewById(R.id.generate);

        generate.setOnClickListener(v->getSlots());

    }

    private void getSlots() {
        slots = new ArrayList<>();
        try {
        String format = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date dateObj2 = null;

        Date dateObj1 = sdf.parse(startDate.getText().toString()+" "+startTime.getText().toString());
        dateObj2 = sdf.parse(endDate.getText().toString()+" "+endTime.getText().toString());

        long dif = dateObj1.getTime();

        while (dif< dateObj2.getTime()){

            Date slot = new Date(dif);
            String sformat = "HH:mm a";
            SimpleDateFormat dateFormat = new SimpleDateFormat(sformat, Locale.US);

            slots.add(dateFormat.format(slot));


            dif += Integer.parseInt(String.valueOf(interval.getText().toString())) * 60000;
        }

            ArrayAdapter<String> arr = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, slots);
            listView.setAdapter(arr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}