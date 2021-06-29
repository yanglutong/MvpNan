package com.sm.example.demo;

import com.j256.ormlite.stmt.query.Between;

public class MyPresenter implements MyView.Presenter {
    private final MyView.Model model;
    private final MyView.View view;

    public MyPresenter(MyView.View view) {
        this.view = view;
        model=new MyModel();
        view.setPresenter(this);
    }

    @Override
    public void setPresenter() {
        model.getModel(new BaseCall() {
            @Override
            public void getData(String s) {
                view.showView(s);
            }
        });
    }
}
