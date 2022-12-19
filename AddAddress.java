package com.example.gas4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import org.w3c.dom.Document;

import java.util.HashMap;

public class AddAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        addAddressBtn.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onCLick(View v){
         String userName = name.getText().toString();
         String userCity = city.getText().toString();
         String userAddress = address.getText().toString();
         String userCode = code.getText().toString();
         String userNumber = number.getText().toString();

         String final_address =" ";

         if (!userName.isEmpty()){
             final_address+=userName+", ";
         }
         if (!userCity .isEmpty()){
             final_address+=userCity +", ";
         }
         if (!userAddress.isEmpty()){
             final_address+=userAddress+", ";
         }
         if (!userCode.isEmpty()){
             final_address+=userCode+", ";
         }
         if (!userNumber.isEmpty()){
             final_address+=userNumber+", ";
         }
         if (!userName.isEmpty() && !userCity.isEmpty() && !userAddress.isEmpty() && !userCode.isEmpty() && userNumber.isEmpty()){
             Map<String,String> map = new HashMap<>();
             map.put("userAddress", final_address);

             fireatore.collection("CurrentUser").document(auth.getCurrentUser).getUid())
                      .collection("Address")add(map).addCompleteListener(new OnCompleteListener<DocumentReferences>());

             @Override
                 public void onComplete(@NonNull Task<DocumentReference> task){
                    if (task.isSuccessful()){
                         Toast.makeText(AddAddressActivity.this,"Adress Added", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(AddAddressActivity.this,DetailedActivity.class));
                         finish();
             }
         });

     } else{
         Toast.makeText(AddAddressActivity.this, "Kindly Fill All FIeld", Toast.LENGTH_SHORT).show();
            }



            }
     }
    }
}