package edu.gatech.seclass.jobcompare6300;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class ComparisonSettingsActivity extends SaveCancelButton {

    private EditText YearlySalaryWeight;
    private EditText YearlyBonusWeight;
    private EditText YearlyStockOptionsWeight;
    private EditText HomeBuyingProgramFundWeight;
    private EditText PersonalChoiceHolidaysWeight;
    private EditText MonthlyInternetStipendWeight;
    @Override
    protected boolean performSave() {

        String salaryInputWeight = YearlySalaryWeight.getText().toString();
        String bonusInputWeight = YearlyBonusWeight.getText().toString();
        String stockInputWeight = YearlyStockOptionsWeight.getText().toString();
        String homeBuyingFundInputWeight = HomeBuyingProgramFundWeight.getText().toString();
        String holidaysInputWeight = PersonalChoiceHolidaysWeight.getText().toString();
        String internetInputWeight = MonthlyInternetStipendWeight.getText().toString();

        if (isValidInput(salaryInputWeight, bonusInputWeight,
                        stockInputWeight, homeBuyingFundInputWeight,
                        holidaysInputWeight, internetInputWeight)){

            int salaryWeight = Integer.parseInt(salaryInputWeight);
            int bonusWeight = Integer.parseInt(bonusInputWeight);
            int stockWeight = Integer.parseInt(stockInputWeight);
            int hbpWeight = Integer.parseInt(homeBuyingFundInputWeight);
            int holidayWeight = Integer.parseInt(holidaysInputWeight);
            int internetWeight = Integer.parseInt(internetInputWeight);

            boolean isWeightAdjusted = new JobComparisonSystem().adjustComparisonSettings(salaryWeight, bonusWeight,
                                                                                        stockWeight, hbpWeight, holidayWeight,
                                                                                        internetWeight, this);

            if (isWeightAdjusted){
                WeightInfo weightInfo = new WeightInfoDatabaseHelper(this).getWeightInfo();
                Log.d("=== adjust weight check ===", "=== salary weight: "  + weightInfo.getYearlySalaryWeight() + " ===");
                Toast.makeText(ComparisonSettingsActivity.this, "Adjusted Weight", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ComparisonSettingsActivity.this, "Couldn't Adjust Weight", Toast.LENGTH_SHORT).show();
            }
            return isWeightAdjusted;
        } else {
            Toast.makeText(ComparisonSettingsActivity.this, "Bad Input", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean isValidInput(String salaryInputWeight, String bonusInputWeight,
                                       String stockInputWeight, String homeBuyingFundInputWeight,
                                       String holidaysInputWeight, String internetInputWeight){
        boolean isValidInput = true;

        // check each input field
        if (salaryInputWeight.isBlank() || Integer.parseInt(salaryInputWeight) == 0){
            YearlySalaryWeight.setError("Invalid Salary Weight");
            isValidInput = false;
        }
        if (bonusInputWeight.isBlank() || Integer.parseInt(bonusInputWeight) == 0){
            YearlyBonusWeight.setError("Invalid Bonus Weight");
            isValidInput = false;
        }
        if (stockInputWeight.isBlank() || Integer.parseInt(stockInputWeight) == 0){
            YearlyStockOptionsWeight.setError("Invalid Stock Weight");
            isValidInput = false;
        }
        if (homeBuyingFundInputWeight.isBlank() || Integer.parseInt(homeBuyingFundInputWeight) == 0){
            HomeBuyingProgramFundWeight.setError("Invalid HomeBuying Program Fund Weight");
            isValidInput = false;
        }
        if (holidaysInputWeight.isBlank() || Integer.parseInt(holidaysInputWeight) == 0){
            PersonalChoiceHolidaysWeight.setError("Invalid Personal Choice Holidays Weight");
            isValidInput = false;
        }
        if (internetInputWeight.isBlank() || Integer.parseInt(internetInputWeight) == 0){
            MonthlyInternetStipendWeight.setError("Invalid Personal Choice Holidays Weight");
            isValidInput = false;
        }
        return isValidInput;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_settings);

        YearlySalaryWeight = findViewById(R.id.editTextCurrentJobYearlySalary);
        YearlyBonusWeight = findViewById(R.id.editTextCurrentJobYearlyBonus);
        YearlyStockOptionsWeight = findViewById(R.id.editTextCurrentJobYearlyStockOptions);
        HomeBuyingProgramFundWeight = findViewById(R.id.editTextCurrentJobHomeBuyingProgramFund);
        PersonalChoiceHolidaysWeight = findViewById(R.id.editTextCurrentJobPersonalChoiceHolidays);
        MonthlyInternetStipendWeight = findViewById(R.id.editTextCurrentJobMonthlyInternetStipend);

        addSaveCancelButtonsListeners();
    }
}