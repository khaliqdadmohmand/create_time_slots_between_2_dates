# create_time_slots_between_2_dates
Get all Time Slots (30 Minuts Interval) Between Start and End Time in Android Studio - Java 

create time slots  between to particular times means create time slots between start and end time.

watch video
[![Watch the video](https://i.imgur.com/f109b9bNcmM.png)](https://youtu.be/f109b9bNcmM)
```
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
    ```
