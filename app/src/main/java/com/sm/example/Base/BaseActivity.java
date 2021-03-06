package com.sm.example.Base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;


import com.sm.example.R;
import com.sm.example.utils.ActivityUtil;
import com.sm.example.utils.MyUtils;
import com.sm.example.utils.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.sm.example.Contant.Contants.WIFINAME;

public abstract class BaseActivity extends AppCompatActivity {
    public static Dialog dialog;
    public static String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.GET_TASKS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION

    };//???????????????
    public static List<String> mPermissionList = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final int MY_PERMISSIONS_REQUEST_CALL_CAMERA = 2;
    public Context Basecontext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
        initQx();
//        getWindows();
        setContentView(getViewLayout());//????????????
//        setStatBar();//????????????????????????????????????
        findViews();//???????????????
        try {
            init();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Basecontext=this;

        // ?????????netEvent
//        netEvent = (NetBroadcastReceiver.NetChangeListener) this;
    }

    private void getWindows() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,//????????????,???????????????Activity?????????
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    protected abstract void initQx();

    protected abstract void init() throws UnsupportedEncodingException;//??????????????????

    protected abstract void findViews();//???????????????

    protected abstract int getViewLayout();//??????

    @SuppressLint("NewApi")
    public void setStatBar() {//????????????????????????????????????
        View decorView = getWindow().getDecorView();
        int option =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT
        );
    }
    private boolean ifWifi() {
        String wifiName = MyUtils.getWifiName(this);
        if (wifiName.equals(WIFINAME)||wifiName.contains(WIFINAME)) {
            return true;
        } else {
            ToastUtils.showToast("??????wifi??????");
            return false;
        }
    }
    public void LoadingTrue(String text) {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //????????????????????????
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_dibushow, null);
        TextView tv=inflate.findViewById(R.id.tv);
        tv.setText(""+text);
        //??????????????????Dialog
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(inflate);
        //????????????Activity???????????????
        Window dialogWindow = dialog.getWindow();
        //??????Dialog?????????????????????
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //?????????????????????
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//       ????????????????????????
        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);//????????????????????????
        dialog.show();//???????????????
    }

    public void LoadingFalse() {
        if (dialog!=null){
            dialog.dismiss();
            dialog.cancel();
        }

    }

    private long mPressedTime = 0;

    @Override
    public void onBackPressed() {//?????????????????????
        long mNowTime = System.currentTimeMillis();//???????????????????????????
        Log.d("nzq", "onBackPressed: "+mNowTime);
        if ((mNowTime - mPressedTime) > 2000) {//???????????????????????????
            ToastUtils.showToast("????????????????????????");
            mPressedTime = mNowTime;
        } else {//????????????
            this.finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        // Activity??????????????????????????????
        // System.gc();
        // ??????Activity
        ActivityUtil.getInstance().removeActivity(this);
        if (dialog!=null){
            dialog.cancel();
        }
        super.onDestroy();
    }
    /**
     * ????????????
     */
    public void getPermissions() {//??????????????????
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//?????????????????????????????????????????????
//            Toast.makeText(LoginActivity.this,"????????????",Toast.LENGTH_LONG).show();
        } else {//??????????????????
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//???List????????????
            ActivityCompat.requestPermissions(this, permissions, MY_PERMISSIONS_REQUEST_CALL_CAMERA);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(getPackageName());
        }
        return isIgnoring;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestIgnoreBatteryOptimizations() {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (dialog!=null){

        }
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = 1;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
