package com.nick.o4s.comparejobs;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class AddJobOfferActivity extends SaveCancelButton {


    private EditText CurrentJobTitle;
    private EditText CurrentJobDescription;
    private EditText CurrentJobLocation;
    private EditText CurrentJobCostOfLivingIndex;
    private EditText CurrentJobYearlySalary;
    private EditText CurrentJobYearlyBonus;
    private EditText CurrentJobYearlyStockOptions;
    private EditText CurrentJobHomeBuyingProgramFund;
    private EditText CurrentJobPersonalChoiceHolidays;
    private EditText CurrentJobMonthlyInternetStipend;
    private JobInfo enteredJobInfo; // JobInfo object holding the most recently saved job offer
//    private boolean CurrentJobisCurrentJob = false;
    protected boolean performSave() {

        enteredJobInfo = null;

        String title = CurrentJobTitle.getText().toString();
        String company = CurrentJobDescription.getText().toString();
        String location = CurrentJobLocation.getText().toString();
        String livingCost = CurrentJobCostOfLivingIndex.getText().toString();
        String salaryInput = CurrentJobYearlySalary.getText().toString();
        String bonusInput = CurrentJobYearlyBonus.getText().toString();
        String stockInput = CurrentJobYearlyStockOptions.getText().toString();
        String homeBuyingFundInput = CurrentJobHomeBuyingProgramFund.getText().toString();
        String holidaysInput = CurrentJobPersonalChoiceHolidays.getText().toString();
        String internetInput = CurrentJobMonthlyInternetStipend.getText().toString();


        JobComparisonSystem comparisonSystem = new JobComparisonSystem();

        if (isValidInput(title, company, location, livingCost, salaryInput, bonusInput,
                stockInput, homeBuyingFundInput, holidaysInput, internetInput, comparisonSystem)){

            int costOfLiving = Integer.parseInt(livingCost);
            double yearlySalary = Double.parseDouble(salaryInput);
            double yearlyBonus = Double.parseDouble(bonusInput);
            int stockOptionsShares = Integer.parseInt(stockInput);
            double homeBuyingProgramFund = Double.parseDouble(homeBuyingFundInput);
            int personalChoiceHolidays = Integer.parseInt(holidaysInput);
            double monthlyInternetStipend = Double.parseDouble(internetInput);

            JobInfo jobInfo = comparisonSystem.enterJobOffer(title, company, location,
                    costOfLiving, yearlySalary, yearlyBonus,
                    stockOptionsShares, homeBuyingProgramFund, personalChoiceHolidays,
                    monthlyInternetStipend, AddJobOfferActivity.this);

            boolean isJobOfferEntered = jobInfo != null;
            if (isJobOfferEntered){
                Toast.makeText(AddJobOfferActivity.this, "Job Offer Saved", Toast.LENGTH_SHORT).show();
                enteredJobInfo = jobInfo;
            } else {
                Toast.makeText(AddJobOfferActivity.this, "Couldn't Save the Job Offer", Toast.LENGTH_SHORT).show();
                enteredJobInfo = null;
            }

            return isJobOfferEntered;

        } else {
            Toast.makeText(this, "Bad Input", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean isValidInput(String title, String company, String location,
                                 String livingCost, String salaryInput, String bonusInput,
                                 String stockInput, String homeBuyingFundInput,
                                 String holidaysInput, String internetInput, JobComparisonSystem comparisonSystem){

        boolean isValidInput = true;

        // check each input field
        if (title.isBlank()){
            CurrentJobTitle.setError("Invalid Title");
            isValidInput = false;
        }
        if (company.isBlank()){
            CurrentJobDescription.setError("Invalid Company");
            isValidInput = false;
        }
        if (location.isBlank()){
            CurrentJobLocation.setError("Invalid Location");
            isValidInput = false;
        }
        if (livingCost.isBlank()){
            CurrentJobCostOfLivingIndex.setError("Invalid Living Cost Index");
            isValidInput = false;
        }
        if (salaryInput.isBlank()){
            CurrentJobYearlySalary.setError("Invalid Salary");
            isValidInput = false;
        }
        if (bonusInput.isBlank()){
            CurrentJobYearlyBonus.setError("Invalid Bonus");
            isValidInput = false;
        }
        if (stockInput.isBlank()){
            CurrentJobYearlyStockOptions.setError("Invalid Stocks Options");
            isValidInput = false;
        }
        if (homeBuyingFundInput.isBlank() ||
                !comparisonSystem.isValidHomeBuyingFund(Double.parseDouble(salaryInput), Double.parseDouble(homeBuyingFundInput))){
            CurrentJobHomeBuyingProgramFund.setError("Invalid HomeBuying Program Fund");
            isValidInput = false;
        }
        if (holidaysInput.isBlank() || !comparisonSystem.isValidNumberOfHolidays(Integer.parseInt(holidaysInput))){
            CurrentJobPersonalChoiceHolidays.setError("Invalid Personal Choice Holidays");
            isValidInput = false;
        }
        if (internetInput.isBlank() || !comparisonSystem.isValidInternetStipend(Double.parseDouble(internetInput))){
            CurrentJobMonthlyInternetStipend.setError("Invalid Internet Stipend");
            isValidInput = false;
        }
        return isValidInput;
    }

    /**
     * Clear all the fields
     */
    protected void clearFields() {
        CurrentJobTitle.setText("");
        CurrentJobDescription.setText("");
        CurrentJobLocation.setText("");
        CurrentJobCostOfLivingIndex.setText("");
        CurrentJobYearlySalary.setText("");
        CurrentJobYearlyBonus.setText("");
        CurrentJobYearlyStockOptions.setText("");
        CurrentJobHomeBuyingProgramFund.setText("");
        CurrentJobPersonalChoiceHolidays.setText("");
        CurrentJobMonthlyInternetStipend.setText("");
        enteredJobInfo = null;
    }

    @Override
    protected void addSaveButtonListener() {
        saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(
                v -> {
                    boolean saveSuccessful = performSave();
                    if (saveSuccessful){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setNeutralButton(R.string.compareWithCurrentJob, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        JobComparisonSystem comparisonSystem = new JobComparisonSystem();
                                        Intent intent = new Intent(AddJobOfferActivity.this, JobComparisonTableActivity.class);
                                        JobInfo currentJob = comparisonSystem.getCurrentJob(AddJobOfferActivity.this);
                                        // Since we can only get to this point if a job was saved, (and there's only one instance of the app)
                                        JobInfo newJob = enteredJobInfo;

                                        intent.putExtra("firstJobOffer", currentJob);
                                        intent.putExtra("secondJobOffer", newJob);
                                        finish();
                                        startActivity(intent);

                                    }
                                })
                                .setNegativeButton(R.string.enterAnotherOffer, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        clearFields();
                                    }
                                })
                                .setPositiveButton(R.string.returnToMainMenu, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                });
                        // Create the AlertDialog object and return it.
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_offer);

        CurrentJobTitle = findViewById(R.id.editTextCurrentJobTitle);
        CurrentJobDescription = findViewById(R.id.editTextCurrentJobDescription);
        CurrentJobLocation = findViewById(R.id.editTextCurrentJobLocation);
        CurrentJobCostOfLivingIndex = findViewById(R.id.editTextCurrentJobCostOfLivingIndex);
        CurrentJobYearlySalary = findViewById(R.id.editTextCurrentJobYearlySalary);
        CurrentJobYearlyBonus = findViewById(R.id.editTextCurrentJobYearlyBonus);
        CurrentJobYearlyStockOptions = findViewById(R.id.editTextCurrentJobYearlyStockOptions);
        CurrentJobHomeBuyingProgramFund = findViewById(R.id.editTextCurrentJobHomeBuyingProgramFund);
        CurrentJobPersonalChoiceHolidays = findViewById(R.id.editTextCurrentJobPersonalChoiceHolidays);
        CurrentJobMonthlyInternetStipend = findViewById(R.id.editTextCurrentJobMonthlyInternetStipend);

        addSaveCancelButtonsListeners();
    }
}