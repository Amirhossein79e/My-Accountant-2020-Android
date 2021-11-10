package com.amirhosseinemadi.myAccountant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Window;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.databinding.ActivitySpendBinding;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelSpend;

public class SpendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySpendBinding binding = DataBindingUtil.setContentView(SpendActivity.this,R.layout.activity_spend);
        ViewModelSpend viewModel = new ViewModelSpend(SpendActivity.this);
        binding.setViewModel(viewModel);
    }
}
