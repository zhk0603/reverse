package com.neo.crackme0201;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.neo.crackme0201.databinding.ActivityLoginBinding;
import com.neo.crackme0201.databinding.ActivityMainBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkSn(binding.userName.getText().toString().trim(), binding.regCode.getText().toString().trim())){
                    Toast.makeText(MainActivity.this, "注册码错误", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "注册码正确", Toast.LENGTH_SHORT).show();
                    setTitle("已注册");
                }
            }
        });
    }

    private boolean checkSn(String userName, String sn) {
        try {
            if (userName == null || userName.length() == 0) {
                return false;
            }

            if (sn == null || sn.length() == 0) {
                return false;
            }

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(userName.getBytes());
            byte[] bytes = md5.digest();
            String hexStr = bytesToHexString(bytes);
            if (!hexStr.equalsIgnoreCase(sn)) {
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            return false;
        }

        return true;

    }

    private static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}