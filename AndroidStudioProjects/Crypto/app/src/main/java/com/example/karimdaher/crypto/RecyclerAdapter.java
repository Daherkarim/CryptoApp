package com.example.karimdaher.crypto;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karimdaher.crypto.Favorites.FavoritesApi;
import com.example.karimdaher.crypto.fragments.CurrencyDetails;
import com.example.karimdaher.crypto.models.Currency;
import com.example.karimdaher.crypto.services.CurrencyFetcher;
import com.example.karimdaher.crypto.services.DeviceStorageManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class
RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "recycler";
    private static List<Currency> currencies;
    private RecyclerView.ViewHolder holder;
    private int position;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView currencyName;
        public TextView currecnySymbol;
        public TextView currencyPrice;
        public ImageView favorites;
        private boolean faved;

        public void switchFaved(){
            faved = !faved;
        }
        public ViewHolder(View x) {
            super(x);
            currencyName = x.findViewById(R.id.currency_name);
            currecnySymbol = x.findViewById(R.id.currency_symbol);
            currencyPrice = x.findViewById(R.id.currency_price);
            favorites = x.findViewById(R.id.favorites);
        }
    }
    public RecyclerAdapter(List<Currency> currencies) {
        this.currencies = currencies;
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    DeviceStorageManager dsm = DeviceStorageManager.getInstance();
    dsm.Favorite(currencies.get(20));
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    private void star(ViewHolder holder){

        Picasso.get().load(android.R.drawable.btn_star_big_off).into(holder.favorites);
    }
    private void unstar(ViewHolder holder){

        Picasso.get().load(android.R.drawable.btn_star_big_on).into(holder.favorites);
    }
    public static List<String> getFavorites(){

        DeviceStorageManager dsm = DeviceStorageManager.getInstance();
        String subject = dsm.getFavorites();
        ArrayList<String> list = new ArrayList<String>();
        if(subject==null){
            return list;
        }
        int i=0; int j=0;
        if(subject!=null)
        while(j<subject.length()){
            if(j==-1)
                return list;
            j = subject.indexOf("*",j+1);
            if(j>subject.length()||j<0){
                return list
                        ;
            }

            Log.d("Adapter","Value of string:"+subject.substring(i,j));
            list.add(subject.substring(i,j));
            j++;
            i=j;
        }
        return list;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Currency currency = currencies.get(position);
        holder.currencyName.setText(currencies.get(position).getName());
        holder.currecnySymbol.setText(currencies.get(position).getSymbol());
        holder.currencyPrice.setText("$"+currencies.get(position).getPrice_usd()+"");
        List<String> favorites_list = getFavorites();

        if(isFoundInList(favorites_list,currency)) {
            Log.d("Adapter","Found "+currency.getName());
            unstar(holder);
            holder.faved=true;
        }
        holder.favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoritesApi api = FavoritesApi.getInstance();
                api.makeFave(currency);
                if(holder.faved)
                    star(holder);
                else
                    unstar(holder);
                holder.switchFaved();
            }
        });

//        Log.d(TAG, "onBindViewHolder: called.");
    }

    private boolean isFoundInList(List<String> favorites_list, Currency currency) {
        String target = currency.getName();
        Log.d("Recycler","Value of size "+favorites_list.size());
        int i =0;
        if(favorites_list==null)
            return false;
        while(favorites_list.get(i)!=null&&i<favorites_list.size()-1){
            Log.d("Recycler","Value of strings "+favorites_list.get(i)+" - "+target);
            if(favorites_list.get(i).equalsIgnoreCase(target))
                return true;
            i++;}

    return false;}

    @Override
    public int getItemCount() {
        return currencies.size();
    }


}