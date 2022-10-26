// Generated by view binder compiler. Do not edit!
package com.example.appquanlidiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appquanlidiem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEditTkbBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final CardView cvEdit1;

  @NonNull
  public final CardView cvEdit2;

  @NonNull
  public final EditText etClassRoom;

  @NonNull
  public final EditText etTeacher;

  @NonNull
  public final ImageView ivBgEdit;

  @NonNull
  public final EditText nameEditText;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView tvClassNum;

  @NonNull
  public final TextView tvWeekOfTerm;

  @NonNull
  public final View view2;

  @NonNull
  public final View view3;

  @NonNull
  public final View view5;

  private ActivityEditTkbBinding(@NonNull FrameLayout rootView, @NonNull CardView cvEdit1,
      @NonNull CardView cvEdit2, @NonNull EditText etClassRoom, @NonNull EditText etTeacher,
      @NonNull ImageView ivBgEdit, @NonNull EditText nameEditText, @NonNull TextView textView2,
      @NonNull TextView textView4, @NonNull TextView textView5, @NonNull TextView textView6,
      @NonNull TextView tvClassNum, @NonNull TextView tvWeekOfTerm, @NonNull View view2,
      @NonNull View view3, @NonNull View view5) {
    this.rootView = rootView;
    this.cvEdit1 = cvEdit1;
    this.cvEdit2 = cvEdit2;
    this.etClassRoom = etClassRoom;
    this.etTeacher = etTeacher;
    this.ivBgEdit = ivBgEdit;
    this.nameEditText = nameEditText;
    this.textView2 = textView2;
    this.textView4 = textView4;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.tvClassNum = tvClassNum;
    this.tvWeekOfTerm = tvWeekOfTerm;
    this.view2 = view2;
    this.view3 = view3;
    this.view5 = view5;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEditTkbBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEditTkbBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_edit_tkb, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEditTkbBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cv_edit_1;
      CardView cvEdit1 = ViewBindings.findChildViewById(rootView, id);
      if (cvEdit1 == null) {
        break missingId;
      }

      id = R.id.cv_edit_2;
      CardView cvEdit2 = ViewBindings.findChildViewById(rootView, id);
      if (cvEdit2 == null) {
        break missingId;
      }

      id = R.id.et_class_room;
      EditText etClassRoom = ViewBindings.findChildViewById(rootView, id);
      if (etClassRoom == null) {
        break missingId;
      }

      id = R.id.et_teacher;
      EditText etTeacher = ViewBindings.findChildViewById(rootView, id);
      if (etTeacher == null) {
        break missingId;
      }

      id = R.id.iv_bg_edit;
      ImageView ivBgEdit = ViewBindings.findChildViewById(rootView, id);
      if (ivBgEdit == null) {
        break missingId;
      }

      id = R.id.name_editText;
      EditText nameEditText = ViewBindings.findChildViewById(rootView, id);
      if (nameEditText == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.tv_class_num;
      TextView tvClassNum = ViewBindings.findChildViewById(rootView, id);
      if (tvClassNum == null) {
        break missingId;
      }

      id = R.id.tv_week_of_term;
      TextView tvWeekOfTerm = ViewBindings.findChildViewById(rootView, id);
      if (tvWeekOfTerm == null) {
        break missingId;
      }

      id = R.id.view2;
      View view2 = ViewBindings.findChildViewById(rootView, id);
      if (view2 == null) {
        break missingId;
      }

      id = R.id.view3;
      View view3 = ViewBindings.findChildViewById(rootView, id);
      if (view3 == null) {
        break missingId;
      }

      id = R.id.view5;
      View view5 = ViewBindings.findChildViewById(rootView, id);
      if (view5 == null) {
        break missingId;
      }

      return new ActivityEditTkbBinding((FrameLayout) rootView, cvEdit1, cvEdit2, etClassRoom,
          etTeacher, ivBgEdit, nameEditText, textView2, textView4, textView5, textView6, tvClassNum,
          tvWeekOfTerm, view2, view3, view5);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
