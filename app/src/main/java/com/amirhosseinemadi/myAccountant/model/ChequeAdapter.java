package com.amirhosseinemadi.myAccountant.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.common.FaNumber;
import com.amirhosseinemadi.myAccountant.common.PrefManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ir.hamsaa.persiandatepicker.util.PersianHelper;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class ChequeAdapter extends RecyclerView.Adapter<ChequeAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;
    private List<Cheque> list;
    @Inject
    PrefManager prefManager;

    public ChequeAdapter(List<Cheque> list)
    {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(Application.component.context());
        Application.component.chequeAdapterInject(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_cheque,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Cheque cheque = list.get(position);

        switch (prefManager.getLang())
        {
            case "fa":

                    PersianDate persianDate = new PersianDate(cheque.getDate());
                    PersianDateFormat persianDateFormat = new PersianDateFormat("y/m/d");
                    holder.date.setText(PersianHelper.toPersianNumber(persianDateFormat.format(persianDate)));
                break;

            case "en":

                    Date date = new Date(cheque.getDate());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    holder.date.setText(simpleDateFormat.format(date));
                break;
        }

        holder.name.setText(cheque.getName());
        holder.account.setText(cheque.getAccount());
        Float f = Float.valueOf(cheque.getValue());
        String val = String.format("%,.0f",f);
        if (prefManager.getLang().equals("fa"))
        {
            holder.value.setText(FaNumber.faNum(val));
        }else
        {
            holder.value.setText(val);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView value,date,account,name;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            value = itemView.findViewById(R.id.txt_cash_ch);
            date = itemView.findViewById(R.id.txt_myDate_ch);
            account = itemView.findViewById(R.id.txt_account_ch);
            name = itemView.findViewById(R.id.txt_name_ch);
        }
    }
}
