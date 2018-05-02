package com.example.karimdaher.crypto.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.karimdaher.crypto.R;
import com.example.karimdaher.crypto.models.Currency;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CryptoUser extends android.support.v4.app.Fragment{

    private RecyclerView currency_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_fragment, container, false);
        currency_list = (RecyclerView) view.findViewById(R.id.list);

        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public static class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.ViewHolder> {

        private List<Currency> currencies;

        public CurrencyListAdapter(List<Currency> currencies) {
            this.currencies = currencies;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.currency_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Currency currency = currencies.get(position);
            holder.currencyNameTextView.setText(currency.getName());
        }

        @Override
        public int getItemCount() {
            return currencies.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.currency_name)
            TextView currencyNameTextView;

            @BindView(R.id.currency_symbol)
            TextView currencyAttTextView;

            @BindView(R.id.currency_price)
            TextView currencyAttackTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

}
