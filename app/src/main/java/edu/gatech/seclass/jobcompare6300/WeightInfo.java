package edu.gatech.seclass.jobcompare6300;

public class WeightInfo {

    private int id;
    private int yearlySalaryWeight;
    private int yearlyBonusWeight;
    private int stockOptionSharesWeight;
    private int homeBuyingProgramFundWeight;
    private int personalChoiceHolidaysWeight;
    private int monthlyInternetStipendWeight;

    public WeightInfo() {
        this.yearlySalaryWeight = 1;
        this.yearlyBonusWeight = 1;
        this.stockOptionSharesWeight = 1;
        this.homeBuyingProgramFundWeight = 1;
        this.personalChoiceHolidaysWeight = 1;
        this.monthlyInternetStipendWeight = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public void setYearlySalaryWeight(int yearlySalaryWeight) {
        this.yearlySalaryWeight = yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public void setYearlyBonusWeight(int yearlyBonusWeight) {
        this.yearlyBonusWeight = yearlyBonusWeight;
    }

    public int getStockOptionSharesWeight() {
        return stockOptionSharesWeight;
    }

    public void setStockOptionSharesWeight(int stockOptionSharesWeight) {
        this.stockOptionSharesWeight = stockOptionSharesWeight;
    }

    public int getHomeBuyingProgramFundWeight() {
        return homeBuyingProgramFundWeight;
    }

    public void setHomeBuyingProgramFundWeight(int homeBuyingProgramFundWeight) {
        this.homeBuyingProgramFundWeight = homeBuyingProgramFundWeight;
    }

    public int getPersonalChoiceHolidaysWeight() {
        return personalChoiceHolidaysWeight;
    }

    public void setPersonalChoiceHolidaysWeight(int personalChoiceHolidaysWeight) {
        this.personalChoiceHolidaysWeight = personalChoiceHolidaysWeight;
    }

    public int getMonthlyInternetStipendWeight() {
        return monthlyInternetStipendWeight;
    }

    public void setMonthlyInternetStipendWeight(int monthlyInternetStipendWeight) {
        this.monthlyInternetStipendWeight = monthlyInternetStipendWeight;
    }
}
