package com.cse110w240t1.systemmoniter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fanfan on 2/7/16.
 */

class CustomAdapter extends ArrayAdapter<String> {
    private Context mContext;

    public CustomAdapter(Context context, String[] information) {
        super(context, R.layout.custom_row, information);

        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater customInflater = LayoutInflater.from(getContext());
        View customView = customInflater.inflate(R.layout.custom_row, parent, false);

        String cellName = getItem(position);
        TextView title = (TextView) customView.findViewById(R.id.info_title);
        final TextView content = (TextView) customView.findViewById(R.id.info_content);
        ImageView image = (ImageView) customView.findViewById(R.id.info_icon);

        title.setText(cellName);
        image.setImageResource(R.drawable.default_icon);
        content.setText("Loading...");

        //System Info
        if (cellName == "OS Version") {
            content.setText(SystemInfoFragment._OS_VERSION);
            image.setImageResource(R.drawable.os_icon);
        }
        if (cellName == "Security Patch") {
            content.setText(SystemInfoFragment._SECURITY_PATCH);
            image.setImageResource(R.drawable.security_patch_icon);
        }
        if (cellName == "Phone Model") {
            content.setText(SystemInfoFragment._PHONE_MODEL);
            image.setImageResource(R.drawable.phone_model_icon);
        }
        if (cellName == "Manufacturer") {
            content.setText(SystemInfoFragment._MANUFACTURER);
            image.setImageResource(R.drawable.phone_model_icon);
        }
        if (cellName == "SIM Card Status") {
            content.setText(SystemInfoFragment._SIM_CARD);
            image.setImageResource(R.drawable.sim_card_status);
        }
        if (cellName == "Serial Number") {
            content.setText(SystemInfoFragment._SERIAL_NUMBER);
            image.setImageResource(R.drawable.serial_number_icon);
        }
        if (cellName == "IMEI") {
            content.setText(SystemInfoFragment._IMEI);
            image.setImageResource(R.drawable.imei_icon);
        }

        //Battery Info
        if (cellName == "Percentage") {
            content.setText(BatteryFragment._PERCENTAGE);
            image.setImageResource(R.drawable.percentage_icon);
        }
        if (cellName == "Voltage") {
            content.setText(BatteryFragment._VOLTAGE);
            image.setImageResource(R.drawable.voltage_icon);
        }
        if (cellName == "Battery Temperature") {
            content.setText(BatteryFragment._TEMPERATURE);
            image.setImageResource(R.drawable.temperature_icon);
        }
        if (cellName == "Technology") {
            content.setText(BatteryFragment._TECHNOLOGY);
            image.setImageResource(R.drawable.technology_icon);
        }

        //RAM Info
        if (cellName == "Total Memory") {
            content.setText(RAMFragment._TOTAL_MEMORY);
            image.setImageResource(R.drawable.memory_icon);
        }
        if (cellName == "Available Memory") {
            content.setText(RAMFragment._AVAILABLE_MEMORY);
            image.setImageResource(R.drawable.available_memory_icon);
        }
        if (cellName == "Current Using Memory") {
            content.setText(RAMFragment._USING_MEMORY);
            image.setImageResource(R.drawable.using_memory_icon);
        }

        //CPU Info
        if (cellName == "CPU Architecture") {
            content.setText(CPUFragment._CPU_ARCHITECTURE);
            image.setImageResource(R.drawable.cpu_icon);
        }

        if (cellName == "CPU Make") {
            content.setText(CPUFragment._CPU_MAKE);
            image.setImageResource(R.drawable.cpu_icon);
        }

        if (cellName == "CPU Model") {
            content.setText(CPUFragment._CPU_MODEL);
            image.setImageResource(R.drawable.cpu_icon);
        }

        if (cellName == "CPU Clock Speed") {
            content.setText(CPUFragment._CPU_CLOCK_SPEED);
            image.setImageResource(R.drawable.speed_icon);
        }

        if (cellName == "CPU Load") {
            new Thread(new Runnable() {
                public void run() {
                    Looper.prepare();

                    final Handler CPUUpdater = new Handler(Looper.getMainLooper());
                    final int delay = 750;

                    CPUUpdater.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            content.setText(CPUFragment._CPU_USAGE);

                            CPUUpdater.postDelayed(this, delay);
                        }
                    }, delay);

                    Looper.loop();
                }
            }).start();

            image.setImageResource(R.drawable.load_icon);
        }


        //GPU Info
        if (cellName == "GPU Load") {
            Thread GPUThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();

                    final Handler GPUUpdater = new Handler(Looper.getMainLooper());
                    final int delay = 750;

                    GPUUpdater.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            content.setText(GPUFragment._GPU_LIVE_USAGE);

                            GPUUpdater.postDelayed(this, delay);
                        }
                    }, delay);

                    Looper.loop();
                }
            });
            GPUThread.start();

            image.setImageResource(R.drawable.load_icon);
        }

        if (cellName == "GPU Make") {
            content.setText(MainActivity.VENDOR);
            image.setImageResource(R.drawable.gpu_icon);
        }

        if (cellName == "OpenGL ES Version") {
            content.setText(MainActivity.VERSION);
            image.setImageResource(R.drawable.gpu_icon);
        }

        if (cellName == "GPU Model") {
            content.setText(MainActivity.RENDERER);
            image.setImageResource(R.drawable.gpu_icon);
        }

        if (cellName == "GPU Clock Speed") {
            content.setText(GPUFragment._GPU_CLOCK_SPEED);
            image.setImageResource(R.drawable.speed_icon);
        }

        return customView;
    }
}
