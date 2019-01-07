package com.ct.mukul.weatherforcast;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ct.mukul.weatherforcast.Data.WeatherData;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String url ="http://api.openweathermap.org/data/2.5/forecast/daily?APPID=e361c6ae553dc2faf9364cd13b1bc038&q=Dhaka&mode=json&units=metric&cnt=7";
    String data;
    List<WeatherData> dataList = new ArrayList<>();
    ProgressDialog pd;
    DatabaseHelp databaseHelp;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new FetchWeatherData().execute();
    }


    String getDataFromUrl(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url(url)
                    .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public class FetchWeatherData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setTitle("Loading...");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                data = getDataFromUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            WeatherData weatherData = new Gson().fromJson(data, WeatherData.class);
            mAdapter = new MyAdapter(MainActivity.this, weatherData.getList());
            recyclerView.setAdapter(mAdapter);
            pd.dismiss();
        }
    }



}
