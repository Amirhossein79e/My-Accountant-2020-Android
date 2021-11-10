package com.amirhosseinemadi.myAccountant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.databinding.ActivityAddChequeBinding;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelAddCheque;

import java.util.zip.Inflater;

public class AddChequeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelAddCheque viewModelAddCheque = new ViewModelAddCheque(AddChequeActivity.this);
        ActivityAddChequeBinding binding = DataBindingUtil.setContentView(AddChequeActivity.this,R.layout.activity_add_cheque);
        binding.setViewModel(viewModelAddCheque);


    }
}
