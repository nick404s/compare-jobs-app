package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class JobComparisonTableActivity extends CancelButton {

    private void addReturnToMainMenuListener() {
        Button returnToMainMenuButton = findViewById(R.id.buttonMainMenu);
        returnToMainMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            finishAffinity();
            startActivity(intent);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_comparison_table);


        Bundle extras = getIntent().getExtras();
        assert extras != null;
        JobInfo firstJobOffer = extras.getSerializable("firstJobOffer", JobInfo.class);
        JobInfo secondJobOffer = extras.getSerializable("secondJobOffer", JobInfo.class);

        if (firstJobOffer != null && secondJobOffer != null){
            ArrayList<JobInfo> comparedJobs = new JobComparisonSystem().compareTwoJobs(firstJobOffer, secondJobOffer);

            JobInfo firstJob = comparedJobs.get(0);
            JobInfo secondJob = comparedJobs.get(1);

            // Populate table with job details
            TextView firstJobTitle = findViewById(R.id.comparisonFirstJobTitle);
            firstJobTitle.setText(firstJob.getTitle());
            TextView secondJobTitle = findViewById(R.id.comparisonSecondJobTitle);
            secondJobTitle.setText(secondJob.getTitle());

            TextView firstJobDescription = findViewById(R.id.comparisonFirstJobDescription);
            firstJobDescription.setText(firstJob.getCompany());
            TextView secondJobDescription = findViewById(R.id.comparisonSecondJobDescription);
            secondJobDescription.setText(secondJob.getCompany());

            TextView firstJobLocation = findViewById(R.id.comparisonFirstJobLocation);
            firstJobLocation.setText(firstJob.getCompanyLocation());
            TextView secondJobLocation = findViewById(R.id.comparisonSecondJobLocation);
            secondJobLocation.setText(secondJob.getCompanyLocation());

            TextView firstJobYearlySalary = findViewById(R.id.comparisonFirstJobYearlySalary);
            firstJobYearlySalary.setText(String.valueOf(firstJob.getYearlySalary()));
            TextView secondJobYearlySalary = findViewById(R.id.comparisonSecondJobYearlySalary);
            secondJobYearlySalary.setText(String.valueOf(secondJob.getYearlySalary()));

            TextView firstJobYearlyBonus = findViewById(R.id.comparisonFirstJobYearlyBonus);
            firstJobYearlyBonus.setText(String.valueOf(firstJob.getYearlyBonus()));
            TextView secondJobYearlyBonus = findViewById(R.id.comparisonSecondJobYearlyBonus);
            secondJobYearlyBonus.setText(String.valueOf(secondJob.getYearlyBonus()));

            TextView firstJobStockOptions = findViewById(R.id.comparisonFirstJobYearlyStockOptions);
            firstJobStockOptions.setText(String.valueOf(firstJob.getStockOptionsShares()));
            TextView secondJobStockOptions = findViewById(R.id.comparisonSecondJobYearlyStockOptions);
            secondJobStockOptions.setText(String.valueOf(secondJob.getStockOptionsShares()));

            TextView firstJobHomeBuyingProgramFund = findViewById(R.id.comparisonFirstJobHomeBuyingProgramFund);
            firstJobHomeBuyingProgramFund.setText(String.valueOf(firstJob.getHomeBuyingProgramFund()));
            TextView secondJobHomeBuyingProgramFund = findViewById(R.id.comparisonSecondJobHomeBuyingProgramFund);
            secondJobHomeBuyingProgramFund.setText(String.valueOf(secondJob.getHomeBuyingProgramFund()));

            TextView firstJobPersonalChoiceHolidays = findViewById(R.id.comparisonFirstJobPersonalChoiceHolidays);
            firstJobPersonalChoiceHolidays.setText(String.valueOf(firstJob.getPersonalChoiceHolidays()));
            TextView secondJobPersonalChoiceHolidays = findViewById(R.id.comparisonSecondJobPersonalChoiceHolidays);
            secondJobPersonalChoiceHolidays.setText(String.valueOf(secondJob.getPersonalChoiceHolidays()));

            TextView firstJobMonthlyInternetStipend = findViewById(R.id.comparisonFirstJobMonthlyInternetStipend);
            firstJobMonthlyInternetStipend.setText(String.valueOf(firstJob.getMonthlyInternetStipend()));
            TextView secondJobMonthlyInternetStipend = findViewById(R.id.comparisonSecondJobMonthlyInternetStipend);
            secondJobMonthlyInternetStipend.setText(String.valueOf(secondJob.getMonthlyInternetStipend()));
        }

        addCancelButtonListener();
        addReturnToMainMenuListener();
    }
}