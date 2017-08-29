package com.haocaipu.basic;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.haocaipu.Interface.GetDataInterface;
import com.haocaipu.R;
import com.haocaipu.moel.Recipe;

import java.util.ArrayList;

public class BasicActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,GetDataInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onSucess(ArrayList arrayList) {

    }
}
