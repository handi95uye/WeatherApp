package com.example.haresh.retrofitlearning.data.remote;

import com.example.haresh.retrofitlearning.data.model.Weather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Haresh on 06-04-2016.
 */
public interface WeatherApi {

    String BASE_URL = "https://query.yahooapis.com/v1/public/";
    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22roorkee%2C%20india%22)%20and%20u%3D'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<Weather> getWeather();

    class Factory {

        private static WeatherApi service;
        public static WeatherApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(WeatherApi.class);
                return service;
            }
            else {
                return service;
            }
        }
    }
}
