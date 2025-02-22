package com.luciana.cantina;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {

    Fragment f1 = Mensal_1.newInstance();
    Fragment f2 = Checkin_2.newInstance();
    Fragment f3 = Extrato_3.newInstance();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                {
                    getSupportActionBar().setTitle("Relatório Mensal");
                    openFragment1(f1);
                    break;
                }
                case R.id.navigation_dashboard:
                {
                    getSupportActionBar().setTitle("Check-in");
                    openFragment1(f2);
                    break;
                }
                case R.id.navigation_notifications:
                {
                    getSupportActionBar().setTitle("Relatório Geral");
                    openFragment1(f3);
                    break;
                }
            }
            return false;
        }
    };
    private void openFragment1(Fragment fragment) {
        if(getSupportFragmentManager().getBackStackEntryCount()>=2){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            getSupportFragmentManager().popBackStackImmediate();
            transaction.replace(R.id.container1, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container1, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        openFragment1(f2);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//---------------------------------------------------------------------------------------gabrield
//------------------------------------------------------------------gabriel

        confirmBox(); //IMPORTANTE!!!!!!!!!!!!!!!!!!!!
    }
    public void confirmBox() {
        DialogFragment newFragment = new Dialogo();
        newFragment.show(getSupportFragmentManager(), "box creation");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
