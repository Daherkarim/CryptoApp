package com.example.karimdaher.crypto.Favorites;

import android.view.View;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

import com.example.karimdaher.crypto.Authenticator.AuthenticationApi;
import com.example.karimdaher.crypto.Constants;
import com.example.karimdaher.crypto.models.Currency;
import com.example.karimdaher.crypto.models.User;
import com.example.karimdaher.crypto.services.DeviceStorageManager;

/**
 * Created by User on 01/05/2018.
 */

public class FavoritesApi {

    private Retrofit retrofit;
    private FavoritesApiInterface favoritesApiInterface;
    private DeviceStorageManager deviceStorageManager;
    private static FavoritesApi favoritesApi;

    private FavoritesApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        favoritesApiInterface = retrofit.create(FavoritesApiInterface.class);
        deviceStorageManager = DeviceStorageManager.getInstance();
    }

    public static FavoritesApi getInstance() {
        if (favoritesApi == null) {
            favoritesApi = new FavoritesApi();
        }
        return favoritesApi;
    }

    public Call<Currency> makeFave(Currency currency) {
        deviceStorageManager.Favorite(currency);
        return favoritesApiInterface.makeFave(currency.getName(),deviceStorageManager.getUser());
    }

    public Call<Currency> getFave() {
        return favoritesApiInterface.getFavorites(deviceStorageManager.getUser());
    }
}
