package com.example.yongheshen.myeventbus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getSupportFragmentManager();//v4 getSupportFragmentManager( )   
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        transaction.replace(R.id.fragment1,firstFragment);
        transaction.replace(R.id.fragment2,secondFragment);
        transaction.commit();
    }
}
