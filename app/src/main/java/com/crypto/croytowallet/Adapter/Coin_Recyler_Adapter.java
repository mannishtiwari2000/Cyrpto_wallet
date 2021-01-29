package com.crypto.croytowallet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crypto.croytowallet.Model.Coin_Model;
import com.crypto.croytowallet.Model.CrptoInfoModel;
import com.crypto.croytowallet.R;
import com.crypto.croytowallet.fragement.Deshboard;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Coin_Recyler_Adapter extends RecyclerView.Adapter<Coin_Recyler_Adapter.Viewholder> {
    ArrayList<Coin_Model> coin_models;
    Context context;

    public Coin_Recyler_Adapter(ArrayList<Coin_Model> coin_models) {
        this.coin_models = coin_models;
    }

    public Coin_Recyler_Adapter() {
    }

    @NonNull
    @Override


    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_design,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        String iconname= coin_models.get(position).getCoin_name();
        String rate= coin_models.get(position).getCoin_Change();
       // String price=coin_models.get(position).getCoin_Current_Change();
        String Amount=coin_models.get(position).getCoin_amount();
        String icon=coin_models.get(position).getImage();
        Picasso.get().load(icon).into(holder.imageView);
        holder.amount_coin.setText("$"+Amount);
        holder.Rate_coin.setText(rate);
//        holder.Rate_coin.setText(price);
        holder.Name_coin.setText(iconname);

//        holder.Rate_coin.setTextColor(coin_models.get(position).getCoin_Change().contains("-")?
//                context.getResources().getColor(R.color.red): context.getResources().getColor(R.color.green)  );
//        holder.percentage.setTextColor(crptoInfoModels.get(position).getCurrencyRate().contains("-")?
//                context.getResources().getColor(R.color.red): context.getResources().getColor(R.color.green) );


//        holder.Current_amt.setText(list.getCoin_Current_Change());
//        holder.amount_coin.setText(list.getCoin_amount());
//
//        holder.Current_amt.setText("$"+Amount);
//        holder.Rate_coin.setText(rate);


    }

    @Override
    public int getItemCount() {
        return coin_models.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
     TextView amount_coin,Current_amt,Rate_coin,Name_coin;
        ImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            amount_coin=itemView.findViewById(R.id.Amount_Coin);
         //   Current_amt=itemView.findViewById(R.id.Current_amount_coin);
            Name_coin=itemView.findViewById(R.id.Name_Coin);
            Rate_coin=itemView.findViewById(R.id.Coin_Rate);
          imageView=itemView.findViewById(R.id.Currency_icon);

        }
    }
}
