package com.amirhosseinemadi.myAccountant.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.DataBindingUtil;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.common.PrefManager;
import com.amirhosseinemadi.myAccountant.databinding.ActivitySettingBinding;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelSetting;

import java.util.Set;

import javax.inject.Inject;

public class SettingActivity extends AppCompatActivity {

    AppCompatSpinner spinner;
    Boolean aBoolean;
    @Inject
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivitySettingBinding binding = DataBindingUtil.setContentView(SettingActivity.this,R.layout.activity_setting);
        ViewModelSetting viewModelSetting = new ViewModelSetting(SettingActivity.this);
        binding.setViewModel(viewModelSetting);
        Application.component.settingActivityInject(this);

        spinner = binding.spinner;
        aBoolean = true;

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    switch (parent.getSelectedItemPosition()) {
                        case 1:
                            if (!aBoolean) {
                                prefManager.setLang("fa");
                                Toast.makeText(Application.component.context(),prefManager.getLang(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                                PendingIntent pendingIntent = PendingIntent.getActivity(SettingActivity.this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);
                                aBoolean = false;
                                System.exit(0);
                            }
                            break;
                        default:
                            if (!aBoolean) {
                                prefManager.setLang("en");
                                Toast.makeText(Application.component.context(),prefManager.getLang(), Toast.LENGTH_LONG).show();
                                Intent intent2 = new Intent(SettingActivity.this, MainActivity.class);
                                PendingIntent pendingIntent2 = PendingIntent.getActivity(SettingActivity.this, 2, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
                                AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
                                alarmManager2.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent2);
                                aBoolean = false;
                                System.exit(0);
                            }
                            break;
                    }

                    aBoolean = false;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
