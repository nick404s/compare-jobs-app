package edu.gatech.seclass.jobcompare6300;

public class JobComparisonService {

    private final int MAX_PERCENTAGE = 15;

    public double calculateScore(JobInfo jobInfo, WeightInfo weightInfo){

        int yearlySalaryWeight =1;
        int yearlyBonusWeight =1;
        int stockOptionSharesWeight =1;
        int homeBuyingProgramFundWeight =1;
        int personalChoiceHolidayWeight =1;
        int monthlyInternetStipendWeight =1;


        if(weightInfo != null){
            yearlySalaryWeight = weightInfo.getYearlySalaryWeight();
            yearlyBonusWeight =  weightInfo.getYearlyBonusWeight();
            stockOptionSharesWeight = weightInfo.getStockOptionSharesWeight();
            homeBuyingProgramFundWeight = weightInfo.getHomeBuyingProgramFundWeight();
            personalChoiceHolidayWeight = weightInfo.getPersonalChoiceHolidaysWeight();
            monthlyInternetStipendWeight = weightInfo.getMonthlyInternetStipendWeight();
        }

        int totalWeights = yearlySalaryWeight + yearlyBonusWeight +stockOptionSharesWeight
                +homeBuyingProgramFundWeight+personalChoiceHolidayWeight+ monthlyInternetStipendWeight;

        // avoid divide by 0
        if (totalWeights == 0){
            totalWeights = 1;
        }

        double AYS;
        double AYB;
        int costOfLiving = jobInfo.getCostOfLivingIndex();

        // avoid divide by zero
        if (costOfLiving == 0){
            AYS = jobInfo.getYearlySalary();
            AYB = jobInfo.getYearlyBonus();
        } else {
            AYS = (jobInfo.getYearlySalary() * 100) / costOfLiving;
            AYB = (jobInfo.getYearlyBonus() * 100) / costOfLiving;
        }

        double CSO = (double) jobInfo.getStockOptionsShares()/3;
        double HBP = jobInfo.getHomeBuyingProgramFund();
        double PCH = jobInfo.getPersonalChoiceHolidays();
        double MIS = jobInfo.getMonthlyInternetStipend();

        double jobScore = (double)yearlySalaryWeight /totalWeights * AYS +
                (double)yearlyBonusWeight /totalWeights * AYB+
                stockOptionSharesWeight *(CSO/3) +
                (double)homeBuyingProgramFundWeight/totalWeights *HBP +
                (double)personalChoiceHolidayWeight /totalWeights *(PCH *AYS/260) +
                (double)monthlyInternetStipendWeight/totalWeights *(MIS*12);

        return Math.round(jobScore * 100) / 100.0;
    }

    public double calculateAdjustedAmount(double amount, int costOfLiving){

        if (costOfLiving == 0){
            return amount;
        }
        double adjustedAmount = (amount * 100) / costOfLiving;
        return Math.round(adjustedAmount * 100) / 100.0; // round to two decimal places
    }

    public double calculateMaxPercentageSum(double amount){
        if (amount == 0){
            return 0;
        }
        return (MAX_PERCENTAGE * amount) / 100;
    }
}
