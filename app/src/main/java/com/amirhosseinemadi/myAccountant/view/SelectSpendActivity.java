package com.amirhosseinemadi.myAccountant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.databinding.ActivitySelectSpendBinding;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelSelect;

public class SelectSpendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySelectSpendBinding binding = DataBindingUtil.setContentView(SelectSpendActivity.this,R.layout.activity_select_spend);
        ViewModelSelect viewModelSelect = new ViewModelSelect(SelectSpendActivity.this);
        binding.setViewModel(viewModelSelect);
    }
}
