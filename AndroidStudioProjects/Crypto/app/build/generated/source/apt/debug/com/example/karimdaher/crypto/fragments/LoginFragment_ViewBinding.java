// Generated code from Butter Knife. Do not modify!
package com.example.karimdaher.crypto.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.karimdaher.crypto.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginFragment_ViewBinding implements Unbinder {
  private LoginFragment target;

  private View view2131230829;

  @UiThread
  public LoginFragment_ViewBinding(final LoginFragment target, View source) {
    this.target = target;

    View view;
    target.emailHolder = Utils.findRequiredViewAsType(source, R.id.email_holder, "field 'emailHolder'", TextInputLayout.class);
    target.emailEditText = Utils.findRequiredViewAsType(source, R.id.email, "field 'emailEditText'", EditText.class);
    target.passwordHolder = Utils.findRequiredViewAsType(source, R.id.password_holder, "field 'passwordHolder'", TextInputLayout.class);
    target.passwordEditText = Utils.findRequiredViewAsType(source, R.id.password, "field 'passwordEditText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.login_frag_button, "field 'loginButton' and method 'attemptLogin'");
    target.loginButton = Utils.castView(view, R.id.login_frag_button, "field 'loginButton'", Button.class);
    view2131230829 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.attemptLogin();
      }
    });
    target.registerTextView = Utils.findRequiredViewAsType(source, R.id.register_text, "field 'registerTextView'", TextView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar_login, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.emailHolder = null;
    target.emailEditText = null;
    target.passwordHolder = null;
    target.passwordEditText = null;
    target.loginButton = null;
    target.registerTextView = null;
    target.progressBar = null;

    view2131230829.setOnClickListener(null);
    view2131230829 = null;
  }
}
