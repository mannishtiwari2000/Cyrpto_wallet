package com.crypto.croytowallet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crypto.croytowallet.R;

import static android.os.Build.VERSION_CODES.R;

public class Coin_Adapter extends RecyclerView.Adapter<Coin_Adapter.Viewholder> {
Context c;
String[] data;
    public Coin_Adapter(String[] data)
    {
        this.c=c;
        this.data=data;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(com.crypto.croytowallet.R.layout.custom_transactio_history,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView transaction_status,transaction_amount,transaction_username,transaction_time;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
//            transaction_status=itemView.findViewById(R.id.transaction_status);
//            transaction_amount=itemView.findViewById(R.id.transaction_amount);
//            transaction_username=itemView.findViewById(R.id.transaction_username);
//            transaction_time=itemView.findViewById(R.id.transaction_date);
        }
    }
}
