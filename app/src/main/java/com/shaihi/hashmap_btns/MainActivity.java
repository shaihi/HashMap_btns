package com.shaihi.hashmap_btns;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText etKey, etValue;
    Button btnAdd, btnClear;
    LinearLayout llButtons;
    HashMap<String,String> dictionary = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etKey = findViewById(R.id.etKey);
        etValue = findViewById(R.id.etValue);
        btnAdd = findViewById(R.id.btnAdd);
        //Needed to add a comment here
        btnClear = findViewById(R.id.btnClear);
        llButtons = findViewById(R.id.llButtons);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = etKey.getText().toString();
                String value = etValue.getText().toString();
                dictionary.put(key,value);
                Button tmpBtn = new Button(MainActivity.this);
                tmpBtn.setText(key);
                tmpBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                    }
                });
                llButtons.addView(tmpBtn);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llButtons.removeAllViews();
                ArrayList<String> keys = new ArrayList<>(dictionary.keySet());
                keys.sort(String::compareTo);
                for(String keyItem: keys){
                    Button tmpBtn = new Button(MainActivity.this);
                    tmpBtn.setText(keyItem);
                    tmpBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, dictionary.get(keyItem), Toast.LENGTH_SHORT).show();
                        }
                    });
                    llButtons.addView(tmpBtn);
                }
            }
        });
    }
}