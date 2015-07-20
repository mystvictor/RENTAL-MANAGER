package com.myves.score.rentalmanager.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.score.fragments.customer.FragmentCustomers;
import com.score.fragments.FragmentDashboard;
import com.score.fragments.FragmentDrawer;
import com.score.fragments.rental.FragmentRentals;
import com.score.fragments.vehicle.FragmentVehicleDetail;
import com.score.fragments.vehicle.FragmentVehicles;
import com.score.models.Vehicle;
import com.score.rentalmanager.R;

import java.util.Stack;

public class MainActivity extends ActionBarActivity implements FragmentDrawer
        .FragmentDrawerListener, FragmentVehicles.FragmentVehiclesListener {
    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer mDrawerFragment;

    private FragmentDashboard mDashboardFragment;
    private FragmentRentals mRentalsFragment;
    private FragmentCustomers mCustomersFragment;

    private FragmentVehicles mVehiclesFragment;
    private FragmentVehicleDetail mVehicleDetailsFragment;
    private Vehicle mVehicle;

    private Fragment mCurrentFragment;
    private Stack<Fragment> mStackFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDrawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.setDrawerListener(this);



        mStackFragments = new Stack<Fragment>();

        displayView(0);
    }

    private void replaceFragment(Fragment fragment){
        if(fragment.isVisible()){
            return;
        }else {
            //String title = getString(R.string.toolbar_title);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_body, fragment)
                    .addToBackStack(null).commit();
            mCurrentFragment = fragment;
            //getSupportActionBar().setTitle(title);
        }
    }

    private void displayView(int position) {
        mCurrentFragment = null;
        String title = getString(R.string.toolbar_title);
        switch (position){
            case 0:
                //Toast.makeText(this, "DASHBOARD CLICK", Toast.LENGTH_SHORT).show();
                title = getString(R.string.title_dashboard);
                mCurrentFragment = new FragmentVehicles();

                break;
            case 1:
                //Toast.makeText(this, "RENTALS CLICK", Toast.LENGTH_SHORT).show();
                title = getString(R.string.title_rentals);
                mCurrentFragment = new FragmentCustomers();
                break;
            case 2:
                //Toast.makeText(this, "CUSTOMERS CLICK", Toast.LENGTH_SHORT).show();
                title = getString(R.string.title_customers);
                mCurrentFragment = new FragmentRentals();
                break;
            default:
                //Toast.makeText(this, "VEHICLES CLICK", Toast.LENGTH_SHORT).show();
                title = getString(R.string.title_vehicles);
                mCurrentFragment = new FragmentDashboard();
                break;
        }

        replaceFragment(mCurrentFragment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    @Override
    public void onVehiclesListItemSelected(View view, int position, Vehicle vehicle){
        FragmentManager fragmentManager = getSupportFragmentManager();

        mVehicleDetailsFragment = FragmentVehicleDetail
                .newInstanceOFragmentVehicleDetails(vehicle.getId());

        mVehicleDetailsFragment.show(fragmentManager, mVehicleDetailsFragment.getTag());

        /*getSupportFragmentManager().beginTransaction()
                .show(mVehicleDetailsFragment)
                .commit();*/

        Toast.makeText(this, vehicle.getPlate() + " CLICKED", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_admin:
                Toast.makeText(this, "ADMIN CLICK", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_settings:
                Toast.makeText(this, "SETTINGS CLICK", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
