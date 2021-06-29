package com.sm.example.Main;


import android.content.Context;
import android.graphics.drawable.Drawable;


import com.sm.example.Base.BasePresenter;
import com.sm.example.Base.BaseView;

public class MainView {
     interface View extends BaseView<MainPresenter> {
        void Up(String str, String strs);//回调更新
    }

     interface MainPresenter extends BasePresenter {//使用
        void set(String str);
    }
}
