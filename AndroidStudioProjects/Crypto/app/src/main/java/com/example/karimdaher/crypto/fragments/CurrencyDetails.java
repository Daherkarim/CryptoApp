package com.example.karimdaher.crypto.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.karimdaher.crypto.R;
import com.example.karimdaher.crypto.models.Currency;
import com.example.karimdaher.crypto.services.DeviceStorageManager;


public class CurrencyDetails extends AppCompatActivity {
    private DeviceStorageManager deviceStorageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        deviceStorageManager = new DeviceStorageManager(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_detailed);
        Currency currency = deviceStorageManager.doTest();
        String attri = currency.getName();
        TextView name,attribute,att,role;
        name = findViewById(R.id.currency_name_detailed);
        attribute = findViewById(R.id.currency_attribute_detailed);
        att = findViewById(R.id.currency_att_type);
        name.setText(currency.getPrice_usd());
        att.setText(currency.getSymbol());
        attribute.setText(attri);

        String roles= currency.getRank();
        role = findViewById(R.id.currency_roles_detailed);
        role.setText(roles);
    }

}
