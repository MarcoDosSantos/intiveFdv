package com.example.fdvapp.fdvapi;

import com.example.fdvapp.model.RandomUserResponse;
import com.example.fdvapp.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FdvApiService {
    @GET("?")
    Call<RandomUserResponse> getUserList(@Query("page") int page, @Query("results") int results, @Query("seed") String seed);
}
