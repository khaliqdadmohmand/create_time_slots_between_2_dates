package com.acorreasia.datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    private EditText datePciker, timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        datePciker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        selectDate();

        timePicker.setOnClickListener(v->selectTime());

    }

    private void selectDate(){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);

                datePciker.setText(updateDate());
            }
        };

        datePciker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    private String updateDate(){
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        return  dateFormat.format(myCalendar.getTime());
    }

    //lets create time picker
    private void selectTime(){
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minut = currentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minut) {
                currentTime.set(Calendar.HOUR_OF_DAY,hour);
                currentTime.set(Calendar.MINUTE,minut);

                String myFormat = "HH:mm:ss";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                timePicker.setText(dateFormat.format(currentTime.getTime()));

            }
        },hour,minut,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}