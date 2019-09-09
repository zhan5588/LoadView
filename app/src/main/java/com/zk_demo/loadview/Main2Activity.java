package com.zk_demo.loadview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends BaseBarActivity {


    TextView textView;

    @Override
    protected int intiLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setCenterTitle("demo2");
        textView = findViewById(R.id.tv);

        showLoad();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showContent();
            }
        }, 5000);
    }

    @Override
    protected void setOnEmptyViewClickListener() {
        super.setOnEmptyViewClickListener();
        Log.e("zz","空页面监听");
    }

    @Override
    protected void setOnErrorViewClickListener() {
        super.setOnErrorViewClickListener();
        Log.e("zz","错误页面监听");
    }
}
