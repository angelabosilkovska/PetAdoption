package com.android.example.petadoption.OnboardingSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.example.petadoption.LoginActivity;
import com.android.example.petadoption.OnboardingSection.OnboardingFragments.OnboardingFragment1;
import com.android.example.petadoption.OnboardingSection.OnboardingFragments.OnboardingFragment2;
import com.android.example.petadoption.OnboardingSection.OnboardingFragments.OnboardingFragment3;
import com.android.example.petadoption.R;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager2 myViewPager2;
    Adapter myAdapter;
    String prevStarted = "yes";

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            moveToSecondary(null);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        myViewPager2 = findViewById(R.id.viewpager);
        myAdapter = new Adapter(getSupportFragmentManager(), getLifecycle());

        myAdapter.addFragment(new OnboardingFragment1());
        myAdapter.addFragment(new OnboardingFragment2());
        myAdapter.addFragment(new OnboardingFragment3());

        myViewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        myViewPager2.setAdapter(myAdapter);
    }

    public void moveToSecondary(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}