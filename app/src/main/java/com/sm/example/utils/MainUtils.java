package com.sm.example.utils;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainUtils {
    public static DatagramSocket DS = null;
    public static void ReceiveMain(final Handler handler, Message messages, final Bundle bundle, final Timer timer1, final Timer timer2, final DatagramPacket dp, final byte[] buf, final Context context, final boolean runThread, String ip1, String ip2, int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DS = new DatagramSocket(3345);
                    if (DS == null) {
                        DS = new DatagramSocket(null);
                        DS.setReuseAddress(true);
                        DS.bind(new InetSocketAddress(port));
                    }
                }catch (Exception e){

                }
                while (true){
                    try {
                        DS.receive(dp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] buf1 = dp.getData();
                    String str = ReceiveTask.toHexString1(buf1);
                    if (ip1.equals(dp.getAddress().getHostAddress())) {
                        System.out.println("收到" + dp.getAddress().getHostAddress() + "发送数据：" + str);
//                        if("00ff".equals(str.substring(16, 20))){
//                            //设置模式FDD
//                            System.err.println("FDD");
//                        }if("ff00".equals(str.substring(16, 20))){
//                            //设置模式TDD
//                            System.out.println("TDD");
//                        }
                    }
                }
            }
        }).start();
    }
}
