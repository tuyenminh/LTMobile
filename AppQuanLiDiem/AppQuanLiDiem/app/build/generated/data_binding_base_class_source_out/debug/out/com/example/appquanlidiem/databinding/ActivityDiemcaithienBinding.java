// Generated by view binder compiler. Do not edit!
package com.example.appquanlidiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appquanlidiem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDiemcaithienBinding implements ViewBinding {
  @NonNull
  private final TableLayout rootView;

  @NonNull
  public final Button buttonddk;

  @NonNull
  public final EditText diemdukien1;

  @NonNull
  public final Button mtButtonddk;

  @NonNull
  public final TextView mtChonmh2;

  @NonNull
  public final TextView mtDht;

  @NonNull
  public final Button mtKq2;

  @NonNull
  public final EditText tbmon;

  @NonNull
  public final EditText tk;

  private ActivityDiemcaithienBinding(@NonNull TableLayout rootView, @NonNull Button buttonddk,
      @NonNull EditText diemdukien1, @NonNull Button mtButtonddk, @NonNull TextView mtChonmh2,
      @NonNull TextView mtDht, @NonNull Button mtKq2, @NonNull EditText tbmon,
      @NonNull EditText tk) {
    this.rootView = rootView;
    this.buttonddk = buttonddk;
    this.diemdukien1 = diemdukien1;
    this.mtButtonddk = mtButtonddk;
    this.mtChonmh2 = mtChonmh2;
    this.mtDht = mtDht;
    this.mtKq2 = mtKq2;
    this.tbmon = tbmon;
    this.tk = tk;
  }

  @Override
  @NonNull
  public TableLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDiemcaithienBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDiemcaithienBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_diemcaithien, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDiemcaithienBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonddk;
      Button buttonddk = ViewBindings.findChildViewById(rootView, id);
      if (buttonddk == null) {
        break missingId;
      }

      id = R.id.diemdukien1;
      EditText diemdukien1 = ViewBindings.findChildViewById(rootView, id);
      if (diemdukien1 == null) {
        break missingId;
      }

      id = R.id.mt_buttonddk;
      Button mtButtonddk = ViewBindings.findChildViewById(rootView, id);
      if (mtButtonddk == null) {
        break missingId;
      }

      id = R.id.mt_chonmh2;
      TextView mtChonmh2 = ViewBindings.findChildViewById(rootView, id);
      if (mtChonmh2 == null) {
        break missingId;
      }

      id = R.id.mt_dht;
      TextView mtDht = ViewBindings.findChildViewById(rootView, id);
      if (mtDht == null) {
        break missingId;
      }

      id = R.id.mt_kq2;
      Button mtKq2 = ViewBindings.findChildViewById(rootView, id);
      if (mtKq2 == null) {
        break missingId;
      }

      id = R.id.tbmon;
      EditText tbmon = ViewBindings.findChildViewById(rootView, id);
      if (tbmon == null) {
        break missingId;
      }

      id = R.id.tk;
      EditText tk = ViewBindings.findChildViewById(rootView, id);
      if (tk == null) {
        break missingId;
      }

      return new ActivityDiemcaithienBinding((TableLayout) rootView, buttonddk, diemdukien1,
          mtButtonddk, mtChonmh2, mtDht, mtKq2, tbmon, tk);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
