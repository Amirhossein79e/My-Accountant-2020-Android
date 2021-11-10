package com.amirhosseinemadi.myAccountant.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.common.FaNumber;
import com.amirhosseinemadi.myAccountant.common.PrefManager;
import com.amirhosseinemadi.myAccountant.view.DetialActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import ir.hamsaa.persiandatepicker.util.PersianHelper;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class SpendAdapter extends RecyclerView.Adapter<SpendAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;
    private List<Spend> list;
    private String key;
    @Inject
    PrefManager prefManager;

    public SpendAdapter(List<Spend> list,String key)
    {
        layoutInflater = LayoutInflater.from(Application.component.context());
        this.list = list;
        this.key = key;
        Application.component.spendAdapterInject(this);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Spend spend = list.get(position);
        switch (prefManager.getLang())
        {
            case "fa":

                PersianDate persianDate  = new PersianDate(spend.getTime());
                PersianDateFormat persianDateFormat = new PersianDateFormat("y/m/d");
                String date = persianDateFormat.format(persianDate);
                holder.date.setText(PersianHelper.toPersianNumber(date));

                break;

            case "en":

                Date date1 = new Date(spend.getTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String s = simpleDateFormat.format(date1);
                holder.date.setText(s);

                break;
        }

        Float f;
        switch (key)
        {
            case "rent":
                f = Float.valueOf(spend.getRent());
                String value = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value));
                }
                else
                    {
                        holder.cash.setText(value);
                    }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_home));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getRent(),spend.getDetial());
                    }
                });
                break;

            case "health":
                f = Float.valueOf(spend.getHealth());
                String value1 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value1));
                }
                else
                {
                    holder.cash.setText(value1);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_health));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getHealth(),spend.getDetial());
                    }
                });
                break;

            case "food":
                f = Float.valueOf(spend.getFood());
                String value2 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value2));
                }
                else
                {
                    holder.cash.setText(value2);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_food));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getFood(),spend.getDetial());
                    }
                });
                break;

            case "buy":
                f = Float.valueOf(spend.getBuy());
                String value3 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value3));
                }
                else
                {
                    holder.cash.setText(value3);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_buy));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getBuy(),spend.getDetial());
                    }
                });
                break;
            case "traffic":
                f = Float.valueOf(spend.getTraffic());
                String value4 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value4));
                }
                else
                {
                    holder.cash.setText(value4);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_transport));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getTraffic(),spend.getDetial());
                    }
                });
                break;

            case "clothes":
                f = Float.valueOf(spend.getClothes());
                String value5 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value5));
                }
                else
                {
                    holder.cash.setText(value5);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_clothes));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getClothes(),spend.getDetial());
                    }
                });
                break;

            case "phone":
                f = Float.valueOf(spend.getPhone());
                String value6 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value6));
                }
                else
                {
                    holder.cash.setText(value6);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_phone));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getPhone(),spend.getDetial());
                    }
                });
                break;

            case "car":
                f = Float.valueOf(spend.getCar());
                String value7 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value7));
                }
                else
                {
                    holder.cash.setText(value7);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_car));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getCar(),spend.getDetial());
                    }
                });
                break;

            case "hobby":
                f = Float.valueOf(spend.getHobby());
                String value9 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value9));
                }
                else
                {
                    holder.cash.setText(value9);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_hobby));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getHobby(),spend.getDetial());
                    }
                });
                break;

            case "internet":
                f = Float.valueOf(spend.getInternet());
                String value10 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value10));
                }
                else
                {
                    holder.cash.setText(value10);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_computer));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getInternet(),spend.getDetial());
                    }
                });
                break;

            case "other":
                f = Float.valueOf(spend.getOther());
                String value11 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value11));
                }
                else
                {
                    holder.cash.setText(value11);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_square));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getOther(),spend.getDetial());
                    }
                });
                break;

            case "pay":
                f = Float.valueOf(spend.getPay());
                String value12 = String.format("%,.0f",f);
                if (prefManager.getLang().equals("fa"))
                {
                    holder.cash.setText(FaNumber.faNum(value12));
                }
                else
                {
                    holder.cash.setText(value12);
                }
                holder.img.setImageDrawable(Application.component.context().getDrawable(R.drawable.ic_pay));
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myIntnt(spend.getPay(),spend.getDetial());
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView img;
        AppCompatTextView cash,date;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            cash = itemView.findViewById(R.id.txt_cash);
            date = itemView.findViewById(R.id.txt_myDate);
            cardView = itemView.findViewById(R.id.recycler_item);
        }
    }

    private void myIntnt(Long value,String detial)
    {
        Intent intent = new Intent(Application.component.context(), DetialActivity.class);
        intent.putExtra("value",value);
        intent.putExtra("detial",detial);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Application.component.context().startActivity(intent);
    }
}
