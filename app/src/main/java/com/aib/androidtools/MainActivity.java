package com.aib.androidtools;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_main);
    }

    /**
     * ItemDecoration
     * @param view
     */
    public void onItemDecorationClick(View view){
        Intent intent = new Intent();
        intent.setClass(this, ItemDecorationActivity.class);
        startActivity(intent);
    }

}
