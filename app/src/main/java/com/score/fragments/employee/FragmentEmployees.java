package com.score.fragments.employee;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.score.adapters.EmployeesListRecyclerAdapter;
import com.score.data.DatabaseHelper;
import com.score.models.Employee;
import com.score.rentalmanager.R;

import java.util.List;

/**
 * Created by myves.stvictor on 2015-06-12.
 */
public class FragmentEmployees extends Fragment {
    private static String TAG = FragmentEmployees.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private EmployeesListRecyclerAdapter mEmployeesListRecyclerAdapter;
    private View mContainerView;
    private FragmentEmployeesListener mFragmentEmployeesListener;

    private Employee mSelectedEmployee = new Employee();

    private DatabaseHelper  mDatabaseHelper = new DatabaseHelper(getActivity());
    private List<Employee> mEmployees = mDatabaseHelper.getAllEmployees();

    /*public static List<Employee> getData(){
        List<Employee> data = new ArrayList<Employee>();

        return data;
    }*/

    public FragmentEmployees() { }

    public void setFragmentEmployeesListener(FragmentEmployeesListener listener) {
            this.mFragmentEmployeesListener = listener;
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
        mEmployeesListRecyclerAdapter = new EmployeesListRecyclerAdapter(getActivity(), mEmployees);

        mRecyclerView.setAdapter(mEmployeesListRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mSelectedEmployee = mEmployeesListRecyclerAdapter.getSelectedEmployee(position);
                mFragmentEmployeesListener.onEmployeesListItemSelected(view, position,
                        mSelectedEmployee);
            }

            @Override
            public void onLongClick(View view, int position) {
                mFragmentEmployeesListener.onEmployeesListItemSelected(view, position,
                        mSelectedEmployee);
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

        mFragmentEmployeesListener = (FragmentEmployeesListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface FragmentEmployeesListener{
        void onEmployeesListItemSelected(View view, int position, Employee employee);
    }
}
