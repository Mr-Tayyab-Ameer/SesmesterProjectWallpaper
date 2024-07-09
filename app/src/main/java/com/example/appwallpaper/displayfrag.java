package com.example.appwallpaper;
import android.app.Fragment;
import android.annotation.SuppressLint;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class displayfrag extends AppCompatActivity {
    private Button temp1;
    private Button temp2;
    private Button temp3;
    private Button temp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Log.d("MainActivity", "onCreate: Activity created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        Intent i=getIntent();
        temp1 = findViewById(R.id.temp1);
        temp2 = findViewById(R.id.temp2);
        temp3 = findViewById(R.id.temp3);
        temp4 = findViewById(R.id.temp4);
        temp1.setOnClickListener(new View.OnClickListener() {
            //  @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                Toast.makeText(displayfrag.this,"Method call", Toast.LENGTH_SHORT).show();
                if (savedInstanceState == null) {
                    FragmentManager manager= getFragmentManager();
                    //  template1 first = new template1();
                    FragmentTransaction trans = manager.beginTransaction();
                    template1 first = new template1();
                    trans.replace(R.id.lis, first, "firsttemplate");
                    trans.addToBackStack("ftemp");
                    trans.commit();
                }
                else
                    Toast.makeText(displayfrag.this,"Method call but activity not", Toast.LENGTH_SHORT).show();
            }
        });
        temp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(displayfrag.this,"Method call", Toast.LENGTH_SHORT).show();
                FragmentManager manager= getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                template2 forth = new template2();
                trans.replace(R.id.lis, forth, "secondtemplate");
                trans.addToBackStack("stemp");
                trans.commit();

            }
        });
        temp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(displayfrag.this,"Method call", Toast.LENGTH_SHORT).show();

                FragmentManager manager= getFragmentManager();

                FragmentTransaction trans = manager.beginTransaction();
                template3 third= new template3();
                trans.replace(R.id.lis,third, "thirdtemplate");
                trans.addToBackStack("ttemp");
                trans.commit();

            }
        });
        temp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Toast.makeText(displayfrag.this,"Method call", Toast.LENGTH_SHORT).show();
                FragmentManager manager= getFragmentManager();

                FragmentTransaction trans = manager.beginTransaction();
                template4 forth = new template4();
                trans.replace(R.id.lis, forth, "fourthtemplate");
                trans.addToBackStack("forthtemp");
                trans.commit();

            }
        });

    }
}

