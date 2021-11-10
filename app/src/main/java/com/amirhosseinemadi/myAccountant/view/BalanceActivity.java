package com.amirhosseinemadi.myAccountant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.databinding.ActivityBalanceBinding;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelBalance;

public class BalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBalanceBinding binding = DataBindingUtil.setContentView(BalanceActivity.this,R.layout.activity_balance);
        ViewModelBalance viewModelBalance = new ViewModelBalance(this);
        binding.setViewModel(viewModelBalance);
    }

}
