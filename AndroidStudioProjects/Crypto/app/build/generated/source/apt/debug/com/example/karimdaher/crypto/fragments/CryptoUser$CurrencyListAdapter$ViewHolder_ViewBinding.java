// Generated code from Butter Knife. Do not modify!
package com.example.karimdaher.crypto.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.karimdaher.crypto.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CryptoUser$CurrencyListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private CryptoUser.CurrencyListAdapter.ViewHolder target;

  @UiThread
  public CryptoUser$CurrencyListAdapter$ViewHolder_ViewBinding(CryptoUser.CurrencyListAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.currencyNameTextView = Utils.findRequiredViewAsType(source, R.id.currency_name, "field 'currencyNameTextView'", TextView.class);
    target.currencyAttTextView = Utils.findRequiredViewAsType(source, R.id.currency_symbol, "field 'currencyAttTextView'", TextView.class);
    target.currencyAttackTextView = Utils.findRequiredViewAsType(source, R.id.currency_price, "field 'currencyAttackTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CryptoUser.CurrencyListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.currencyNameTextView = null;
    target.currencyAttTextView = null;
    target.currencyAttackTextView = null;
  }
}
