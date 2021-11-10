package com.amirhosseinemadi.myAccountant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.databinding.ActivityShowSpendBinding;
import com.amirhosseinemadi.myAccountant.model.Spend;
import com.amirhosseinemadi.myAccountant.model.SpendAdapter;
import com.amirhosseinemadi.myAccountant.viewModel.ViewModelShow;

import java.util.List;

public class ShowSpendActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShowSpendBinding binding = DataBindingUtil.setContentView(ShowSpendActivity.this,R.layout.activity_show_spend);
        ViewModelShow viewModelShow = new ViewModelShow(ShowSpendActivity.this);
        binding.setViewModel(viewModelShow);

        recyclerView = binding.recycler;

        viewModelShow.getLiveData().observe(ShowSpendActivity.this, new Observer<List<Spend>>() {
            @Override
            public void onChanged(List<Spend> list) {
                if (list!=null){
                SpendAdapter spendAdapter = new SpendAdapter(list,getIntent().getExtras().getString("key"));
                LinearLayoutManager layoutManager = new LinearLayoutManager(ShowSpendActivity.this,RecyclerView.VERTICAL,true);
                layoutManager.setStackFromEnd(true);
                spendAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(spendAdapter);
                recyclerView.setLayoutManager(layoutManager);}
            }
        });

    }
}
