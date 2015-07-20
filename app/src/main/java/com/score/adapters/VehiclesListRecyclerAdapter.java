package com.score.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import com.score.models.Vehicle;
import com.score.rentalmanager.R;

/**
 * Created by myves.stvictor on 2015-06-12.
 */
public class VehiclesListRecyclerAdapter extends RecyclerView.Adapter<VehiclesListRecyclerAdapter.MyViewHolder> {
    List<Vehicle> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public VehiclesListRecyclerAdapter(Context context, List<Vehicle> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public Vehicle getSelectedVehicle(int position){
        return this.data.get(position);
    }

    public void updateVehiclesList(List<Vehicle> list){
        data = list;
        notifyDataSetChanged();
    }

    public void addVehicle(int position, Vehicle vehicle){
        data.add(position, vehicle);
        notifyItemInserted(position);
    }

    public void deleteVehicle(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_row_veh_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Vehicle current = data.get(position);
        holder.make.setText(current.getMake());
        holder.model.setText(current.getModel());
        holder.plate.setText(current.getPlate());
        holder.rate.setText(String.valueOf(current.getRate()) + " USD");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView make;
        TextView model;
        TextView plate;
        TextView rate;

        public MyViewHolder(View itemView) {
            super(itemView);
            make = (TextView) itemView.findViewById(R.id.textViewMake);
            model = (TextView) itemView.findViewById(R.id.textViewModel);
            plate = (TextView) itemView.findViewById(R.id.textViewPlate);
            rate = (TextView) itemView.findViewById(R.id.textViewRate);
        }
    }
}
