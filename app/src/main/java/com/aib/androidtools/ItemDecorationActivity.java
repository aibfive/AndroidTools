package com.aib.androidtools;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.aib.base.recyclerview.adapter.BaseAdapterHelper;
import com.aib.base.recyclerview.adapter.OnItemClickListener;
import com.aib.base.recyclerview.adapter.QuickAdapter;
import com.aib.base.recyclerview.itemdecoration.LinearItemDecoration;
import com.aib.base.swipeback.SwipeBackActivity;

import java.util.Arrays;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ItemDecorationActivity extends SwipeBackActivity {

    @BindView(R.id.ll_content)
    LinearLayout contentLl;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private QuickAdapter<String> adapter = new QuickAdapter<String>(this, R.layout.item_vertical) {
        @Override
        protected void convert(BaseAdapterHelper helper, String value) {
            helper.setText(R.id.tv_value, value);
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.activity_item_decoration;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    protected void initComponent() {
        super.initComponent();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new LinearItemDecoration(10, Color.BLUE));
        adapter.addAll(Arrays.asList(getResources().getStringArray(R.array.vertical_items)));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("GG", "onItemClick-->"+position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.i("GG", "onItemLongClick-->"+position);
            }
        });
    }

}
