package com.example.gas4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Address;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.core.view.QuerySpec;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress{

    Button addAddress;
    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Button paymentBtn;;
    Toolbar toolbar;
    String Address = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity address);

        toolbar findViewById (R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get data from detailed activity
        Object obj = getIntent().getSerializableExtra("item");

        firestore FirebaseFirestore.getInstance();
        auth FirebaseAuth.getInstance();

        recyclerview = findViewById(R.id.address_recycler);
        paymentBtn = findViewById(R.id.payment_btn);
        addAddress = findViewById(R.id.add_address_btn);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModelList = new ArrayList<>();

        addressAdapter = new AddressAdapter(getApplicationContext(), addressModelList, this);
        recyclerview.setAdapter(addressAdapter);

        Firesture.collection("CurrentUser").document(auth.getCurrentUser().getuid())
                .collection("Address").get().addCompleteListener(new OnCompleteListere<QuerySpec>);

        @Override
        public void onComplete (@NonNull TaskQuerySnapshot > task) {

            if (task.isSuccessful()) {
                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                    AddressModel addressmodel = doc.toObject(Addressmodel.clss);
                    AddressModelList.add(addressModel);
                    addressAdapter.notifyDataSetChanged();
                }
            }
        }
    });
    paymentBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
            double amount = 0.0;

            if (obj instanceof NewProductsModel){
                NewProductsModel newProductsModel = NewProductsModel) obj;
                amount = newProductsModel.getPrice();
            }if (obj instanceaf PopularProductsModel){
                PopularProductsModel popularProductsModel = (PopularProductsModel) obj;
                amount = popularProductsModel.getPrice();
            }if (obj instanceof ShowAllModel){
                    ShowAllModel showAllModel = (ShowAllModel) obj;
                    amount = showAllModel.getPrice();
            }
            Intent intent = new Intent(AddressActivity.this, PaymentActivity.class);
            intent.putExtra("amount", amount);
            startActivity(intent);
        }
        });

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));

            });

        }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
    }
}