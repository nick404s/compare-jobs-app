package com.nick.o4s.comparejobs;

import java.io.Serializable;

public class JobInfo implements Serializable {

    private int id;
    private String title;
    private String company;
    private String companyLocation;
    private int costOfLivingIndex;
    private double yearlySalary;
    private double yearlyBonus;
    private int stockOptionsShares;
    private double homeBuyingProgramFund;
    private int personalChoiceHolidays;
    private double monthlyInternetStipend;
    private boolean isCurrentJob;

    public JobInfo(String title, String company,
                   String companyLocation, int costOfLivingIndex,
                   double yearlySalary, double yearlyBonus,
                   int stockOptionsShares, double homeBuyingProgramFund,
                   int personalChoiceHolidays, double monthlyInternetStipend, boolean isCurrentJob){
        this.title = title;
        this.company = company;
        this.companyLocation = companyLocation;
        this.costOfLivingIndex = costOfLivingIndex;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.stockOptionsShares = stockOptionsShares;
        this.homeBuyingProgramFund = homeBuyingProgramFund;
        this.personalChoiceHolidays = personalChoiceHolidays;
        this.monthlyInternetStipend = monthlyInternetStipend;
        this.isCurrentJob = isCurrentJob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public int getCostOfLivingIndex() {
        return costOfLivingIndex;
    }

    public void setCostOfLivingIndex(int costOfLivingIndex) {
        this.costOfLivingIndex = costOfLivingIndex;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public double getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(double yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public int getStockOptionsShares() {
        return stockOptionsShares;
    }

    public void setStockOptionsShares(int stockOptionsShares) {
        this.stockOptionsShares = stockOptionsShares;
    }

    public double getHomeBuyingProgramFund() {
        return homeBuyingProgramFund;
    }

    public void setHomeBuyingProgramFund(double homeBuyingProgramFund) {
        this.homeBuyingProgramFund = homeBuyingProgramFund;
    }

    public int getPersonalChoiceHolidays() {
        return personalChoiceHolidays;
    }

    public void setPersonalChoiceHolidays(int personalChoiceHolidays) {
        this.personalChoiceHolidays = personalChoiceHolidays;
    }

    public double getMonthlyInternetStipend() {
        return monthlyInternetStipend;
    }

    public void setMonthlyInternetStipend(double monthlyInternetStipend) {
        this.monthlyInternetStipend = monthlyInternetStipend;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }
}
