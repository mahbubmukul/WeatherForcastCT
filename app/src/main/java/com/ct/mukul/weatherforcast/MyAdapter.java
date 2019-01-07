package com.ct.mukul.weatherforcast;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ct.mukul.weatherforcast.Data.WeatherData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by acer on 7/29/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<com.ct.mukul.weatherforcast.Data.List> moviesList;
    Context c;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView maxTemp, minTemp, date, desc;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            maxTemp = view.findViewById(R.id.maxTemp);
            minTemp = view.findViewById(R.id.minTemp);
            date = view.findViewById(R.id.date);
            desc = view.findViewById(R.id.desc);
            icon = view.findViewById(R.id.icon);
        }
    }


    public MyAdapter(Context c, List<com.ct.mukul.weatherforcast.Data.List> moviesList) {
        this.c = c;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType ==0) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_today, parent, false);
        }else{
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_weekday, parent, false);
        }

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        com.ct.mukul.weatherforcast.Data.List movie = moviesList.get(position);
        holder.maxTemp.setText(String.format("%.0f", Double.parseDouble(movie.getTemp().getMax().toString())) + (char) 0x00B0);
        holder.minTemp.setText(String.format("%.0f", Double.parseDouble(movie.getTemp().getMin().toString())) + (char) 0x00B0);
        holder.date.setText(getDateFromString( movie.getDt().toString() ));
        holder.desc.setText(movie.getWeather().get(0).getMain());
        String imageUrl = "http://www.openweathermap.org/img/w/"+movie.getWeather().get(0).getIcon()+".png";
        Glide.with(c).load(imageUrl).into(holder.icon);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 ) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static String getDateFromString(String seconds)
    {
        long sec = Long.parseLong(seconds);
// Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
// Create a calendar object that will convert the date and time value in milliseconds to date.
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+6"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sec*1000);
        DateFormat f = new SimpleDateFormat("EEEE");

        Calendar calendar1 = Calendar.getInstance();
        Date today = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar1.getTime();

        String todayName = f.format(today);
        String tomorrowdayName = f.format(tomorrow);
        String dayName = f.format(calendar.getTime());
        if(dayName.equalsIgnoreCase(todayName))
            return "Today";
        else if (dayName.equalsIgnoreCase(tomorrowdayName))
            return "Tomorrow";
        else
            return  dayName;
    }
}

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater = (LayoutInflater) c
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if(position==0)
//            convertView = inflater.inflate(R.layout.list_item_today, parent, false);
//        else
//            convertView = inflater.inflate(R.layout.list_item_weekday, parent, false);
//
//
//        TextView dateTextView = (TextView) convertView.findViewById(R.id.date);
//        TextView descTextView = (TextView) convertView.findViewById(R.id.desc);
//        TextView maxTempTextView = (TextView) convertView.findViewById(R.id.maxTemp);
//        TextView minTempTextView = (TextView) convertView.findViewById(R.id.minTemp);
//
//        ImageView iconImage = (ImageView) convertView.findViewById(R.id.icon);
//
//        dateTextView.setText(getDateFromString(dataList.get(position).getDate()));
//        descTextView.setText(dataList.get(position).getDescription());
//        maxTempTextView.setText(dataList.get(position).getMaxTemp());
//        minTempTextView.setText(dataList.get(position).getMinTemp());
//
//        String imageUrl = "http://www.openweathermap.org/img/w/"+dataList.get(position).getIcon()+".png";
//
//        Glide.with(c).load(imageUrl).into(iconImage);
//
//
//        return convertView;
//    }




