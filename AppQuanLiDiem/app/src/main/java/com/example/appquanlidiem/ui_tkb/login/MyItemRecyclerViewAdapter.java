package com.example.appquanlidiem.ui_tkb.login;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlidiem.R;
import com.example.appquanlidiem.colleges_tkb.base.CollegeFactory;
import com.example.appquanlidiem.ui_tkb.login.ItemFragment.OnListFragmentInteractionListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<String> collegeNameList;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        collegeNameList= CollegeFactory.getCollegeNameList();
        mListener = listener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_tkb, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String name=collegeNameList.get(position);
        holder.mContentView.setText(name);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {

                mListener.onListFragmentInteraction(name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return collegeNameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.content);
        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
