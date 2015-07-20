package com.score.fragments.vehicle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.score.data.DatabaseHelperFake;
import com.score.models.Vehicle;

import com.score.rentalmanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myves.stvictor on 2015-06-17.
 */
public class FragmentVehicleDetail extends DialogFragment {
    private static String TAG = FragmentVehicleDetail.class.getSimpleName();

    private TextView mVehiclePlate;
    private TextView mVehicleRate;
    private TextView mVehicleOwnerName;
    private TextView mVehicleOwnerPhone;
    private TextView mVehicleOwnerEmail;
    private TextView mVehicleStatus;

    private Button mEditVehicle;
    private Button mDeleteVehicle;

    private Vehicle mVehicle;
    private List<Vehicle> mVehicles;

    public FragmentVehicleDetail(){}

    public static FragmentVehicleDetail newInstanceOFragmentVehicleDetails(long id){
        FragmentVehicleDetail fragment = new FragmentVehicleDetail();
        Bundle args = new Bundle();
        args.putLong("ID", id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_vehicle_detail_layout, container);

        mVehicles = new ArrayList<Vehicle>();
        mVehicles = DatabaseHelperFake.Vehicles();
        mVehicle = DatabaseHelperFake.getVehicle(getArguments().getLong("ID"), mVehicles);

        mVehiclePlate = (TextView) view.findViewById(R.id.textViewPlateVehicleDetail);
        mVehicleRate = (TextView) view.findViewById(R.id.textViewRateVehicleDetails);
        mVehicleOwnerName = (TextView) view.findViewById(R.id.textViewOwnerNameVehicleDetails);
        mVehicleOwnerPhone = (TextView) view.findViewById(R.id.textViewOwnerPhoneVehicleDetails);
        mVehicleOwnerEmail = (TextView) view.findViewById(R.id.textViewOwnerEmailVehicleDetails);
        mVehicleStatus = (TextView) view.findViewById(R.id.textViewVehicleStatusDetails);

        mEditVehicle = (Button) view.findViewById(R.id.buttonEditVehicule);
        mDeleteVehicle = (Button) view.findViewById(R.id.buttonDeleteVehicule);

        if(mVehicle.getModel() != null){
            getDialog().setTitle(mVehicle.getMake() + " " + mVehicle.getModel());
        }else{
            getDialog().setTitle(mVehicle.getMake());
        }

        mVehiclePlate.setText(mVehicle.getPlate());
        mVehicleRate.setText(String.valueOf(mVehicle.getRate()) + " USD");
        mVehicleOwnerName.setText(mVehicle.getOwnerName());
        mVehicleOwnerPhone.setText(mVehicle.getOwnerPhone());
        mVehicleOwnerEmail.setText(mVehicle.getOwnerEmail());

        if(mVehicle.getStatus() == 1){
            mVehicleStatus.setText("AVAILABLE");
        }else{
            mVehicleStatus.setText("NON AVAILABLE");
        }

        mEditVehicle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                Toast.makeText(getActivity(), "BTN EDIT CLICK", Toast.LENGTH_SHORT).show();
            }
        });

        mDeleteVehicle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                Toast.makeText(getActivity(), "BTN DELETE CLICK", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
