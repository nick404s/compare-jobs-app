package com.nick.o4s.comparejobs;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WeightInfoDatabaseHelper extends DatabaseHelper{

    private final int THE_ID = 333; // Any number will do
    public WeightInfoDatabaseHelper(Context context) {
        super(context);
        // insert the default weight right away to db
        if (getTableSize() == 0){
            insertDefaultWeight();
        }
    }


    /**
     * Inserts the default weight in DB
     */
    private void insertDefaultWeight(){

        ContentValues contentValues = new ContentValues();

        final int defaultValue = 1;
        contentValues.put(COLUMN_NAME_WEIGHT_ID, THE_ID);
        contentValues.put(COLUMN_NAME_SALARY_WEIGHT, defaultValue);
        contentValues.put(COLUMN_NAME_BONUS_WEIGHT, defaultValue);
        contentValues.put(COLUMN_NAME_SHARES_WEIGHT, defaultValue);
        contentValues.put(COLUMN_NAME_HOME_BUYING_WEIGHT, defaultValue);
        contentValues.put(COLUMN_NAME_HOLIDAYS_WEIGHT, defaultValue);
        contentValues.put(COLUMN_NAME_INTERNET_STIPEND_WEIGHT, defaultValue);



        // open the database connection
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NAME_WEIGHT, null, contentValues);

        db.close();
    }

    /**
     * Updates the current WeightInfo in DB
     * @param weightInfo object
     * @return true/false
     */
    public boolean update(WeightInfo weightInfo){


        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_SALARY_WEIGHT, weightInfo.getYearlySalaryWeight());
        contentValues.put(COLUMN_NAME_BONUS_WEIGHT, weightInfo.getYearlyBonusWeight());
        contentValues.put(COLUMN_NAME_SHARES_WEIGHT, weightInfo.getStockOptionSharesWeight());
        contentValues.put(COLUMN_NAME_HOME_BUYING_WEIGHT, weightInfo.getHomeBuyingProgramFundWeight());
        contentValues.put(COLUMN_NAME_HOLIDAYS_WEIGHT, weightInfo.getPersonalChoiceHolidaysWeight());
        contentValues.put(COLUMN_NAME_INTERNET_STIPEND_WEIGHT, weightInfo.getMonthlyInternetStipendWeight());
        // open the database connection
        SQLiteDatabase db = this.getWritableDatabase();

//        boolean isUpdated = db.update(TABLE_NAME_WEIGHT, contentValues, "id ='" + weightInfo.getId() + "'", null) > 0;
        boolean isUpdated = db.update(TABLE_NAME_WEIGHT, contentValues,   COLUMN_NAME_WEIGHT_ID + "='" + THE_ID + "'", null) > 0;
        db.close();

        return isUpdated;

    }

    /**
     * Returns the current weight from DB
     * @return weightInfo
     */
    //    public WeightInfo getWeightInfo(int id){
    public WeightInfo getWeightInfo(){

        WeightInfo weightInfo = null;

//        String sqlQuery = "SELECT * FROM Weight WHERE id = " + id;
        String sqlQuery = "SELECT * FROM " + TABLE_NAME_WEIGHT + " WHERE " + COLUMN_NAME_WEIGHT_ID + "=" + THE_ID;
        // open the database connection
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);


        if (cursor.moveToFirst()){
            // get the values
            int weightId = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_WEIGHT_ID)));
            int salaryWeight = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_SALARY_WEIGHT)));
            int bonusWeight = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_BONUS_WEIGHT)));
            int sharesWeight = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_SHARES_WEIGHT)));
            int homeBuyingWeight = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOME_BUYING_WEIGHT)));
            int holidaysWeight = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOLIDAYS_WEIGHT)));
            int internetStipendWeight = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_INTERNET_STIPEND_WEIGHT)));

            // set the values for return
            weightInfo = new WeightInfo();
            weightInfo.setId(weightId);
            weightInfo.setYearlySalaryWeight(salaryWeight);
            weightInfo.setYearlyBonusWeight(bonusWeight);
            weightInfo.setStockOptionSharesWeight(sharesWeight);
            weightInfo.setHomeBuyingProgramFundWeight(homeBuyingWeight);
            weightInfo.setPersonalChoiceHolidaysWeight(holidaysWeight);
            weightInfo.setMonthlyInternetStipendWeight(internetStipendWeight);
        }

        // close resources
        db.close();
        cursor.close();

        return weightInfo;
    }


    /**
     *  Calculates a number of rows in the table
     * @return the table size
     */
    public int getTableSize(){
        int size = 0;
        String sqlQuery = "SELECT COUNT(*) FROM " + TABLE_NAME_WEIGHT;
        // open the database connection
        SQLiteDatabase db = this.getReadableDatabase();
        // run the query
        Cursor cursor = db.rawQuery(sqlQuery, null);

        // check the result
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            // get the first column with the count
            size = cursor.getInt(0);
        }

        db.close();
        cursor.close();

        return size;
    }
}
