package com.odintsov.mapsapp.network;

import android.content.Context;
import android.util.Log;

import com.odintsov.mapsapp.Route;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GpsController {

    private static final String IP = "http://46.101.170.89:9000";

    private Context context;
    private final Retrofit retrofit;

    public GpsController(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(IP)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void sendCoordinates(double longitude, double latitude, float angle, double distance) {
        MapsService mapsService = retrofit.create(MapsService.class);
        mapsService.sendRouteInfo("Route", longitude, latitude, angle, distance).enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                Route route = response.body();
                Log.d("RouteService", "Route: " + route);
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                Log.d("RouteService", "Error occurred");
                t.printStackTrace();
            }
        });
    }

}
