package com.amirhosseinemadi.myAccountant.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.model.Cheque;
import com.amirhosseinemadi.myAccountant.model.DbHandler;

import java.util.List;

import javax.inject.Inject;

public class ViewModelShowCheque extends BaseObservable {

    private AppCompatActivity activity;
    private List<Cheque> list;
    private MutableLiveData<List<Cheque>> mutableLiveData;
    @Inject
    DbHandler dbHandler;

    public MutableLiveData<List<Cheque>> getMutableLiveData()
    {
        return mutableLiveData;
    }

    public ViewModelShowCheque(AppCompatActivity activity)
    {
        this.activity = activity;

        mutableLiveData = new MutableLiveData<>();

        Application.component.vmShowCheque(this);

        list = dbHandler.getCheque();

        mutableLiveData.postValue(list);
    }


    public void return1()
    {
        activity.finish();
    }

}
