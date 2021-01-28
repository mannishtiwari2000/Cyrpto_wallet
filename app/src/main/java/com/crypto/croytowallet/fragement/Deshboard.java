package com.crypto.croytowallet.fragement;


import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crypto.croytowallet.Activity.Graph_layout;
import com.crypto.croytowallet.Activity.WalletBalance;
import com.crypto.croytowallet.Activity.WalletReceive;
import com.crypto.croytowallet.Activity.WalletScan;
import com.crypto.croytowallet.Adapter.Crypto_currencyInfo;
import com.crypto.croytowallet.Interface.CryptoClickListner;
import com.crypto.croytowallet.Model.CrptoInfoModel;
import com.crypto.croytowallet.Payment.Complate_payment;
import com.crypto.croytowallet.Payment.Enter_transaction_pin;
import com.crypto.croytowallet.Payment.Top_up_Money;
import com.crypto.croytowallet.R;
import com.crypto.croytowallet.SharedPrefernce.SharedPrefManager;
import com.crypto.croytowallet.SharedPrefernce.UserData;
import com.crypto.croytowallet.TransactionHistory.Full_Transaction_History;
import com.crypto.croytowallet.TransactionHistory.Transaction_history;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.KEYGUARD_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
public class Deshboard extends Fragment implements View.OnClickListener, CryptoClickListner {
    ArrayList<CrptoInfoModel> crptoInfoModels;
    RecyclerView cryptoInfoRecyclerView;
    RequestQueue requestQueue;
    Crypto_currencyInfo crypto_currencyInfo;
    LinearLayout lytscan,lytPay,lytWalletBalance,lytaddMoney;
    SharedPreferences sharedPreferences;



    public Deshboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_deshboard, container, false);
        cryptoInfoRecyclerView = view.findViewById(R.id.deshboardRecyclerView);

