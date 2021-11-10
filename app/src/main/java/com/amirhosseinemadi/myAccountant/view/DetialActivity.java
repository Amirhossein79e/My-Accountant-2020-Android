package com.amirhosseinemadi.myAccountant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.databinding.ActivityDetialBinding;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelDetial;

public class DetialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelDetial viewModelDetial = new ViewModelDetial(DetialActivity.this);
        ActivityDetialBinding binding = DataBindingUtil.setContentView(DetialActivity.this,R.layout.activity_detial);
        binding.setViewModel(viewModelDetial);


    }
}
