
package com.crypto.croytowallet.fragement;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crypto.croytowallet.Activity.Graph_layout;
import com.crypto.croytowallet.Adapter.Coin_Recyler_Adapter;
import com.crypto.croytowallet.Adapter.Crypto_currencyInfo;
import com.crypto.croytowallet.Interface.CryptoClickListner;
import com.crypto.croytowallet.Model.Coin_Model;
import com.crypto.croytowallet.Model.CrptoInfoModel;
import com.crypto.croytowallet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Wallet extends Fragment implements  CryptoClickListner{
    ArrayList<CrptoInfoModel> crptoInfoModels;
    RecyclerView WalletRecyclerView,Coin_recycler_wallet;
    RequestQueue requestQueue;
    Crypto_currencyInfo crypto_currencyInfo;
    SharedPreferences sharedPreferences;
    ArrayList<Coin_Model> item_data;
    public Wallet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_mywallet, container, false);

        WalletRecyclerView =view.findViewById(R.id.walletRecyclerView);
        crptoInfoModels=new ArrayList<CrptoInfoModel>();
        Coin_recycler_wallet = view.findViewById(R.id.RecyclerView_wallet);
        sharedPreferences=getActivity().getSharedPreferences("symbols", Context.MODE_PRIVATE);
        item_data = new ArrayList<Coin_Model>();
        crptoInfoModels=new ArrayList<CrptoInfoModel>();
        Coin_setdata();

        CryptoInfoRecyclerView();
    return view;
    }

    public void Coin_setdata()
    {
        String url="https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=bitcoin%2Cethereum&order=market_cap_desc&sparkline=false&price_change_percentage=24h";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<=jsonArray.length();i++){

                        Coin_Model data_coin=new Coin_Model();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String id=jsonObject1.getString("id");
                        String image=jsonObject1.getString("image");
                        String name=jsonObject1.getString("name");
                        String rate=jsonObject1.getString("price_change_percentage_24h");
                        String price=jsonObject1.getString("current_price");
                        String high_price=jsonObject1.getString("high_24h");
                        String low_price=jsonObject1.getString("low_24h");

                        data_coin.setCoin_name(name);
                        data_coin.setCoin_amount(price);
                        data_coin.setCoin_Change(rate);
                        data_coin.setImage(image);

//                        data_coin.setCoin_name(name);
//                        data_coin.setCoin_Current_Change(price);
//                        data_coin.setCoin_amount(rate);
//                        data_coin.setCoin_name(name);
                        item_data.add(data_coin);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//
//                crypto_currencyInfo = new Crypto_currencyInfo(crptoInfoModels,getContext(),Deshboard.this::onCryptoItemClickListener );
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
//                cryptoInfoRecyclerView.setLayoutManager(mLayoutManager);
//                cryptoInfoRecyclerView.setItemAnimator(new DefaultItemAnimator());
//                cryptoInfoRecyclerView.setAdapter(crypto_currencyInfo);

                Coin_Recyler_Adapter adapter = new Coin_Recyler_Adapter(item_data);
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
                Coin_recycler_wallet.setLayoutManager(mLayoutManager1);
                Coin_recycler_wallet.setItemAnimator(new DefaultItemAnimator());
                Coin_recycler_wallet.setAdapter(adapter);
//        Coin_Model data = new Coin_Model("$5.0","Bitcoin","-0.121","1.11");
//        itemList.add(data);
//        data=new Coin_Model("10.00","Bitcoin","+1.00001","+1.111");
//        itemList.add(data);
//        data=new Coin_Model("20.00","Bitcoin","+2.00001","-1.111");
//        itemList.add(data);


                //  Toast.makeText(getContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }

    public void CryptoInfoRecyclerView(){
        String url="https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=bitcoin%2Cethereum%2Ctether%2Cripple%2Clitecoin&order=market_cap_desc&sparkline=false&price_change_percentage=24h";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<=jsonArray.length();i++){
                        CrptoInfoModel  crptoInfoModel1= new CrptoInfoModel();
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String id=jsonObject1.getString("id");
                        String image=jsonObject1.getString("image");
                        String name=jsonObject1.getString("name");
                        String rate=jsonObject1.getString("price_change_percentage_24h");
                        String price=jsonObject1.getString("current_price");

                        crptoInfoModel1.setId(id);
                        crptoInfoModel1.setImage(image);
                        crptoInfoModel1.setName(name);
                        crptoInfoModel1.setCurrencyRate(rate);
                        crptoInfoModel1.setCurrentPrice(price);
                        crptoInfoModels.add(crptoInfoModel1);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                crypto_currencyInfo = new Crypto_currencyInfo(crptoInfoModels,getContext(), Wallet.this::onCryptoItemClickListener);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
                WalletRecyclerView.setLayoutManager(mLayoutManager);
                WalletRecyclerView.setItemAnimator(new DefaultItemAnimator());
                WalletRecyclerView.setAdapter(crypto_currencyInfo);
                //  Toast.makeText(getContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    @Override
    public void onCryptoItemClickListener(int position) {
        Intent intent = new Intent(getContext(), Graph_layout.class);
        intent.putExtra("position",position);
        startActivity(intent);

        String result=crptoInfoModels.get(position).getSymbol();
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("symbol1",result);
        editor.commit();
    }
}
