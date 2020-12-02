package com.example.tugas4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas4.util.Util;
import com.example.tugas4.R;
import com.example.tugas4.ViewActivity;
import com.example.tugas4.models.Pulsa;
import com.example.tugas4.viewmodel.PulsaViewModel;

import java.util.ArrayList;


public class PulsaAdapter extends RecyclerView.Adapter<PulsaAdapter.PulsaViewHolder>  {
    private Context context;
    private RelativeLayout layout_rincian;
    private ArrayList<Pulsa> list;
    private AppCompatImageView closeIV;
    private AppCompatTextView paymentTV,pulsaTV;
    private CoordinatorLayout layoutPulsa;
    private LinearLayoutCompat paymentBT;
    private PulsaViewModel pulsaViewModel;
    private View viewfocus;

    public PulsaAdapter(Context context, ArrayList<Pulsa> list, RelativeLayout layout_rincian,View viewfocus,AppCompatImageView closeIV,AppCompatTextView paymentTV,CoordinatorLayout layoutPulsa, AppCompatTextView pulsaTV,LinearLayoutCompat paymentBT, PulsaViewModel pulsaViewModel) {
        this.layout_rincian=layout_rincian;
        this.layoutPulsa=layoutPulsa;
        this.paymentTV=paymentTV;
        this.pulsaTV=pulsaTV;
        this.closeIV=closeIV;
        this.paymentBT=paymentBT;
        this.context = context;
        this.pulsaViewModel = pulsaViewModel;
        this.list = list;
        this.viewfocus = viewfocus;
    }
    @NonNull
    @Override
    public PulsaAdapter.PulsaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pulsa,parent,false);
        return new PulsaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PulsaAdapter.PulsaViewHolder holder, int position) {
        int pulsa = (int) list.get(position).getNominal();
        int price = (int) list.get(position).getPrice();
        String nom ="";
        if(String.valueOf(pulsa).length()==6){
            nom=String.valueOf(pulsa).substring(0,3);
        } else {
            nom=String.valueOf(pulsa).substring(0,2);
        }
        holder.nominalTV.setText(nom);
        holder.nominalTV2.setText(".000");
        holder.linear_nominal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.expand(layout_rincian,2);
                layoutPulsa.animate().alpha(0.5f);
//                viewfocus.setVisibility(View.VISIBLE);
//                viewfocus.animate().alpha(0.5f);
                pulsaTV.setText("Pulsa "+pulsa);
                paymentTV.setText("Rp. "+price);
            }
        });
        closeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.collapse(layout_rincian,2);
                viewfocus.setVisibility(View.GONE);
                layoutPulsa.animate().alpha(1f);
            }
        });
        paymentBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pulsa pulsa1 = new Pulsa(list.get(position).getCode(),list.get(position).getNominal());
                pulsaViewModel.postPulsa(pulsa1)
                        .observe((LifecycleOwner) context, pulsaResponse -> {
                            if (pulsaResponse.getCode()==200){
                                Intent intent = new Intent(context, ViewActivity.class);
                                context.startActivity(intent);
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PulsaViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView nominalTV,nominalTV2;
        LinearLayoutCompat linear_nominal;

        public PulsaViewHolder(@NonNull View itemView) {
            super(itemView);
            nominalTV=itemView.findViewById(R.id.nominalTV);
            nominalTV2=itemView.findViewById(R.id.nominal2TV);
            linear_nominal=itemView.findViewById(R.id.linear_nominal);



        }

    }
}
