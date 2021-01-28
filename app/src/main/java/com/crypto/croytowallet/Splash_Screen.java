package com.crypto.croytowallet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.crypto.croytowallet.login.Login;

public class Splash_Screen extends AppCompatActivity {
    private static  int SPLASH_SCREEN=1500;
    ImageView imageView,logo,left,right;
    LinearLayout layout;
    private static final int LOCK_REQUEST_CODE = 221;
    private static final int SECURITY_SETTING_REQUEST_CODE = 233;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        logo=findViewById(R.id.logo);
        layout=findViewById(R.id.splash);
        imageView=findViewById(R.id.image_view);
        left=findViewById(R.id.left_image);
        right=findViewById(R.id.right_image);

        SharedPreferences sharedPreferences = getSharedPreferences("myKey1", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        if(value.equals("passcode"))
        {
            authenticateApp();
        }

        Animation animation_left = AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        left.setAnimation(animation_left);
        Animation animation_right = AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        right.setAnimation(animation_right);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.toptobottom);
        layout.setAnimation(animation);
//        imageView.setAnimation(animation);
//        logo.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_Screen.this, Login.class));
                finish();
            }
        },SPLASH_SCREEN);
    }
    private void authenticateApp() {
        //Get the instance of KeyGuardManager
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        //Check if the device version is greater than or equal to Lollipop(21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Create an intent to open device screen lock screen to authenticate
            //Pass the Screen Lock screen Title and Description
            //  Intent i = keyguardManager.createConfirmDeviceCredentialIntent(getResources().getString(R.string.unlock), getResources().getString(R.string.confirm_pattern));
            Intent i = keyguardManager.createConfirmDeviceCredentialIntent(getResources().getString(R.string.passcode_text), getResources().getString(R.string.passcode_detail));
            try {
                //Start activity for result
                startActivityForResult(i, LOCK_REQUEST_CODE);
            } catch (Exception e) {

                //If some exception occurs means Screen lock is not set up please set screen lock
                //Open Security screen directly to enable patter lock
                Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                try {

                    //Start activity for result
                    startActivityForResult(intent, SECURITY_SETTING_REQUEST_CODE);
                } catch (Exception ex) {

                    //If app is unable to find any Security settings then user has to set screen lock manually

                    Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCK_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //If screen lock authentication is success update text

                    Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_LONG).show();
                } else {
                    //If screen lock authentication is failed update text
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();

                }
                break;
            case SECURITY_SETTING_REQUEST_CODE:
                //When user is enabled Security settings then we don't get any kind of RESULT_OK
                //So we need to check whether device has enabled screen lock or not
                if (isDeviceSecure()) {
                    //If screen lock enabled show toast and start intent to authenticate user
                    Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
                    authenticateApp();
                } else {
                    //If screen lock is not enabled just update text
                    Toast.makeText(this, "Not done", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    /**
     * method to return whether device has screen lock enabled or not
     **/
    private boolean isDeviceSecure() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        //this method only work whose api level is greater than or equal to Jelly_Bean (16)
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && keyguardManager.isKeyguardSecure();

        //You can also use keyguardManager.isDeviceSecure(); but it requires API Level 23

    }

    //On Click of button do authentication again
    public void authenticateAgain(View view) {
        authenticateApp();
    }
}