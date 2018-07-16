package com.lecturer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lecturer.R;
import com.lecturer.model.Student;

import java.util.ArrayList;

/**
 * Created by Vibhuti on 7/16/2018.
 */

public class ViewAttendanceAdapter extends RecyclerView.Adapter<ViewAttendanceAdapter.ViewAttendanceViewHolder> {

    private ArrayList<Student> mStudentList;

    public static class ViewAttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView txtStudentName,txtPresent,txtAbsent;

        public ViewAttendanceViewHolder(View itemView){
            super(itemView);

            txtStudentName = itemView.findViewById(R.id.txt_student_name);
            txtPresent = itemView.findViewById(R.id.txt_present);
            txtAbsent = itemView.findViewById(R.id.txt_absent);

        }

    }

    public ViewAttendanceAdapter(ArrayList<Student> studentArrayList){
        this.mStudentList = studentArrayList;
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

        holder.txtStudentName.setText(mStudentList.get(position).getStudent_name());
        holder.txtPresent.setText(mStudentList.get(position).getPresent()+"");
        holder.txtAbsent.setText(mStudentList.get(position).getAbsent()+"");
    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }
}
