package com.odintsov.mapsapp.network;

import com.odintsov.mapsapp.Route;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MapsService {

    @POST("/coordinates")
    Call<Route> sendRouteInfo(@Query("route") String title,
                              @Query("longitude") double longitude,
                              @Query("latitude") double latitude,
                              @Query("angle") float angle,
                              @Query("distance") double distance);

}
