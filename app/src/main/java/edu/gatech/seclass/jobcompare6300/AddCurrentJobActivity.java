package edu.gatech.seclass.jobcompare6300;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class AddCurrentJobActivity extends SaveCancelButton {
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

    JobComparisonSystem comparisonSystem;

    protected boolean performSave() {
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


        if (isValidInput(title, company, location, livingCost, salaryInput, bonusInput,
                            stockInput, homeBuyingFundInput, holidaysInput, internetInput)){

            int costOfLiving = Integer.parseInt(livingCost);
            double yearlySalary = Double.parseDouble(salaryInput);
            double yearlyBonus = Double.parseDouble(bonusInput);
            int stockOptionsShares = Integer.parseInt(stockInput);
            double homeBuyingProgramFund = Double.parseDouble(homeBuyingFundInput);
            int personalChoiceHolidays = Integer.parseInt(holidaysInput);
            double monthlyInternetStipend = Double.parseDouble(internetInput);



            boolean saveSuccessful;

            if (comparisonSystem.isCurrentJobEntered(this)){

                saveSuccessful = comparisonSystem.editCurrentJob(title, company, location,
                        costOfLiving, yearlySalary, yearlyBonus, stockOptionsShares,
                        homeBuyingProgramFund, personalChoiceHolidays, monthlyInternetStipend, this);
                Toast.makeText(this, "Already Exists, Updating", Toast.LENGTH_SHORT).show();

            } else {
                saveSuccessful = comparisonSystem.enterCurrentJob(title, company, location,
                        costOfLiving, yearlySalary, yearlyBonus, stockOptionsShares,
                        homeBuyingProgramFund, personalChoiceHolidays, monthlyInternetStipend, this);

                Toast.makeText(this, "Current Job Entered", Toast.LENGTH_SHORT).show();
            }

            return saveSuccessful;

        } else {
            Toast.makeText(this, "Bad Input", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private boolean isValidInput(String title, String company, String location,
                                 String livingCost, String salaryInput, String bonusInput,
                                 String stockInput, String homeBuyingFundInput,
                                 String holidaysInput, String internetInput){

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_current_job);

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

        comparisonSystem = new JobComparisonSystem();

        // get the current job
        JobInfo currentJob = comparisonSystem.getCurrentJob(this);

        if (currentJob != null){
//            Toast.makeText(this, "retrieving current Job: " + currentJob.getTitle(), Toast.LENGTH_SHORT).show();

            // prepopulate the fields
            CurrentJobTitle.setText(currentJob.getTitle());
            CurrentJobDescription.setText(currentJob.getCompany());
            CurrentJobLocation.setText(currentJob.getCompanyLocation());
            CurrentJobCostOfLivingIndex.setText(currentJob.getCostOfLivingIndex() + "");
            CurrentJobYearlySalary.setText(currentJob.getYearlySalary() + "");
            CurrentJobYearlyBonus.setText(currentJob.getYearlyBonus() + "");
            CurrentJobYearlyStockOptions.setText(currentJob.getStockOptionsShares() + "");
            CurrentJobHomeBuyingProgramFund.setText(currentJob.getHomeBuyingProgramFund() + "");
            CurrentJobPersonalChoiceHolidays.setText(currentJob.getPersonalChoiceHolidays() + "");
            CurrentJobMonthlyInternetStipend.setText(currentJob.getMonthlyInternetStipend() + "");
        }

        addSaveCancelButtonsListeners();
    }
}