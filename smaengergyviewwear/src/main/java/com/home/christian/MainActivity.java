package com.home.christian;

import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;

import com.zgkxzx.modbus4And.ModbusMaster;
import com.zgkxzx.modbus4And.ModbusSlaveSet;
import com.zgkxzx.modbus4And.base.ModbusUtils;
import com.zgkxzx.modbus4And.requset.ModbusParam;
import com.zgkxzx.modbus4And.requset.ModbusReq;
import com.zgkxzx.modbus4And.requset.OnRequestBack;
import com.zgkxzx.modbus4And.value.ModbusValue;
import com.sccomponents.gauges.gr004.GR004;

public class MainActivity extends WearableActivity {

    private GR004 gauge;
    private int updateCounter;
    private Handler handler = new Handler();

    // Define the code block to be executed
    /*private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // read power value
            ModbusReq.getInstance().readHoldingRegisters(new OnRequestBack<short[]>() {

                @Override
                public void onSuccess(short[] data) {
                    Integer value = 0;
                    for (int i = 0; i < data.length; i++) {
                        value += (data[i] & 0x0000FFFF) << ((data.length - 1) - i) * 16;
                    }
                    if (value == -2147483648) {
                        value = 0;
                    }
                    final double uiValue = value / 1000.0;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    Log.d("1", "readPower onSuccess " + value.toString());
                }

                @Override
                public void onFailed(String msg) {
                    Log.e("1", "readPower onFailed " + msg);
                }
            }, 3, 30775, 2);
            updateCounter -= 1;
            if (updateCounter <= 0) {
                // read energy value
                ModbusReq.getInstance().readHoldingRegisters(new OnRequestBack<short[]>() {

                    @Override
                    public void onSuccess(short[] data) {
                        long value = 0;
                        for (int i = 0; i < data.length; i++) {
                            value += (data[i] & 0x000000000000FFFF) << ((data.length - 1) - i) * 16;
                        }
                        final double uiValue = value / 1000.0;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateCounter = 24;
                            }
                        });
                        Log.d("1", "readEnergy onSuccess " + Long.toString(value));
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e("1", "readEnergy onFailed " + msg);
                    }
                }, 3, 30517, 4);
            }
            // Repeat every 5 seconds
            handler.postDelayed(runnable, 5000);
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
        setAmbientEnabled();

        gauge = findViewById(R.id.gauge);

        gauge.setValue(0);

        /*ModbusReq.getInstance().setParam(new ModbusParam()
                .setHost("192.168.1.69")
                .setPort(502)
                .setEncapsulated(false)
                .setKeepAlive(true)
                .setTimeout(2000)
                .setRetries(0))
                .init(new OnRequestBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.d("1", "onSuccess " + s);
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.d("1", "onFailed " + msg);
                    }
                });*/
    }

    protected void onDestroy() {
        ModbusReq.getInstance().destory();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        //handler.removeCallbacks(runnable);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCounter = 0;
        //handler.post(runnable);
    }

    /*@Override
    public void onEnterAmbient(Bundle ambientDetails) {
        handler.removeCallbacks(runnable);
        super.onEnterAmbient(ambientDetails);
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        handler.post(runnable);
    }*/
}
