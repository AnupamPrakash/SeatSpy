package com.example.seatspy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {


    private ViewPager viewPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndictor;
    Button btnNext;
    int position = 0 ;
    Button btnGetStarted;
    Animation btnAnim ;
    TextView tvSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class );
            startActivity(mainActivity);
            finish();
        }
        setContentView(R.layout.activity_intro);
        viewPager = findViewById(R.id.view_pager);
        tabIndictor = findViewById(R.id.tab_layout);
        btnNext = findViewById(R.id.button_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        final List<IntroScreenItem> ScreenList = new ArrayList<>();
        ScreenList.add(new IntroScreenItem("Seat Availabilty","Check your seat availability",R.drawable.trainseat));
        ScreenList.add(new IntroScreenItem("PNR Status","Check your pnr status",R.drawable.pnr));
        ScreenList.add(new IntroScreenItem("Alternate Routes","Check for alternate routes to follow",R.drawable.route));
        introViewPagerAdapter = new IntroViewPagerAdapter(this,ScreenList);
        viewPager.setAdapter(introViewPagerAdapter);
        tabIndictor.setupWithViewPager(viewPager);

        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                position = viewPager.getCurrentItem();
                if (position < ScreenList.size()) {
                    position++;
                    viewPager.setCurrentItem(position);
                }
                if (position == ScreenList.size()-1) { // when we rech to the last screen
                    loaddLastScreen();
                }
            }
        });
        tabIndictor.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == ScreenList.size()-1) {
                    loaddLastScreen();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();
    }

    private void loaddLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
//        tabIndictor.removeAllTabs();
        viewPager.beginFakeDrag();
        tabIndictor.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnim);
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;
    }
}
