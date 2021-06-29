package com.sm.example.Main;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sm.example.App.Application;
import com.sm.example.Base.BaseActivity;
import com.sm.example.OrmSqlLite.Bean.DemoBean;
import com.sm.example.OrmSqlLite.DBManager;
import com.sm.example.R;
import com.sm.example.utils.MainUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import jxl.demo.Demo;


public class MainActivity extends BaseActivity implements MainView.View {
    MainView.MainPresenter presenter;
    EditText editText;
    Button button1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            if (msg.what == 100001) {//   第一种
//
            //                    break;
//            }
//            switch (msg.what) {    //第二种
//                case 10001:
//
//                    break;
//            }
        }
    };
//        handleMessage()

    @Override

    protected void initQx() {
        getPermissions();
    }

    @Override
    protected void init() throws UnsupportedEncodingException {
        new MainPersent(this);
        handler = handler;
        //以下MVP示例
        button1 = findViewById(R.id.bt_setEdit);
        editText = findViewById(R.id.et_get);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.set("改成2好不好?");
            }
        });


    }

    @Override
    protected void findViews() {
        getData();
    }

    private void getData() {

    }































    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void Up(String str, String strs) {
        editText.setText(strs);//只做更新 回调
    }

    @Override
    public void setPresenter(MainView.MainPresenter presenter) {
        this.presenter = presenter;
    }
}