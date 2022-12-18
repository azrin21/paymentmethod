package com.example.gas4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.annotation.Documented;
import java.text.SimpleDateFormat;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView rating name,description, price, quantity;
    Button addToCard buyNow;
    ImageView addItems, removeItems;

    Toolbar toolbar;
    int totalQuantity = 1;
    int totalPrice = 0;

    //New Products
    NewProductsModel newProductsModel =null;

    //Popular product
    PopularProductsModel popularProductsModel = null;

    // show all
    ShowAllModel showAllModel = null;

    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getinstance();
        auth = FirebaseAuth.getInstance();
        Final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof NewProductsModel) {
            newProductsModel = (newProductsModel) obj;
        }else if (obj instanceof PopularProductsModel){
            popularProductsModel = (PopularProductsModel) obj;
        }else if (obj instanceof ShowAllModel){
            showAllModel = (ShowAllModel) obj;
        }

        detailedImg = findViewById(R.id.detailed_img);
        quantity = findViewById(R.id.quantity);
        name = findViewById(detailed_name);
        rating = findViewById(R.id.rating);
        description = findViewById(R.id.detailed_decs);
        price = findViewById(R.id.detailed_price);

        addToCard = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);

        addItems = findViewById(R.id.add_item);
        removeItems = findViewById(R.id.remove_item);

        // New Product
        if (newProductsModel != null){
            Glide.with(getApplication()).load(newProductsModel.getImg_url())into(detailedImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            description.setText(newProductsModel.getDecription());
            price.setText(String.valueOf(newProductsModel.getDecription()));
            name.setText(newProductsModel.getName());

            totalPrice = newProductsModel.getPrice() * totalQuantity;
        }
        // Popular product
        if (popularProductsModel != null) {
            Glide.with(getApplication()).load(popularProductsModel.getImg_url()) into(detailedImg);
            name.setText(popularProductsModel.getName());
            rating.setText(popularProductsModel.getRating());
            description.setText(popularProductsModel.getDecription());
            price.setText(String.valueOf(popularProductsModel.getDecription()));
            name.setText(popularProductsModel.getName());

            totalPrice = popularProductsModel.getPrice() * totalQuantity;
        }
        // show all products
        if (showAllModel != null) {
            Glide.with(getApplication()).load(showAllModel.getImg_url()) into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDecription());
            price.setText(String.valueOf(showAllModel.getDecription()));
            name.setText(showAllModel.getName());

            totalPrice = showAllModel.getPrice() * totalQuantity;
        }
        // Buy now
        buyNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedActivity.this,AddressActivity.class);

                if (newProductsModel != null){
                    intent.putExtra("item",newProductsModel);
                }
                if (popularProductsModel != null) {
                    intent.putExtra("item",popularProductsModel);
                }
                if (showAllModel != null) {
                    intent.putExtra("item",showAllModel);
                }

                startActivity(intent);

            }
        });

        // add to cart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtoCard();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){

                if (totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    if (newProductsModel) != null) {
                        totalPrice = newProductsModel.getPrice() * totalQuantity;
                    }
                    if (popularProductsModel) != null) {
                        totalPrice = newProductsModel.getPrice() * totalQuantity;
                    }
                    if (showAllModel) != null) {
                        totalPrice = newProductsModel.getPrice() * totalQuantity;
                    }
                }
            }
        });
    removeItems.setOnClickListener(View.OnClickListener()){
            @Override
            public void onClick(View v){

                if (totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));

                }
            }
        });
    }

    private void addtoCard() {

        String saveCurrentTime, saveCurrentDate;

        Calender calForDate = Calender.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat = currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        cartMap.put("productName", name.getText().toString());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.colection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<Documented>());

        @Override
        public void onComplete (@NonNull Task<DocumentReference> task) {
            Toast.makeText(DetailedActivity, "Added To A Cart", Toast.LENGTH_SHORT).show();
            finish();
        }
    });
}
}







    }

}