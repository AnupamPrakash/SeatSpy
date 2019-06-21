package com.example.seatspy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

   BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     setupBottonNavigation();
        if(savedInstanceState==null){
            loadSearchFragment();
        }
    }

    private void setupBottonNavigation() {
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.search:
                        loadSearchFragment();
                        break;
                    case R.id.route:
                        loadRouteFragment();
                        break;
                    case R.id.pnr:
                        loadPnrFragment();
                        break;
                    case R.id.profile:
                        loadProfileFragment();
                        break;
                }
                return true;
            }
        });
    }

    private void loadProfileFragment() {
      androidx.fragment.app.Fragment fragment = ProfileFragment.newInstance();
        androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_fragment,fragment);
        ft.commit();
    }

    private void loadPnrFragment() {
      androidx.fragment.app.Fragment fragment = PnrFragment.newInstance();
      androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_fragment,fragment);
        ft.commit();
    }

    private void loadRouteFragment() {
        androidx.fragment.app.Fragment fragment = RouteFragment.newInstance();
        androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_fragment,fragment);
        ft.commit();
    }

    private void loadSearchFragment() {

        Fragment fragment = SearchFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_fragment,fragment);
        ft.commit();
    }

}