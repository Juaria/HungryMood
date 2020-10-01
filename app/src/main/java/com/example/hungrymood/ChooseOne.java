package com.example.hungrymood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseOne extends AppCompatActivity {

    Button Chef, Customer, DeliveryPerson;
    Intent intent;
    String type;
    ConstraintLayout bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg2), 3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        bgimage = findViewById(R.id.bg2);
        bgimage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();


        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();


        Chef = (Button) findViewById(R.id.btnchef);
        Customer = (Button) findViewById(R.id.btncustomer);
        DeliveryPerson = (Button) findViewById(R.id.btndelivery);

        Chef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("Email")) {
                    Intent LoginEmailChef = new Intent(ChooseOne.this, ChefLogin.class);
                    startActivity(LoginEmailChef);
                    finish();
                }
                if (type.equals("Phone")) {
                    Intent LoginPhoneChef = new Intent(ChooseOne.this, ChefLoginPhone.class);
                    startActivity(LoginPhoneChef);
                    finish();
                }
                if (type.equals("SignUp")) {
                    Intent RegisterChef = new Intent(ChooseOne.this, ChefRegistration.class);
                    startActivity(RegisterChef);
                }
            }

        });
        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("Email")) {
                    Intent LoginEmailCustomer= new Intent(ChooseOne.this, CustomerLogin.class);
                    startActivity(LoginEmailCustomer);
                    finish();
                }
                if (type.equals("Phone")) {
                    Intent LoginPhoneCustomer = new Intent(ChooseOne.this, CustomerLoginphone.class);
                    startActivity(LoginPhoneCustomer);
                    finish();
                }
                if (type.equals("SignUp")) {
                    Intent RegisterCustomer = new Intent(ChooseOne.this, CustomerRegistration.class);
                    startActivity(RegisterCustomer);
                }
            }
        });
        DeliveryPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("Email")) {
                    Intent LoginEmailDel = new Intent(ChooseOne.this, DelLogin.class);
                    startActivity(LoginEmailDel);
                    finish();
                }
                if (type.equals("Phone")) {
                    Intent DelPhoneLogin = new Intent(ChooseOne.this, DelLoginPhone.class);
                    startActivity(DelPhoneLogin);
                    finish();
                }
                if (type.equals("SignUp")) {
                    Intent RegisterDel= new Intent(ChooseOne.this, DelRegistration.class);
                    startActivity(RegisterDel);
                }
            }
        });
    }
}