package com.luciana.cantina;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Inicio extends AppCompatActivity {
    Fragment f1 = fragmento1.newInstance();
    Fragment f2 = fragmento2.newInstance();
    Fragment f3 = fragmento3.newInstance();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                {
                    getSupportActionBar().setTitle("frag1");
                    openFragment1(f1);
                    break;
                }
                case R.id.navigation_dashboard:
                {
                    getSupportActionBar().setTitle("frag2");
                    openFragment1(f2);
                    break;
                }
                case R.id.navigation_notifications:
                {
                    getSupportActionBar().setTitle("frag3");
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



        confirmFireMissiles(); //IMPORTANTE!!!!!!!!!!!!!!!!!!!!
    }
    public void confirmFireMissiles() {
        DialogFragment newFragment = new Dialogo();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }
}
