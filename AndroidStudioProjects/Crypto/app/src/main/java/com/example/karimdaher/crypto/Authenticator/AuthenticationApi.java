package com.example.karimdaher.crypto.Authenticator;

import com.example.karimdaher.crypto.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface AuthenticationApi {

    @POST("register")
    Call<User> register(@Body User user);

    @POST("login")
    Call<User> login(@Body User user);
}
