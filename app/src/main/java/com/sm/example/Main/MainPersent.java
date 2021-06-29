package com.sm.example.Main;


import android.util.Log;



import androidx.annotation.NonNull;



public class MainPersent implements MainView.MainPresenter {
    public MainView.View viewS;

    public MainPersent(@NonNull MainView.View view) {
        this.viewS = view;
        view.setPresenter(this);
    }


    @Override
    public void set(String str) {
        Log.d("nzq", str);


        if("修改为你的名字".equals(str)){
            viewS.Up("名字修改为：", "杨路通");
        }
    }
}
