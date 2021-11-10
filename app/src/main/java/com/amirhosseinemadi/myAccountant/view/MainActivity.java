package com.amirhosseinemadi.myAccountant.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.R.color;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.common.FaNumber;
import com.amirhosseinemadi.myAccountant.common.PrefManager;
import com.amirhosseinemadi.myAccountant.databinding.ActivityMainBinding;
import com.amirhosseinemadi.myAccountant.model.ChartModel;
import com.amirhosseinemadi.myAccountant.model.DbCreate;
import com.amirhosseinemadi.myAccountant.model.DbHandler;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelMain;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import ir.hamsaa.persiandatepicker.util.PersianHelper;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DbCreate dbCreate;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    LineChart lineChart;
    List<Entry> barEntryList;
    List<String> labels;
    NavigationView navigationView;
    Typeface typeface;
    @Inject
    PrefManager prefManager;
    @Inject
    DbHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application.component.mainActivityInject(this);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefManager.getLang()));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Resources resources = new Resources(getAssets(),displayMetrics,configuration);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/yekan.ttf");

        dbCreate = new DbCreate(MainActivity.this);
        ViewModelMain viewModelMain = new ViewModelMain(dbCreate,MainActivity.this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding.setViewModel(viewModelMain);

        drawerLayout = binding.drawer;
        toolbar = binding.toolbarMain;
        lineChart = binding.chart;
        navigationView = binding.nav;

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(color.md_white_1000));
        actionBarDrawerToggle.getDrawerArrowDrawable().setSpinEnabled(true);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(MainActivity.this);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                barEntryList = new ArrayList<>();
                labels = new ArrayList<>();
                List<ChartModel> list = dbHandler.incomeList();

                if (list.size()<=7)
                {
                    lineChart.getXAxis().setLabelCount(list.size(),true);
                }


                if (list.size()!=0)
                {
                    int i = 0;
                    for (ChartModel chartModel : list )
                    {
                        barEntryList.add(new BarEntry(i, chartModel.getIncome()));
                        PersianDate persianDate = new PersianDate(chartModel.getTime());
                        PersianDateFormat persianDateFormat = new PersianDateFormat("m/d");
                        if(prefManager.getLang().equals("fa"))
                        {
                            labels.add(PersianHelper.toPersianNumber(persianDateFormat.format(persianDate)));
                        }else
                        {
                            labels.add(persianDateFormat.format(persianDate));
                        }
                        i++;
                    }
                }


                LineDataSet lineDataSet = new LineDataSet(barEntryList,null);
                lineDataSet.setCircleColor(color.md_pink_200);
                lineDataSet.setDrawFilled(true);
                lineDataSet.setFillDrawable(getResources().getDrawable(R.drawable.card_background));
                lineDataSet.setValueTypeface(typeface);
                lineDataSet.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value)
                    {
                        String s;
                        if (prefManager.getLang().equals("fa"))
                        {
                            s = FaNumber.faNum(String.format("%,.0f",value));
                        }else
                        {
                            s = String.format("%,.0f",value);
                        }
                        return s;
                    }
                });

                final LineData lineData = new LineData(lineDataSet);
                lineData.setValueTextSize(9);
                lineData.setValueTypeface(typeface);

                final Description description = new Description();
                description.setText(getResources().getString(R.string.description));

                YAxis left = lineChart.getAxisLeft();
                left.setTypeface(typeface);
                YAxis right = lineChart.getAxisRight();
                right.setTypeface(typeface);

                        lineChart.setDescription(description);
                        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
                        lineChart.getXAxis().setTypeface(typeface);
                        lineChart.setData(lineData);
                        lineChart.setNoDataTextTypeface(typeface);
                        lineChart.setDragEnabled(true);
                        lineChart.setVisibleXRangeMaximum(6);
                        lineChart.getAxisLeft().setEnabled(false);
                        lineChart.getAxisRight().setEnabled(false);

            }
        });
        thread.start();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.setting:
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
                break;

            case R.id.bug:
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:amirhossein79.e@gmail.com"));
                startActivity(intent1);
                break;

            case R.id.cheque:
                Intent intent2 = new Intent(MainActivity.this,AddChequeActivity.class);
                startActivity(intent2);
                break;

            case R.id.show_ch:
                Intent intent3 = new Intent(MainActivity.this,ShowChequeActivity.class);
                startActivity(intent3);
                break;

            case R.id.exit:
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.dialogTheme);
                alert.setTitle(R.string.alert_title)
                        .setMessage(R.string.alert_message)
                        .setPositiveButton(R.string.alert_pos, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                finishAffinity();

                            }
                        })
                        .setNegativeButton(R.string.alert_neg,null)
                        .show();
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.dialogTheme);
        alert.setTitle(R.string.alert_title)
                .setMessage(R.string.alert_message)
                .setPositiveButton(R.string.alert_pos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finishAffinity();

                    }
                })
                .setNegativeButton(R.string.alert_neg,null)
                .show();
    }
}
