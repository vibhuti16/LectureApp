package com.lecturer.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecturer.R;
import com.lecturer.model.StudentListResponse;

/**
 * Created by Vibhuti on 7/15/2018.
 */

public class TakeAttendanceAdapter extends RecyclerView.Adapter<TakeAttendanceAdapter.TakeAttendanceViewHolder> {

    private StudentListResponse  studentListResponses;
    private static Activity mContext;

    public TakeAttendanceAdapter(Activity context,StudentListResponse studentListResponse){
        this.studentListResponses = studentListResponse;
        mContext = context;
    }

    public static class TakeAttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView txtStudentName;
        CheckBox chkPresent,chkAbsent;

        public TakeAttendanceViewHolder(View itemView){
            super(itemView);

            this.txtStudentName = itemView.findViewById(R.id.txt_customer_name);
            this.chkPresent = itemView.findViewById(R.id.chk_present);
            this.chkAbsent = itemView.findViewById(R.id.chk_absent);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            mContext.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;

            params.width = width/3;
            params.gravity = Gravity.CENTER;
            this.txtStudentName.setLayoutParams(params);
            this.chkPresent.setLayoutParams(params);
            this.chkAbsent.setLayoutParams(params);

        }

    }

    @NonNull
    @Override
    public TakeAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_take_attendance,null);

        TakeAttendanceViewHolder takeAttendanceViewHolder = new TakeAttendanceViewHolder(view);
        return takeAttendanceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TakeAttendanceViewHolder holder, int position) {

        holder.txtStudentName.setText(studentListResponses.getStudent().get(position).getStudent_name());
        holder.chkPresent.setTag(R.string.presentees,position);
        holder.chkAbsent.setTag(R.string.absentees,position);
        holder.chkPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int tag = (Integer) buttonView.getTag(R.string.presentees);
                if(isChecked){
                    studentListResponses.getStudent().get(tag).setPresent((studentListResponses.getStudent().get(tag).getPresent()+1));
                    holder.chkAbsent.setChecked(false);
                }else
                {
                    studentListResponses.getStudent().get(tag).setPresent((studentListResponses.getStudent().get(tag).getPresent()-1));
                }



            }
        });
        holder.chkAbsent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int tag = (Integer) buttonView.getTag(R.string.absentees);
                if(isChecked){
                    studentListResponses.getStudent().get(tag).setAbsent((studentListResponses.getStudent().get(tag).getAbsent()+1));
                    holder.chkPresent.setChecked(false);
                }else
                {
                    studentListResponses.getStudent().get(tag).setAbsent((studentListResponses.getStudent().get(tag).getAbsent()-1));
                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return studentListResponses.getStudent().size();
    }

    public StudentListResponse getStudentListResponses(){
        return studentListResponses;
    }
}
