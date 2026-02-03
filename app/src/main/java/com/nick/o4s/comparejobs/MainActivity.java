package com.nick.o4s.comparejobs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button addCurrentJobButton;
    Button modifyComparisonSettingsButton;
    Button addJobOfferButton;
    Button viewJobOfferListButton;
    Button resetDBButton;

    private boolean setViewJobOfferListButtonEnabled() {
        JobComparisonSystem jobComparisonSystem = new JobComparisonSystem();
        int numJobs = jobComparisonSystem.getEnteredJobsSize(viewJobOfferListButton.getContext());
        boolean enabled = numJobs > 1;
        viewJobOfferListButton.setEnabled(enabled);
        return enabled;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewJobOfferListButtonEnabled();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCurrentJobButton = findViewById(R.id.buttonCurrentJob);
        modifyComparisonSettingsButton = findViewById(R.id.buttonComparisonSettings);
        addJobOfferButton = findViewById(R.id.buttonAddJobOffer);
        viewJobOfferListButton = findViewById(R.id.buttonCompareJobList);
//        resetDBButton = findViewById(R.id.buttonResetDB);
//
//        resetDBButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // delete the db
//                MainActivity.this.deleteDatabase("JobComparisonDatabase");
//                setViewJobOfferListButtonEnabled();
//            }
//        });

        addCurrentJobButton.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this, AddCurrentJobActivity.class);
                    startActivity(i);
                }
        );
        addJobOfferButton.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this, AddJobOfferActivity.class);
                    startActivity(i);
                }
        );
        modifyComparisonSettingsButton.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this, ComparisonSettingsActivity.class);
                    startActivity(i);
                }
        );

        setViewJobOfferListButtonEnabled();
        viewJobOfferListButton.setOnClickListener(
                v -> {
                    Intent i = new Intent(MainActivity.this, ViewJobOfferListActivity.class);
                    startActivity(i);
                }
        );


    }
}