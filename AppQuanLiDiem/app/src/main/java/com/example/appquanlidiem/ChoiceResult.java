package com.example.appquanlidiem;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ChoiceResult extends DialogFragment {
    int position1 = 0;
    public interface SingleChoiceListenner{
        void onPostiveButtonClicked1(String[] list, int position1);
        void oNegativeButtonClicked1();
    }
    SingleChoiceListenner mListenner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListenner = (SingleChoiceListenner) context;

        } catch (Exception e) {
            throw  new ClassCastException(getActivity().toString()+ "Duoc tien hanh");
        }
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] list = getActivity().getResources().getStringArray(R.array.choice_result);
        builder.setTitle("").setSingleChoiceItems(list, position1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        position1 = i;

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListenner.onPostiveButtonClicked1(list,position1);
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListenner.oNegativeButtonClicked1();
                    }
                });
        return builder.create();
    }
}

