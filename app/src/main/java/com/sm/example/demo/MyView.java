package com.sm.example.demo;

import com.sm.example.Base.BasePresenter;
import com.sm.example.Base.BaseView;

public class MyView {
    interface View extends BaseView<MyPresenter> {//展示到activity
        void showView(String name);
    }
    interface Presenter extends BasePresenter{//处理逻辑
        void setPresenter();
    }
    interface Model {//网络
        void getModel(BaseCall baseCall);
    }
}
