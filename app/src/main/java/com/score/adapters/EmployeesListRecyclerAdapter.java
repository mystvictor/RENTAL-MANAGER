package com.score.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.score.models.Employee;
import com.score.models.Vehicle;
import com.score.rentalmanager.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by myves.stvictor on 2015-06-12.
 */
public class EmployeesListRecyclerAdapter extends RecyclerView.Adapter<EmployeesListRecyclerAdapter.MyViewHolder> {
    List<Employee> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public EmployeesListRecyclerAdapter(Context context, List<Employee> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public Employee getSelectedEmployee(int position){
        return this.data.get(position);
    }

    public void updateEmployeesList(List<Employee> list){
        data = list;
        notifyDataSetChanged();
    }

    public void addEmployee(int position, Employee employee){
        data.add(position, employee);
        notifyItemInserted(position);
    }

    public void deleteEmployee(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_row_emp_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Employee current = data.get(position);

        holder.fullName.setText(current.getFullName());
        holder.username.setText(current.getUsername());

        if(current.getProfile() == 1){
            holder.isAdmin.isChecked();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fullName;
        TextView username;
        CheckBox isAdmin;

        public MyViewHolder(View itemView) {
            super(itemView);
            fullName = (TextView) itemView.findViewById(R.id.textViewEmpFullName);
            username = (TextView) itemView.findViewById(R.id.textViewEmpUsername);
            isAdmin = (CheckBox) itemView.findViewById(R.id.checkBoxEmp);
        }
    }
}
