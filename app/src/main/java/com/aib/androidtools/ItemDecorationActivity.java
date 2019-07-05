package com.aib.androidtools;

import android.graphics.Color;
import android.os.Bundle;

import com.aib.base.recyclerview.adapter.BaseAdapterHelper;
import com.aib.base.recyclerview.adapter.QuickAdapter;
import com.aib.base.recyclerview.itemdecoration.LinearItemDecoration;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDecorationActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private QuickAdapter<String> adapter = new QuickAdapter<String>(this, R.layout.item_vertical) {
        @Override
        protected void convert(BaseAdapterHelper helper, String value) {
            helper.setText(R.id.tv_value, value);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_decoration);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new LinearItemDecoration(10, Color.BLUE));
        adapter.addAll(Arrays.asList(getResources().getStringArray(R.array.vertical_items)));
        recyclerView.setAdapter(adapter);

        
    }

}
