package com.amirhosseinemadi.myAccountant.common;

import android.content.Context;

import com.amirhosseinemadi.myAccountant.model.ChartHandler;
import com.amirhosseinemadi.myAccountant.model.ChequeAdapter;
import com.amirhosseinemadi.myAccountant.model.ChequeAlarm;
import com.amirhosseinemadi.myAccountant.model.DbHandler;
import com.amirhosseinemadi.myAccountant.model.SpendAdapter;
import com.amirhosseinemadi.myAccountant.view.MainActivity;
import com.amirhosseinemadi.myAccountant.view.SettingActivity;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelAddCheque;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelAddSpend;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelBalance;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelDetial;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelMain;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelSelect;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelShow;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelShowCheque;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelSpend;

@dagger.Component(modules = Module.class)
public interface Component {

    Context context();
    void injectReboot(RebootListener rebootListener);
    void chartInject(ChartHandler chartHandler);
    void chequeAdapterInject(ChequeAdapter chequeAdapter);
    void chequeAlarmInject(ChequeAlarm chequeAlarm);
    void spendAdapterInject(SpendAdapter spendAdapter);
    void mainActivityInject(MainActivity mainActivity);
    void settingActivityInject(SettingActivity settingActivity);
    void vmAddCheque(ViewModelAddCheque viewModelAddCheque);
    void vmAddSpend(ViewModelAddSpend viewModelAddSpend);
    void vmBalanceInject(ViewModelBalance viewModelBalance);
    void vmDetailInject(ViewModelDetial viewModelDetial);
    void vmMain(ViewModelMain viewModelMain);
    void vmShow(ViewModelShow viewModelShow);
    void vmShowCheque(ViewModelShowCheque viewModelShowCheque);



}