//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myKey1", MODE_PRIVATE);
//        String value = sharedPreferences.getString("value","");
//        if(value.equals("passcode"))
//        {
//            authenticateApp();
//        }
        lytscan=view.findViewById(R.id.lytScan);
        lytPay=view.findViewById(R.id.lytPay);
        lytWalletBalance=view.findViewById(R.id.lytwallet);
        lytaddMoney=view.findViewById(R.id.lytaddMoney);
        lytscan.setOnClickListener(this);
        lytPay.setOnClickListener(this);
        lytWalletBalance.setOnClickListener(this);
        lytaddMoney.setOnClickListener(this);

        crptoInfoModels=new ArrayList<CrptoInfoModel>();


        sharedPreferences=getActivity().getSharedPreferences("symbols", MODE_PRIVATE);


        CryptoInfoRecyclerView();
    //    checkBalance();

    return view;
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
                        String symbol =jsonObject1.getString("symbol");
                        String image=jsonObject1.getString("image");
                        String name=jsonObject1.getString("name");
                        String rate=jsonObject1.getString("price_change_percentage_24h");
                        String price=jsonObject1.getString("current_price");
                        String high_price=jsonObject1.getString("high_24h");
                        String low_price=jsonObject1.getString("low_24h");

                        crptoInfoModel1.setId(id);
                        crptoInfoModel1.setImage(image);
                        crptoInfoModel1.setName(name);
                        crptoInfoModel1.setCurrencyRate(rate);
                        crptoInfoModel1.setCurrentPrice(price);
                        crptoInfoModel1.setHigh_price(high_price);
                        crptoInfoModel1.setLow_price(low_price);
                        crptoInfoModel1.setSymbol(symbol);
                        crptoInfoModels.add(crptoInfoModel1);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                crypto_currencyInfo = new Crypto_currencyInfo(crptoInfoModels,getContext(),Deshboard.this::onCryptoItemClickListener );
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
                cryptoInfoRecyclerView.setLayoutManager(mLayoutManager);
                cryptoInfoRecyclerView.setItemAnimator(new DefaultItemAnimator());
                cryptoInfoRecyclerView.setAdapter(crypto_currencyInfo);
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

 public void checkBalance(){
     UserData user = SharedPrefManager.getInstance(getContext()).getUser();
     String id=user.getId();
     String url="http://13.233.136.56:8080/api/user/totalAirDrop/"+id;

//     balance=getView().findViewById(R.id.balance);

     StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
          //   Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();

             try {
                 JSONObject object=new JSONObject(response);
                 String   checkBalance=object.getString("airDrop");

                 TextView textView=getActivity().findViewById(R.id.balance);

                 textView.setText("$"+checkBalance+".00");
              //   Toast.makeText(getContext(), ""+checkBalance, Toast.LENGTH_SHORT).show();
             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {

         }
     }) {
         @Override
         protected Map<String, String> getParams() throws AuthFailureError {
             Map<String, String> params = new HashMap<>();
            /* params.put("_id", id);*/

                return params;
         }

         @Override
         public Map<String, String> getHeaders() throws AuthFailureError {
             Map<String, String> headers = new HashMap<String, String>();

             // headers.put("Authorization", "Bearer "+Token);

             return headers;
         }
     };

     requestQueue = Volley.newRequestQueue(getContext());
     requestQueue.add(stringRequest);
 }

    @Override
    public void onResume() {
        super.onResume();
        deepChangeTextColor(1);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

   /* switch (id){
        case R.id.lytScan:

            break;
    }*/

      if (id == R.id.lytScan) {
            deepChangeTextColor(1);
            startActivity(new Intent(getContext(), WalletScan.class));
            getActivity().finish();
        } else if (id == R.id.lytPay) {
            deepChangeTextColor(2);
            startActivity(new Intent(getContext(), WalletReceive.class));
            getActivity().finish();

        } else if (id == R.id.lytwallet) {
            deepChangeTextColor(3);
            startActivity(new Intent(getContext(), WalletBalance.class));
            getActivity().finish();

        }else if (id == R.id.lytaddMoney) {
          deepChangeTextColor(4);
         startActivity(new Intent(getContext(), Top_up_Money.class));
          getActivity().finish();

      }

    }

    public void deepChangeTextColor(int changeId) {
        for (int i = 1; i <= 4; i++) {
            int img = getResources().getIdentifier("img" + i, "id", getActivity().getPackageName());
            int txt = getResources().getIdentifier("txt" + i, "id", getActivity().getPackageName());

            TextView textView = getView().findViewById(txt);
            ImageView imageView = getView().findViewById(img);

            if (changeId == i) {
                imageView.setColorFilter(getResources().getColor(R.color.purple_500));
                textView.setTextColor(getResources().getColor(R.color.purple_500));
                //  textView.setVisibility(View.VISIBLE);
            } else {
                imageView.setColorFilter(getResources().getColor(R.color.toolbar_text_color));
                textView.setTextColor(getResources().getColor(R.color.toolbar_text_color));
                //  textView.setVisibility(View.GONE);
            }

        }
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
//    private void authenticateApp() {
//        //Get the instance of KeyGuardManager
//        KeyguardManager keyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
//
//        //Check if the device version is greater than or equal to Lollipop(21)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //Create an intent to open device screen lock screen to authenticate
//            //Pass the Screen Lock screen Title and Description
//            //  Intent i = keyguardManager.createConfirmDeviceCredentialIntent(getResources().getString(R.string.unlock), getResources().getString(R.string.confirm_pattern));
//            Intent i = keyguardManager.createConfirmDeviceCredentialIntent(getResources().getString(R.string.passcode_text), getResources().getString(R.string.passcode_detail));
//            try {
//                //Start activity for result
//                startActivityForResult(i, LOCK_REQUEST_CODE);
//            } catch (Exception e) {
//
//                //If some exception occurs means Screen lock is not set up please set screen lock
//                //Open Security screen directly to enable patter lock
//                Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
//                try {
//
//                    //Start activity for result
//                    startActivityForResult(intent, SECURITY_SETTING_REQUEST_CODE);
//                } catch (Exception ex) {
//
//                    //If app is unable to find any Security settings then user has to set screen lock manually
//
//                    Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

//    @Override
 /*   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCK_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //If screen lock authentication is success update text

                    Toast.makeText(getContext(), "Successfull", Toast.LENGTH_LONG).show();
                } else {
                    //If screen lock authentication is failed update text
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();

                }
                break;
            case SECURITY_SETTING_REQUEST_CODE:
                //When user is enabled Security settings then we don't get any kind of RESULT_OK
                //So we need to check whether device has enabled screen lock or not
                if (isDeviceSecure()) {
                    //If screen lock enabled show toast and start intent to authenticate user
                    Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
                    authenticateApp();
                } else {
                    //If screen lock is not enabled just update text
                    Toast.makeText(getContext(), "Not done", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    *//**
     * method to return whether device has screen lock enabled or not
     **//*
    private boolean isDeviceSecure() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        //this method only work whose api level is greater than or equal to Jelly_Bean (16)
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && keyguardManager.isKeyguardSecure();

        //You can also use keyguardManager.isDeviceSecure(); but it requires API Level 23

    }

    //On Click of button do authentication again
    public void authenticateAgain(View view) {
        authenticateApp();
    }*/
}
