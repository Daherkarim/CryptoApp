package com.example.karimdaher.crypto.Authenticator;

import com.example.karimdaher.crypto.Constants;
import com.example.karimdaher.crypto.models.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class AuthenticationApiManager {

    private Retrofit retrofit;
    private AuthenticationApi authenticationApi;

    private static AuthenticationApiManager authenticationApiManager;

    private AuthenticationApiManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authenticationApi = retrofit.create(AuthenticationApi.class);
    }

    public static AuthenticationApiManager getInstance() {
        if (authenticationApiManager == null) {
            authenticationApiManager = new AuthenticationApiManager();
        }
        return authenticationApiManager;
    }

    public Call<User> login(User user) {
        return authenticationApi.login(user);
    }

    public Call<User> register(User user) {
        return authenticationApi.register(user);
    }
}
