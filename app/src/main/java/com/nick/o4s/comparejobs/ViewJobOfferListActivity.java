package com.nick.o4s.comparejobs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ViewJobOfferListActivity extends CancelButton implements RecyclerViewInterface {

    JobOfferListCustomAdapter customAdapter = new JobOfferListCustomAdapter(
            new ArrayList<>(), this
    );


    private void addCompareButtonListener() {
        Button compareButton = findViewById(R.id.buttonCompare);
        compareButton.setOnClickListener(
                v -> {
                    ArrayList<JobInfo> selectedJobs = customAdapter.getSelectedJobs();
                    if (selectedJobs.size() == 2) {
                        Intent intent = new Intent(this, JobComparisonTableActivity.class);
                        intent.putExtra("firstJobOffer", selectedJobs.get(0));
                        intent.putExtra("secondJobOffer", selectedJobs.get(1));
                        startActivity(intent);
                    }
                });
    }

    private void updateCompareButtonState() {
        Button compareButton = findViewById(R.id.buttonCompare);
        compareButton.setEnabled(customAdapter.selectedJobsPositions.size() == 2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_offer_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(customAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<JobInfo> rankedJobs = new JobComparisonSystem().sortJobsByRank(this);

//        JobInfoDatabaseHelper databaseHelper = new JobInfoDatabaseHelper(this);
//        ArrayList<JobInfo> jobOffers = databaseHelper.getAllJobsInfo();
//
//        // Update the adapter with the loaded job offers
//        customAdapter.updateJobOffers(jobOffers);

        customAdapter.updateJobOffers(rankedJobs);

        addCancelButtonListener();
        addCompareButtonListener();
        updateCompareButtonState();

    }

    @Override
    public void onItemClick(int position) {

        customAdapter.selectJob(position);

        // If two jobs are selected, enable the compare button
        updateCompareButtonState();
    }
}