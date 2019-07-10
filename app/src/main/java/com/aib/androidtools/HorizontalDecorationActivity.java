package com.aib.androidtools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.aib.base.frame.FrameActivity;
import com.aib.base.recyclerview.adapter.BaseAdapterHelper;
import com.aib.base.recyclerview.adapter.QuickAdapter;
import com.aib.base.recyclerview.itemdecoration.LinearItemDecoration;
import com.aib.base.swipeback.SwipeBackActivity;

import java.util.Arrays;

public class HorizontalDecorationActivity extends SwipeBackActivity {

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearItemDecoration linearItemDecoration;

    private QuickAdapter<String> adapter = new QuickAdapter<String>(this, R.layout.item_horizontal) {
        @Override
        protected void convert(BaseAdapterHelper helper, String value) {
            helper.setText(R.id.tv_value, value);
        }
    };

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.rb_null:
                    recyclerView.removeItemDecoration(linearItemDecoration);
                    recyclerView.addItemDecoration(linearItemDecoration = new LinearItemDecoration(5, Color.GREEN, LinearItemDecoration.HORIZONTAL,  LinearItemDecoration.HORIZONTAL_INCLUDE_NULL));
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.rb_left:
                    recyclerView.removeItemDecoration(linearItemDecoration);
                    recyclerView.addItemDecoration(linearItemDecoration = new LinearItemDecoration(5, Color.GREEN, LinearItemDecoration.HORIZONTAL,  LinearItemDecoration.HORIZONTAL_INCLUDE_LEFT));
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.rb_right:
                    recyclerView.removeItemDecoration(linearItemDecoration);
                    recyclerView.addItemDecoration(linearItemDecoration = new LinearItemDecoration(5, Color.GREEN, LinearItemDecoration.HORIZONTAL,  LinearItemDecoration.HORIZONTAL_INCLUDE_RIGHT));
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.rb_include:
                    recyclerView.removeItemDecoration(linearItemDecoration);
                    recyclerView.addItemDecoration(linearItemDecoration = new LinearItemDecoration(5, Color.GREEN, LinearItemDecoration.HORIZONTAL, LinearItemDecoration.HORIZONTAL_INCLUDE_LEFT_RIGHT));
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.activity_horizontal_decoration;
    }

    @Override
    protected void initComponent() {
        super.initComponent();
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(linearItemDecoration = new LinearItemDecoration(5, Color.GREEN, LinearItemDecoration.HORIZONTAL,  LinearItemDecoration.HORIZONTAL_INCLUDE_NULL));
        adapter.addAll(Arrays.asList(getResources().getStringArray(R.array.vertical_items)));
        recyclerView.setAdapter(adapter);
    }
}
