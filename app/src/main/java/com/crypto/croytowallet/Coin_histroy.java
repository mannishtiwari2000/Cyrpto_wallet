package com.crypto.croytowallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.crypto.croytowallet.Adapter.Coin_Adapter;
import com.crypto.croytowallet.Adapter.Transaaction_history_adapter;
import com.crypto.croytowallet.Model.TransactionHistoryModel;
import com.crypto.croytowallet.SharedPrefernce.SharedPrefManager;
import com.crypto.croytowallet.SharedPrefernce.UserData;
import com.crypto.croytowallet.TransactionHistory.Full_Transaction_History;
import com.crypto.croytowallet.TransactionHistory.Transaction_history;
import com.crypto.croytowallet.VolleyDatabase.URLs;
import com.crypto.croytowallet.VolleyDatabase.VolleySingleton;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Coin_histroy extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_histroy);
        recyclerView = findViewById(R.id.recyclerCoin);
        imageView = findViewById(R.id.back);
        String[] data = {"Ravi", "Bhanu", "Manoj", "Sachin", "Pinku", "Suresh", "Rahul", "Harish", "Naveen", "Satish", "Manish"};
        Coin_Adapter adapter = new Coin_Adapter(data);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        back();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    public void back() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Coin_histroy.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public void onHistoryItemClickListener(int position) {
//
//        Intent intent = new Intent(Coin_histroy.this, Full_Transaction_History.class);
//        //   String result=transactionHistoryModels.get(position).getSymbol();
//       /* SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString("symbol1",result);
//        editor.commit();
//       */ startActivity(intent);
//    }
}