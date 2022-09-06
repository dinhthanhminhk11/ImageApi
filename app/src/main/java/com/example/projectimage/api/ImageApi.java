package com.example.projectimage.api;

import com.example.projectimage.model.Image;
import com.example.projectimage.model.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImageApi {
    @GET("v1/images/search")
    Call<List<Image>> getImage(
            @Query("limit") int limit,
            @Query("breed_ids") String breed_ids,
            @Query("api_key") String api_key
    );

    @GET("v1/images/{id}")
    Call<Root> getRoot(@Path("id") String id);

}
