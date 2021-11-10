package com.amirhosseinemadi.myAccountant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.databinding.ActivityShowChequeBinding;
import com.amirhosseinemadi.myAccountant.model.Cheque;
import com.amirhosseinemadi.myAccountant.model.ChequeAdapter;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelShowCheque;

import java.util.List;

public class ShowChequeActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelShowCheque viewModelShowCheque = new ViewModelShowCheque(ShowChequeActivity.this);
        ActivityShowChequeBinding binding = DataBindingUtil.setContentView(ShowChequeActivity.this,R.layout.activity_show_cheque);
        binding.setViewModel(viewModelShowCheque);

        recyclerView = binding.recyclerCheque;

        viewModelShowCheque.getMutableLiveData().observe(ShowChequeActivity.this, new Observer<List<Cheque>>() {
            @Override
            public void onChanged(List<Cheque> list) {
                ChequeAdapter chequeAdapter = new ChequeAdapter(list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ShowChequeActivity.this,RecyclerView.VERTICAL,true);
                layoutManager.setStackFromEnd(true);
                recyclerView.setAdapter(chequeAdapter);
                recyclerView.setLayoutManager(layoutManager);
                chequeAdapter.notifyDataSetChanged();
            }
        });

    }
}
