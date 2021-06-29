package com.sm.example.demo;

public class MyModel implements MyView.Model {
    @Override
    public void getModel(BaseCall baseCall) {
        String string="123456789";
        baseCall.getData(string);
    }
}
