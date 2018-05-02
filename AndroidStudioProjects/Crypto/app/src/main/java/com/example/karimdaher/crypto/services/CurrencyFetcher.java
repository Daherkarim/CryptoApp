package com.example.karimdaher.crypto.services;

import com.example.karimdaher.crypto.models.Currency;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface CurrencyFetcher {

    @GET("ticker/")
    Call<List<Currency>> getCurrency();
}
