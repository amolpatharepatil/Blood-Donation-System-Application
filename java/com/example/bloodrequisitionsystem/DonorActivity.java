package com.example.bloodrequisitionsystem;

import static com.example.bloodrequisitionsystem.Home_Activity.rcvlist;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodrequisitionsystem.donor.AdapterDonor;

public class DonorActivity extends AppCompatActivity {

public static Context dct;
    RecyclerView.LayoutManager layoutManager;
    AdapterDonor adapterDonor;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
dct=DonorActivity.this;
getSupportActionBar().setTitle("DONATE BLOOD");
if (rcvlist.isEmpty())
{

    getSupportActionBar().setTitle("NO BLOOD REQUEST AVALABLE");
}
        recyclerView = findViewById(R.id.rv_donor);
        layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);




        adapterDonor = new AdapterDonor(DonorActivity.this,rcvlist);

        recyclerView.setAdapter(adapterDonor);

    }

    @Override
    protected void onRestart() {
        adapterDonor.notifyDataSetChanged();
        recyclerView.setAdapter(adapterDonor);
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        rcvlist.clear();
        super.onBackPressed();
    }
}