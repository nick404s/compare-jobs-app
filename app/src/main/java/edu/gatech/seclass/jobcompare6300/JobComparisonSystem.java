package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class JobComparisonSystem {

    private final int MAX_NUMBER_OF_HOLIDAYS = 20;
    private final double MAX_INTERNET_STIPEND = 75;

    private final JobComparisonService  jobComparisonService;
    public JobComparisonSystem() {
        jobComparisonService = new JobComparisonService();
    }

    public boolean enterCurrentJob(String title, String company,
                                String companyLocation, int costOfLivingIndex,
                                double yearlySalary, double yearlyBonus,
                                int stockOptionsShares, double homeBuyingProgramFund,
                                int personalChoiceHolidays, double monthlyInternetStipend, Context context){

        JobInfo currentJob = new JobInfo (title, company,companyLocation, costOfLivingIndex,
                yearlySalary, yearlyBonus, stockOptionsShares, homeBuyingProgramFund,
                personalChoiceHolidays, monthlyInternetStipend, true);

        JobInfoDatabaseHelper db = new JobInfoDatabaseHelper(context);

        return db.create(currentJob);
    }


    public boolean editCurrentJob(String title, String company,
                               String companyLocation, int costOfLivingIndex,
                               double yearlySalary, double yearlyBonus,
                               int stockOptionsShares, double homeBuyingProgramFund,
                               int personalChoiceHolidays, double monthlyInternetStipend, Context context){

        JobInfo currentJob = new JobInfo (title, company,companyLocation, costOfLivingIndex,
                yearlySalary, yearlyBonus, stockOptionsShares, homeBuyingProgramFund,
                personalChoiceHolidays, monthlyInternetStipend, true);

        JobInfoDatabaseHelper db = new JobInfoDatabaseHelper(context);

        return db.updateCurrentJob(currentJob);
    }

    public JobInfo enterJobOffer(String title, String company,
                              String companyLocation, int costOfLivingIndex,
                              double yearlySalary, double yearlyBonus,
                              int stockOptionsShares, double homeBuyingProgramFund,
                              int personalChoiceHolidays, double monthlyInternetStipend, Context context) {

        JobInfo jobOffer = new JobInfo (title, company,companyLocation, costOfLivingIndex,
                yearlySalary, yearlyBonus, stockOptionsShares, homeBuyingProgramFund,
                personalChoiceHolidays, monthlyInternetStipend, false);

        JobInfoDatabaseHelper db = new JobInfoDatabaseHelper(context);

        if (db.create(jobOffer)){
            return jobOffer;
        } else {
            return null;
        }
    }

    public int getEnteredJobsSize(Context context){

        return new JobInfoDatabaseHelper(context).getTableSize();
    }

    public boolean isCurrentJobEntered(Context context){
        return new JobInfoDatabaseHelper(context).isCurrentJobExists();
    }


    public JobInfo findJobOfferByID(int id, Context context){
        return new JobInfoDatabaseHelper(context).getJobInfoById(id);
    }

    public boolean  adjustComparisonSettings(int yearlySalaryWeight, int yearlyBonusWeight,
                                          int stockOptionSharesWeight, int homeBuyingProgramFundWeight,
                                          int personalChoiceHolidaysWeight, int monthlyInternetStipendWeight, Context context) {

        WeightInfo weightInfo = new WeightInfo();
        weightInfo.setYearlySalaryWeight(yearlySalaryWeight);
        weightInfo.setYearlyBonusWeight(yearlyBonusWeight);
        weightInfo.setStockOptionSharesWeight(stockOptionSharesWeight);
        weightInfo.setHomeBuyingProgramFundWeight(homeBuyingProgramFundWeight);
        weightInfo.setPersonalChoiceHolidaysWeight(personalChoiceHolidaysWeight);
        weightInfo.setMonthlyInternetStipendWeight(monthlyInternetStipendWeight);

        WeightInfoDatabaseHelper db = new WeightInfoDatabaseHelper(context);

        return db.update(weightInfo);
    }

    public ArrayList<JobInfo> sortJobsByRank(Context context) {


        ArrayList<JobInfo> sortedJobs = null;

        ArrayList<JobInfo> unsortedJobs = new JobInfoDatabaseHelper(context).getAllJobsInfo();

        WeightInfo weightInfo = new WeightInfoDatabaseHelper(context).getWeightInfo();

        List<JobInfo> sortedJobOffers = unsortedJobs
                .stream()
                .sorted(Comparator.comparingDouble(jobInfo -> jobComparisonService.calculateScore((JobInfo) jobInfo, weightInfo)).reversed())
                .collect(Collectors.toList());

        sortedJobs = new ArrayList<>(sortedJobOffers);

        return sortedJobs;
    }

    public double getAdjustedValue(double value, int costOfLiving){
        return jobComparisonService.calculateAdjustedAmount(value, costOfLiving);
    }

    public boolean isValidHomeBuyingFund(double salary, double inputHomeBuyingFund){
        return inputHomeBuyingFund >= 0 && inputHomeBuyingFund <= jobComparisonService.calculateMaxPercentageSum(salary);
    }

    public boolean isValidNumberOfHolidays(int inputNumberOfHolidays){
        return inputNumberOfHolidays >= 0 && inputNumberOfHolidays <= MAX_NUMBER_OF_HOLIDAYS;
    }

    public boolean isValidInternetStipend(double inputStipend){
        return inputStipend >= 0 && inputStipend <= MAX_INTERNET_STIPEND;
    }

    public JobInfo getCurrentJob(Context context){
        return new JobInfoDatabaseHelper(context).findCurrentJob();
    }


    /**
     * Adjusts the salary and bonus for the comparison jobs
     * @param jobInfo1
     * @param jobInfo2
     * @return array list with the two jobs
     */
    public ArrayList<JobInfo>  compareTwoJobs(JobInfo jobInfo1, JobInfo jobInfo2){

        ArrayList<JobInfo> twoJobs = new ArrayList<>();

        double adjustedSalary1 = getAdjustedValue(jobInfo1.getYearlySalary(), jobInfo1.getCostOfLivingIndex());
        double adjustedBonus1 = getAdjustedValue(jobInfo1.getYearlyBonus(), jobInfo1.getCostOfLivingIndex());
        jobInfo1.setYearlySalary(adjustedSalary1);
        jobInfo1.setYearlyBonus(adjustedBonus1);
        twoJobs.add(jobInfo1);

        double adjustedSalary2 = getAdjustedValue(jobInfo2.getYearlySalary(), jobInfo2.getCostOfLivingIndex());
        double adjustedBonus2 = getAdjustedValue(jobInfo2.getYearlyBonus(), jobInfo2.getCostOfLivingIndex());
        jobInfo2.setYearlySalary(adjustedSalary2);
        jobInfo2.setYearlyBonus(adjustedBonus2);
        twoJobs.add(jobInfo2);

        return twoJobs;
    }








}
