package com.zk_demo.loadview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zk.loadingview.StatusView;
import com.zk.loadingview.StatusViewBuilder;

/**
 * Created by zhanke
 * 导航栏和顶部状态
 */

public abstract class BaseBarActivity extends AppCompatActivity {

    private TextView mCenterTitle;
    private Toolbar toolbar;
    private StatusView viewContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (loadToolbarGone()) {
            setContentView(intiLayoutId());
        } else {
            setContentView(R.layout.activity_base);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            viewContent = (StatusView) findViewById(R.id.viewContent);
            mCenterTitle = (TextView) findViewById(R.id.center_bar_tv);
            initTitleBar(toolbar);
            setStatusViewClick();
            viewContent.addContentView(intiLayoutId());
        }
        init(savedInstanceState);
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int intiLayoutId();

    /**
     * 初始化
     */
    protected void init(@Nullable Bundle savedInstanceState) {
    }

    protected void setToolbarGone() {
        toolbar.setVisibility(View.GONE);
    }

    protected void setCenterTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mCenterTitle.setText(title);
        }
    }

    /**
     * 隐藏状态栏
     */
    protected void hideStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
    }

    /**
     * 显示正在加载
     */
    protected void showLoad() {
        viewContent.showLoadingView();
    }

    /**
     * 显示数据内容
     */
    protected void showContent() {
        viewContent.showContentView();
    }

    /**
     * 网络错误
     */
    protected void showError() {
        viewContent.showErrorView();
    }

    /**
     * 没有数据
     */
    protected void showEmpty() {
        viewContent.showEmptyView();
    }

    private void setStatusViewClick() {
        //TODO  错误和空页面的按钮监听
        viewContent.config(new StatusViewBuilder.Builder()
                .setOnErrorRetryClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // viewContent.showLoadingView();
                        setOnErrorViewClickListener();
                    }
                }).setOnEmptyRetryClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // viewContent.showLoadingView();
                        setOnEmptyViewClickListener();
                    }
                })
                .build());
    }

    /**
     * 网络错误页面事件回调
     */
    protected void setOnErrorViewClickListener() {

    }

    /**
     * 空页面事件回调
     */
    protected void setOnEmptyViewClickListener() {

    }

    protected boolean loadToolbarGone(){
        return false;
    }

    protected void initTitleBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNavigationIcon();
            }
        });
    }

    protected void onNavigationIcon(){
        finish();
    }

    protected void onKeyBack(){

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onKeyBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
