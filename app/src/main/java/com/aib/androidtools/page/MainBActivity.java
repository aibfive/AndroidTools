package com.aib.androidtools.page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.aib.androidtools.R;

public class MainBActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_a);
        Log.i("GG", TAG+"-->onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("GG", TAG+"-->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("GG", TAG+"-->onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("GG", TAG+"-->onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("GG", TAG+"-->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("GG", TAG+"-->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("GG", TAG+"-->onDestroy");
    }
}
