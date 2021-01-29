package com.crypto.croytowallet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crypto.croytowallet.Model.Coin_Model;
import com.crypto.croytowallet.R;
import com.crypto.croytowallet.fragement.Deshboard;

import java.util.List;

public class Coin_Recyler_Adapter extends RecyclerView.Adapter<Coin_Recyler_Adapter.Viewholder> {
    private List itemList;
    Context context;

    public Coin_Recyler_Adapter(List itemList) {
        this.itemList = itemList;

    }

    @NonNull
    @Override


    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_design,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Coin_Model list = (Coin_Model) itemList.get(position);
        holder.Name_coin.setText(list.getCoin_name());
        holder.change_coin.setText(list.getCoin_Change());
        holder.Current_amt.setText(list.getCoin_Current_Change());
        holder.amount_coin.setText(list.getCoin_amount());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
     TextView amount_coin,Current_amt,change_coin,Name_coin;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            amount_coin=itemView.findViewById(R.id.Amount_Coin);
            Current_amt=itemView.findViewById(R.id.Current_amount_coin);
            Name_coin=itemView.findViewById(R.id.Name_Coin);
            change_coin=itemView.findViewById(R.id.Change_Coin);

        }
    }
}
