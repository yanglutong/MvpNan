package com.sm.example.App;

import android.content.Context;

import java.security.AccessControlContext;

import static java.security.AccessController.getContext;

public class Application  extends android.app.Application {
    public static Context context = null;
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getContexts();//初始化 上下文
        instance = this;
    }

    public static Context getContexts() {
        return context;
    }
    public static Application getInstance() {
        return instance;
    }

    public Context getContext() {
        return getApplicationContext();
    }
}
