package com.lecturer.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecturer.R;
import com.lecturer.model.ViewAttendanceResponse;

/**
 * Created by Vibhuti on 7/16/2018.
 */

public class ViewAttendanceAdapter extends RecyclerView.Adapter<ViewAttendanceAdapter.ViewAttendanceViewHolder> {

    private ViewAttendanceResponse mViewAttendance;
    private static Activity mContext;

    public static class ViewAttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView txtStudentName,txtPresent,txtAbsent;

        public ViewAttendanceViewHolder(View itemView){
            super(itemView);

            txtStudentName = itemView.findViewById(R.id.txt_student_name);
            txtPresent = itemView.findViewById(R.id.txt_present);
            txtAbsent = itemView.findViewById(R.id.txt_absent);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            mContext.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;

            params.width = width/4;
            params.gravity = Gravity.CENTER;
            this.txtStudentName.setLayoutParams(params);
            this.txtPresent.setLayoutParams(params);
            this.txtAbsent.setLayoutParams(params);

        }

    }

    public ViewAttendanceAdapter(Activity context,ViewAttendanceResponse viewAttendanceResponseArrayList){
        this.mViewAttendance = viewAttendanceResponseArrayList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_attendance,null);
        ViewAttendanceViewHolder viewAttendanceViewHolder = new ViewAttendanceViewHolder(view);
        return viewAttendanceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAttendanceViewHolder holder, int position) {

        holder.txtStudentName.setText(mViewAttendance.getViewAttendances().get(position).getStudent_name());
        holder.txtPresent.setText(mViewAttendance.getViewAttendances().get(position).getTotal_present());
        holder.txtAbsent.setText(mViewAttendance.getViewAttendances().get(position).getTotal_absent());
    }

    @Override
    public int getItemCount() {
        if(mViewAttendance.getViewAttendances()!=null){
            return mViewAttendance.getViewAttendances().size();
        }else
        {
            return 0;
        }
    }
}
