package com.example.hungrymood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.hungrymood.ReusableCodeForAll;

import java.util.HashMap;
import java.util.ArrayList;

import dagger.Reusable;

public class ChefRegistration extends AppCompatActivity {

    TextInputLayout Fname,Lname,Email,Pass,CPass,MobileNo,Address;
    Button Register, EmailBtn, Phone;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname,lname,emailid,password,confpassword,mobile,address;
    String role="Chef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_registration);

        Fname = (TextInputLayout)findViewById(R.id.FirstName);
        Lname = (TextInputLayout)findViewById(R.id.LastName);
        Email = (TextInputLayout)findViewById(R.id.Email);
        Pass = (TextInputLayout)findViewById(R.id.Pwd);
        CPass = (TextInputLayout)findViewById(R.id.Cpass);
        MobileNo = (TextInputLayout)findViewById(R.id.Mobileno);
        Address = (TextInputLayout)findViewById(R.id.add);

        Register = (Button)findViewById(R.id.ChefReg);
        EmailBtn = (Button)findViewById(R.id.email);
        Phone = (Button)findViewById(R.id.phone);



       databaseReference = firebaseDatabase.getInstance().getReference("chef");
       FAuth = FirebaseAuth.getInstance();

       Register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               fname = Fname.getEditText().getText().toString().trim();
               lname = Lname.getEditText().getText().toString().trim();
               emailid = Email.getEditText().getText().toString().trim();
               mobile = MobileNo.getEditText().getText().toString().trim();
               password = Pass.getEditText().getText().toString().trim();
               confpassword = CPass.getEditText().getText().toString().trim();
               address = Address.getEditText().getText().toString().trim();

               if (isValid()) {
                   final ProgressDialog mDialog = new ProgressDialog(ChefRegistration.this);
                   mDialog.setCancelable(false);
                   mDialog.setCanceledOnTouchOutside(false);
                   mDialog.setMessage("Registration in progress, Please wait.....");
                   mDialog.show();

                   FAuth.createUserWithEmailAndPassword(emailid, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                               databaseReference = FirebaseDatabase.getInstance().getReference("User").child(userid);
                               final HashMap<String, String> hashMap = new HashMap<>();
                               hashMap.put("Role", role);
                               databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       HashMap<String, String> hashMap1 = new HashMap<>();
                                       hashMap1.put("Mobile No", mobile);
                                       hashMap1.put("First Name", fname);
                                       hashMap1.put("Last Name", lname);
                                       hashMap1.put("EmailId", emailid);
                                       hashMap1.put("Address", address);
                                       hashMap1.put("Password", password);
                                       hashMap1.put("Confirm Password", confpassword);


                                       firebaseDatabase.getInstance().getReference("Chef")
                                               .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                               .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               mDialog.dismiss();

                                               FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<Void> task) {

                                                       if (task.isSuccessful()) {
                                                           AlertDialog.Builder builder = new AlertDialog.Builder(ChefRegistration.this);
                                                           builder.setMessage("You Have Registered! Please Verify Your Email");
                                                           builder.setCancelable(false);
                                                           builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog, int which) {
                                                                   dialog.dismiss();
                                                               }
                                                           });
                                                           AlertDialog Alert = builder.create();
                                                           Alert.show();
                                                       } else {
                                                           mDialog.dismiss();
                                                           ReusableCodeForAll.ShowAlert(ChefRegistration.this, "Error", task.getException().getMessage());
                                                       }
                                                   }
                                               });
                                           }
                                       });
                                   }
                               });
                           }
                       }
                   });
               }
           }
       });
    }

    String emailpattern = "[a-zA-Z0.9._-]+@[a-z]\\.+[a-z]+";
    public boolean isValid(){
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        MobileNo.setErrorEnabled(false);
        MobileNo.setError("");
        CPass.setErrorEnabled(false);
        CPass.setError("");
        Address.setErrorEnabled(false);
        Address.setError("");

        boolean isValid=false,isValidlname=false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false,isValidaddress=false;
        if(TextUtils.isEmpty(fname)){
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");
        }else{
            isValidname = true;
        }
        if(TextUtils.isEmpty(lname)){
            Lname.setErrorEnabled(true);
            Lname.setError("Enter Last Name");
        }else{
            isValidlname = true;
        }
        if(TextUtils.isEmpty(emailid)){
            Email.setErrorEnabled(true);
            Email.setError("Email Is Required");
        }else{
            isValidemail = true;
        }
        if(TextUtils.isEmpty(password)){
            Pass.setErrorEnabled(true);
            Pass.setError("Enter Password");
        }else{
            if(password.length()<8){
                Pass.setErrorEnabled(true);
                Pass.setError("Password is Weak");
            }else{
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(confpassword)){
            CPass.setErrorEnabled(true);
            CPass.setError("Enter Password Again");
        }else{
            if(!password.equals(confpassword)){
                CPass.setErrorEnabled(true);
                CPass.setError("Password Doesn't Match");
            }else{
                isValidconfpassword = true;
            }
        }
        if(TextUtils.isEmpty(mobile)){
            MobileNo.setErrorEnabled(true);
            MobileNo.setError("Mobile Number Is Required");
        }else{
            if(mobile.length()<10){
                MobileNo.setErrorEnabled(true);
                MobileNo.setError("Invalid Mobile Number");
            }else{
                isValidmobilenum = true;
            }
        }
        if(TextUtils.isEmpty(address)){
            Address.setErrorEnabled(true);
            Address.setError("Area Is Required");
        }else{
            isValidaddress = true;
        }

        isValid = ( isValidconfpassword && isValidpassword && isValidaddress && isValidemail && isValidmobilenum && isValidname && isValidlname) ? true : false;
        return isValid;

    }

}
