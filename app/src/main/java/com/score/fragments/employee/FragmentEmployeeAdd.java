package com.score.fragments.employee;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.score.models.Employee;
import com.score.rentalmanager.R;

/**
 * Created by myves.stvictor on 2015-06-12.
 */
public class FragmentEmployeeAdd extends Fragment {
    private static String TAG = FragmentEmployeeAdd.class.getSimpleName();

    private EditText mEditTextFullName;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private EditText mEditTextPhone;
    private EditText mEditTextEmail;
    private CheckBox mCheckBoxIsAdmin;
    private Button mButtonSave;
    private Button mButtonCancel;

    private Employee mEmployee;

    public FragmentEmployeeAdd() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_employee_add_layout, container,
                false);



        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
