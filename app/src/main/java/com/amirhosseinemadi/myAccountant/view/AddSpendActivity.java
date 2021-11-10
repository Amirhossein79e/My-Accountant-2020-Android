package com.amirhosseinemadi.myAccountant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.transition.Transition;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Window;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.databinding.ActivityAddSpendBinding;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelAddSpend;

public class AddSpendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddSpendBinding binding = DataBindingUtil.setContentView(AddSpendActivity.this,R.layout.activity_add_spend);
        ViewModelAddSpend viewModelAddSpend = new ViewModelAddSpend(AddSpendActivity.this);
        binding.setViewModel(viewModelAddSpend);
    }
}
