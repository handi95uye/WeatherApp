package com.example.haresh.retrofitlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.haresh.retrofitlearning.data.model.Weather;
import com.example.haresh.retrofitlearning.data.remote.WeatherApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainAcitvity";
    Button refreshButton;
    TextView city, date, temperature, conditions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshButton = (Button) findViewById(R.id.refreshButton);
        city = (TextView) findViewById(R.id.city);
        date = (TextView) findViewById(R.id.date);
        temperature = (TextView) findViewById(R.id.temperature);
        conditions = (TextView) findViewById(R.id.conditions);


        refreshButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change();
                }
            }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        change();
    }

    private void change() {
        WeatherApi.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                System.out.println("cek json cuaca : " + new Gson().toJsonTree(response));
                temperature.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp());
                city.setText(response.body().getQuery().getResults().getChannel().getLocation().getCity());
                date.setText(response.body().getQuery().getResults().getChannel().getLastBuildDate());
                conditions.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getText());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG, t.getMessage());

            }
        });
    }
}
