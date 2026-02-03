package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class JobInfoDatabaseHelper extends DatabaseHelper{

    private JobInfo mostRecentJobOffer;

    public JobInfoDatabaseHelper(Context context) {
        super(context);
    }


    /**
     * CreateS a JobInfo in DB
     * @param jobInfo object
     * @return true/false
     */
    public boolean create(JobInfo jobInfo){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TITLE, jobInfo.getTitle());
        contentValues.put(COLUMN_NAME_COMPANY, jobInfo.getCompany());
        contentValues.put(COLUMN_NAME_LOCATION, jobInfo.getCompanyLocation());
        contentValues.put(COLUMN_NAME_LIVING_COST, jobInfo.getCostOfLivingIndex());
        contentValues.put(COLUMN_NAME_SALARY, jobInfo.getYearlySalary());
        contentValues.put(COLUMN_NAME_BONUS, jobInfo.getYearlyBonus());
        contentValues.put(COLUMN_NAME_SHARES, jobInfo.getStockOptionsShares());
        contentValues.put(COLUMN_NAME_HOME_BUYING, jobInfo.getHomeBuyingProgramFund());
        contentValues.put(COLUMN_NAME_HOLIDAYS, jobInfo.getPersonalChoiceHolidays());
        contentValues.put(COLUMN_NAME_INTERNET_STIPEND, jobInfo.getMonthlyInternetStipend());
        contentValues.put(COLUMN_NAME_IS_CURRENT_JOB, jobInfo.isCurrentJob()); // db should convert it to 0 or 1
        // open the database connection
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreated = db.insert(TABLE_NAME_JOB, null, contentValues) > 0;

        db.close();

        if (isCreated){
            mostRecentJobOffer = jobInfo;
        }

        return isCreated;
    }


    /**
     * Returns all JobInfo objects from DB
     * @return Arraylist
     */
    public ArrayList<JobInfo> getAllJobsInfo(){
        ArrayList<JobInfo> jobInfoArrayList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM " + TABLE_NAME_JOB;
        // open the database connection
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);


        // check the cursor and proceed fetching the data
        if (cursor.moveToFirst()){
            do {
                // get the values
                int jobId = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_JOB_ID)));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TITLE));
                String company = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_COMPANY));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_LOCATION));
                int livingCost = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_LIVING_COST)));
                double salary = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_SALARY)));
                double bonus = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_BONUS)));
                int shares = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_SHARES)));
                double homeBuying = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOME_BUYING)));
                int holidays = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOLIDAYS)));
                double internetStipend = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_INTERNET_STIPEND)));
                int isCurrentJobValue = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_IS_CURRENT_JOB)));
                boolean isCurrentJob = isCurrentJobValue == 1; // convert the result from db(0 or 1) to boolean

                JobInfo jobInfo = new JobInfo(title, company, location,
                        livingCost, salary, bonus,
                        shares, homeBuying, holidays, internetStipend, isCurrentJob);
                jobInfo.setId(jobId);
                jobInfo.setCurrentJob(isCurrentJob);
                // add the job to the list
                jobInfoArrayList.add(jobInfo);

            } while (cursor.moveToNext());
        }
        // close db
        db.close();
        // close the cursor to avoid a memory leak
        cursor.close();
        return jobInfoArrayList;
    }


    /**
     * Calculates a number of rows in the table
     * @return int size
     */
    public int getTableSize(){
        int size = 0;
        String sqlQuery = "SELECT COUNT(*) FROM " + TABLE_NAME_JOB;
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
        // close db
        db.close();
        // close the cursor to avoid a memory leak
        cursor.close();

        return size;
    }


    /**
     * Finds a JobInfo by id
     * @param id of the jobInfo
     * @return jobInfo
     */
    public JobInfo getJobInfoById(int id){

        JobInfo jobInfo = null;

        String sqlQuery = "SELECT * FROM " + TABLE_NAME_JOB + " WHERE " + COLUMN_NAME_JOB_ID + "=" + id;
        // open the database connection
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()){
            // get the values
            // get the values
            int jobId = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_JOB_ID)));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TITLE));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_COMPANY));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_LOCATION));
            int livingCost = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_LIVING_COST)));
            double salary = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_SALARY)));
            double bonus = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_BONUS)));
            int shares = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_SHARES)));
            double homeBuying = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOME_BUYING)));
            int holidays = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOLIDAYS)));
            double internetStipend = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_INTERNET_STIPEND)));
            int isCurrentJobValue = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_IS_CURRENT_JOB)));
            boolean isCurrentJob = isCurrentJobValue == 1; // convert the result from db(0 or 1) to boolean

            jobInfo = new JobInfo(title, company, location,
                    livingCost, salary, bonus,
                    shares, homeBuying, holidays, internetStipend, isCurrentJob);
            jobInfo.setId(jobId);
            jobInfo.setCurrentJob(isCurrentJob);
        }

        // close db
        db.close();
        // close the cursor to avoid a memory leak
        cursor.close();

        return jobInfo;
    }


    public boolean isCurrentJobExists(){
        /**
         * https://stackoverflow.com/questions/20415309/android-sqlite-how-to-check-if-a-record-exists
         */
        if (getTableSize() == 0){
            return false;
        }

        String sqlQuery = "SELECT EXISTS(SELECT 1 FROM " +  TABLE_NAME_JOB + " WHERE " + COLUMN_NAME_IS_CURRENT_JOB + "=1 LIMIT 1)";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        cursor.moveToFirst();
        if (cursor.getInt(0) == 1){
            db.close();
            cursor.close();
            return true;
        }

        db.close();
        cursor.close();
        return false;
    }


    /**
     * Updates the input object in DB
     * @param jobInfo Object
     * @return true/false
     */
    public boolean updateCurrentJob(JobInfo jobInfo){ // TODO: should I make this method exclusive to the current job only?

        ContentValues contentValues = new ContentValues();
        // fill out the content
        contentValues.put(COLUMN_NAME_TITLE, jobInfo.getTitle());
        contentValues.put(COLUMN_NAME_COMPANY, jobInfo.getCompany());
        contentValues.put(COLUMN_NAME_LOCATION, jobInfo.getCompanyLocation());
        contentValues.put(COLUMN_NAME_LIVING_COST, jobInfo.getCostOfLivingIndex());
        contentValues.put(COLUMN_NAME_SALARY, jobInfo.getYearlySalary());
        contentValues.put(COLUMN_NAME_BONUS, jobInfo.getYearlyBonus());
        contentValues.put(COLUMN_NAME_SHARES, jobInfo.getStockOptionsShares());
        contentValues.put(COLUMN_NAME_HOME_BUYING, jobInfo.getHomeBuyingProgramFund());
        contentValues.put(COLUMN_NAME_HOLIDAYS, jobInfo.getPersonalChoiceHolidays());
        contentValues.put(COLUMN_NAME_INTERNET_STIPEND, jobInfo.getMonthlyInternetStipend());
        contentValues.put(COLUMN_NAME_IS_CURRENT_JOB, jobInfo.isCurrentJob()); // db should convert it to 0 or 1

        // open the database connection
        SQLiteDatabase db = this.getWritableDatabase();

//        boolean isUpdated = db.update(TABLE_NAME_JOB, contentValues, COLUMN_NAME_JOB_ID + " ='" + jobInfo.getId() + "'", null) > 0;
        boolean isCurrentJobUpdated = db.update(TABLE_NAME_JOB, contentValues, COLUMN_NAME_IS_CURRENT_JOB + " ='" + 1 + "'", null) > 0;

        db.close();
//        return isUpdated;
        return isCurrentJobUpdated;
    }

    public JobInfo findCurrentJob(){
        JobInfo currentJob = null;

        String sqlQuery = "SELECT * FROM " + TABLE_NAME_JOB + " WHERE " + COLUMN_NAME_IS_CURRENT_JOB + "=1";
        // open the database connection
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()){
            // get the values
            int jobId = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_JOB_ID)));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TITLE));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_COMPANY));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_LOCATION));
            int livingCost = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_LIVING_COST)));
            double salary = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_SALARY)));
            double bonus = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_BONUS)));
            int shares = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_SHARES)));
            double homeBuying = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOME_BUYING)));
            int holidays = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOLIDAYS)));
            double internetStipend = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_INTERNET_STIPEND)));
            int isCurrentJobValue = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_IS_CURRENT_JOB)));
            boolean isCurrentJob = isCurrentJobValue == 1; // convert the result from db(0 or 1) to boolean

            currentJob = new JobInfo(title, company, location,
                    livingCost, salary, bonus,
                    shares, homeBuying, holidays, internetStipend, isCurrentJob);
            currentJob.setId(jobId);
            currentJob.setCurrentJob(isCurrentJob);
        }

        // close db
        db.close();
        // close the cursor to avoid a memory leak
        cursor.close();

        return currentJob;
    }

    public boolean delete(int id){
        // open the database connection
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isDeleted = db.delete(TABLE_NAME_JOB, COLUMN_NAME_JOB_ID + " ='" + id + "'", null) > 0;

        db.close();

        return isDeleted;
    }

    public JobInfo getMostRecentJobOffer() {
        return mostRecentJobOffer;
    }
}
