package com.example.karimdaher.crypto;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.karimdaher.crypto.fragments.CryptoUser;
import com.example.karimdaher.crypto.fragments.LoginFragment;
import com.example.karimdaher.crypto.fragments.RegisterFragment;
//import com.example.karimdaher.crypto.fragments.CryptoUser$CurrencyListAdapter$ViewHolder_ViewBinding;
import com.example.karimdaher.crypto.fragments.CryptoUser.CurrencyListAdapter;
import com.example.karimdaher.crypto.models.Currency;
import com.example.karimdaher.crypto.models.User;
import com.example.karimdaher.crypto.services.DeviceStorageManager;
import com.example.karimdaher.crypto.services.CurrencyFetcher;
import com.example.karimdaher.crypto.services.CurrencyFragmentAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener{
    private DeviceStorageManager deviceStorageManager;
    private CurrencyListAdapter mSections;
    private RecyclerView mView;
    private LoginFragment mLoginfragment;
    private RegisterFragment mRegisterFragment;
    private TextView tvFirst;
    private TextView tvSecond;
    private TextView logout;
    private TextView register_go;
    private boolean tvFirstIsCheck=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        deviceStorageManager = new DeviceStorageManager(this);

        Log.d("ADebugTag", "Value: " + deviceStorageManager.getFavorites());
        deviceStorageManager.saveUser(new User("cc","cc"));
        super.onCreate(savedInstanceState);
        if(deviceStorageManager.getUser()==null){
            setContentView(R.layout.base_fragment);
            initFragment();
            showFirstFragment();
            tvFirst= findViewById(R.id.tvFirst);
            tvSecond= findViewById(R.id.tvSecond);
            register_go = findViewById(R.id.register_text);

            tvFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firstSelected();
                    showFirstFragment();
                }
            });
            tvSecond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    secondSelected();
                    showSecondFragment();
                }
            });
//            register_go.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    secondSelected();
//                    showSecondFragment();
//                }
//            });
        }
        else{
            setContentView(R.layout.currency_fragment);
            displayCurrencies();

        }

     }
    private void firstSelected(){

        tvFirst= findViewById(R.id.tvFirst);
        tvSecond= findViewById(R.id.tvSecond);
        tvFirst.setBackgroundResource(R.drawable.bg_selected);
        tvSecond.setBackgroundResource(R.drawable.bg_un_selected);
        tvFirstIsCheck=true;
    }

    private void secondSelected(){

        tvFirst= findViewById(R.id.tvFirst);
        tvSecond= findViewById(R.id.tvSecond);
        tvSecond.setBackgroundResource(R.drawable.bg_selected);
        tvFirst.setBackgroundResource(R.drawable.bg_un_selected);
        tvFirstIsCheck=false;
    }

    private void initFragment() {
        mLoginfragment= new LoginFragment();
        mRegisterFragment= new RegisterFragment();
    }


    private void showFirstFragment() {
        setTitle("Login");
        getFragmentManager()
                .beginTransaction()
//                .setCustomAnimations(R.anim.left_in, R.anim.left_out)
                .replace(R.id.main_layout, mLoginfragment)
                .commit();
        firstSelected();
    }

    private void showSecondFragment(){

        setTitle("Register");
        getFragmentManager()
                .beginTransaction()
//                .setCustomAnimations(R.anim.right_in, R.anim.right_out, R.anim.left_in, R.anim.left_out)
                .replace(R.id.main_layout, mRegisterFragment)
                .commit();
        firstSelected();
        secondSelected();
    }

    @Override public void onBackPressed() {
        if(!tvFirstIsCheck) {
            firstSelected();
            tvFirstIsCheck=true;;
        }
        super.onBackPressed();
    }
    public void displayCurrencies(){

        setContentView(R.layout.currency_fragment);

        deviceStorageManager = DeviceStorageManager.getInstance(this);
        mView = findViewById(R.id.list);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        CurrencyFetcher client = retrofit.create(CurrencyFetcher.class);

        Call<List<Currency>> call =  client.getCurrency();
        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                List<Currency> currencies = response.body();
                TextView name;
//                addCurrencyList(currencies);
                name = (TextView) findViewById(R.id.currency_name);
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);
                mRecyclerView.setHasFixedSize(false);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getParent());
                mRecyclerView.setLayoutManager(mLayoutManager);
                RecyclerView.Adapter mAdapter = new RecyclerAdapter(currencies);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                System.exit(404);
            }
        });
    }
    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_currencyes:
//                addCurrencyList();
                break;

            case R.id.nav_signout:
                logout();
                break;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void addCurrencyList(List<Currency> currencies) {
        setTitle("Currency");
        CurrencyListAdapter adapter = new CurrencyListAdapter(currencies);
        CryptoUser fragment = new CryptoUser();
        mSections = new CurrencyListAdapter(currencies);
        ViewPager pager = new ViewPager(this);
         pager.setAdapter(new CurrencyFragmentAdapter(getSupportFragmentManager()));
        Intent intent = new Intent(this,CryptoUser.class);
        startActivity(intent);
    }

    private void logout() {
        deviceStorageManager.getInstance(this).deleteUser();
        Intent intent = new Intent(this, DeviceStorageManager.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void onRequestRegister() {

    }
}
