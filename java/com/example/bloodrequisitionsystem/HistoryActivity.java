package com.example.bloodrequisitionsystem;

import static com.example.bloodrequisitionsystem.Home_Activity.hislist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodrequisitionsystem.history.AdapterHistory;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    AdapterHistory adapterHistory;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("HISTORY");
        recyclerView = findViewById(R.id.rv_history);

        layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

if(hislist.isEmpty())
{
    getSupportActionBar().setTitle("NO HISTORY AVALABLE");

}

        adapterHistory = new AdapterHistory(HistoryActivity.this,hislist);
        recyclerView.setAdapter(adapterHistory);

    }

    @Override
    public void onBackPressed() {
        hislist.clear();
        super.onBackPressed();
    }
}