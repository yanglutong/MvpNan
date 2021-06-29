package com.sm.example.demo;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sm.example.Base.BaseActivity;
import com.sm.example.R;

import java.io.UnsupportedEncodingException;

public class MainActivity2 extends BaseActivity implements MyView.View {
    private MyView.Presenter presenter;
    private TextView tv;

    @Override
    protected void initQx() {
        getPermissions();
    }

    @Override
    protected void init() throws UnsupportedEncodingException {
        new MyPresenter(this);
    }

    @Override
    protected void findViews() {
        tv = findViewById(R.id.tv);
        tv.setOnClickListener(view -> {
            //调用p层
            presenter.setPresenter();
        });
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main2;
    }




    @Override
    public void showView(String name) {
        Toast.makeText(Basecontext, ""+name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MyPresenter presenter) {
        this.presenter=presenter;
    }
}
