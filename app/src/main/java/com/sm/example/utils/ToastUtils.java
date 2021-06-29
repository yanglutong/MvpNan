package com.sm.example.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.sm.example.App.Application;
import com.sm.example.R;


public class ToastUtils {
    //    //居中 自定义布局Toast
    public static void showToast(String text) {

        Context contexts = Application.getContexts();
        try {
            Toast toast = Toast.makeText(contexts, text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, -250);

            View view = LayoutInflater.from(contexts).inflate(R.layout.toast_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(text + "");
            toast.setView(view);
            toast.show();
        } catch (Exception e) {

        }
    }

    //    //居中 自定义布局Toast
    public static void showToastDown(String text) {

        Context contexts = Application.getContexts();
        try {
            Toast toast = Toast.makeText(contexts, text, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, -250);
            View view = LayoutInflater.from(contexts).inflate(R.layout.toast_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(text + "");
            toast.setView(view);
            toast.show();
        } catch (Exception e) {
        }
    }
}
