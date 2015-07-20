package com.score.fragments.vehicle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.score.adapters.VehiclesListRecyclerAdapter;
import com.score.data.DatabaseHelperFake;
import com.score.models.Vehicle;
import com.score.rentalmanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myves.stvictor on 2015-06-12.
 */
public class FragmentVehicles extends Fragment {
    private static String TAG = FragmentVehicles.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private VehiclesListRecyclerAdapter mVehiclesListRecyclerAdapter;
    private View mContainerView;
    private FragmentVehiclesListener mFragmentVehiclesListener;

    private Vehicle mSelectedVehicle = new Vehicle();

    public static List<Vehicle> getData(){
        List<Vehicle> data = new ArrayList<Vehicle>();
        data = DatabaseHelperFake.Vehicles();
        return data;
    }

    public FragmentVehicles() { }

    public void setFragmentVehiclesListener(FragmentVehiclesListener listener) {
            this.mFragmentVehiclesListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_vehicles_layout, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewVehiclesList);
        mVehiclesListRecyclerAdapter = new VehiclesListRecyclerAdapter(getActivity(), getData());

        mRecyclerView.setAdapter(mVehiclesListRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mSelectedVehicle = mVehiclesListRecyclerAdapter.getSelectedVehicle(position);
                mFragmentVehiclesListener.onVehiclesListItemSelected(view, position,
                        mSelectedVehicle);
            }

            @Override
            public void onLongClick(View view, int position) {
                mFragmentVehiclesListener.onVehiclesListItemSelected(view, position,
                        mSelectedVehicle);
           }
        }));
        // Inflate the layout for this fragment
        return rootView;
    }

    public void setUp(){

    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView,
                                     final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context,
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }

                        @Override
                        public void onLongPress(MotionEvent e) {
                            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                            if (child != null && clickListener != null) {
                                clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                            }
                        }
                    });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mFragmentVehiclesListener = (FragmentVehiclesListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface FragmentVehiclesListener{
        void onVehiclesListItemSelected(View view, int position, Vehicle vehicle);
    }
}
