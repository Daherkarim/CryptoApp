package com.example.karimdaher.crypto.Favorites;


import com.example.karimdaher.crypto.models.Currency;
import com.example.karimdaher.crypto.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface FavoritesApiInterface {

    @GET("favorites")
    Call<Currency> getFavorites(@Body User user);

    @POST("favorties/{currency_name}")
    Call<Currency> makeFave(@Path(value = "currency_name", encoded = false) String name,@Body User user);

}
