package com.nick.o4s.comparejobs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Parent class for the Job and Weight info classes
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * inspired by some info from these:
     * "https://www.digitalocean.com/community/tutorials/android-sqlite-database-example-tutorial"
     *  https://www.youtube.com/watch?v=NjhuYPSaAzM
     *  https://janetpearson.com/android-sqlite-multiple-tables-best-practices
     */
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "JobComparisonDatabase";

    // Job table and columns
    protected static final String TABLE_NAME_JOB = "Job";
    protected static final String COLUMN_NAME_JOB_ID = "job_id";
    protected static final String COLUMN_NAME_TITLE = "title";
    protected static final String COLUMN_NAME_COMPANY = "company";
    protected static final String COLUMN_NAME_LOCATION = "location";
    protected static final String COLUMN_NAME_LIVING_COST = "living_cost";
    protected static final String COLUMN_NAME_SALARY = "salary";
    protected static final String COLUMN_NAME_BONUS = "bonus";
    protected static final String COLUMN_NAME_SHARES = "shares";
    protected static final String COLUMN_NAME_HOME_BUYING = "home_buying";
    protected static final String COLUMN_NAME_HOLIDAYS = "holidays";
    protected static final String COLUMN_NAME_INTERNET_STIPEND = "internet_stipend";
    protected static final String COLUMN_NAME_IS_CURRENT_JOB = "is_current_job";

    // Weight table and columns
    protected static final String TABLE_NAME_WEIGHT = "Weight";
    protected static final String COLUMN_NAME_WEIGHT_ID = "weight_id";
    protected static final String COLUMN_NAME_SALARY_WEIGHT = "salary_weight";
    protected static final String COLUMN_NAME_BONUS_WEIGHT = "bonus_weight";
    protected static final String COLUMN_NAME_SHARES_WEIGHT = "shares_weight";
    protected static final String COLUMN_NAME_HOME_BUYING_WEIGHT = "home_buying_weight";
    protected static final String COLUMN_NAME_HOLIDAYS_WEIGHT = "holidays_weight";
    protected static final String COLUMN_NAME_INTERNET_STIPEND_WEIGHT = "internet_stipend_weight";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // create the the queries to create the Job and Weight
        String sqlQueryJob = "CREATE TABLE " +
                            TABLE_NAME_JOB +
                            " (" +
                                COLUMN_NAME_JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                COLUMN_NAME_TITLE + " TEXT, " +
                                COLUMN_NAME_COMPANY + " TEXT, " +
                                COLUMN_NAME_LOCATION + " TEXT, " +
                                COLUMN_NAME_LIVING_COST + " INTEGER, " +
                                COLUMN_NAME_SALARY + " REAL, " +
                                COLUMN_NAME_BONUS + " REAL, " +
                                COLUMN_NAME_SHARES + " INTEGER, " +
                                COLUMN_NAME_HOME_BUYING + " REAL, " +
                                COLUMN_NAME_HOLIDAYS + " INTEGER, " +
                                COLUMN_NAME_INTERNET_STIPEND + " REAL, " +
                                COLUMN_NAME_IS_CURRENT_JOB + " INTEGER" +
                            ")";


        String sqlQueryWeight = "CREATE TABLE " +
                                TABLE_NAME_WEIGHT +
                                " (" +
                                    COLUMN_NAME_WEIGHT_ID + " INTEGER PRIMARY KEY, " +
                                    COLUMN_NAME_SALARY_WEIGHT + " INTEGER, " +
                                    COLUMN_NAME_BONUS_WEIGHT + " INTEGER, " +
                                    COLUMN_NAME_SHARES_WEIGHT + " INTEGER, " +
                                    COLUMN_NAME_HOME_BUYING_WEIGHT + " INTEGER, " +
                                    COLUMN_NAME_HOLIDAYS_WEIGHT + " INTEGER, " +
                                    COLUMN_NAME_INTERNET_STIPEND_WEIGHT + " INTEGER" +
                                ")";

        // run the queries
        sqLiteDatabase.execSQL(sqlQueryJob);
        sqLiteDatabase.execSQL(sqlQueryWeight);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // create the the queries to delete the Job and Weight
        String sqlQueryJob = "DROP TABLE IF EXISTS " + TABLE_NAME_JOB;
        String sqlQueryWeight = "DROP TABLE IF EXISTS " + TABLE_NAME_WEIGHT;

        // run the queries
        sqLiteDatabase.execSQL(sqlQueryJob);
        sqLiteDatabase.execSQL(sqlQueryWeight);

        // recreate the tables
        onCreate(sqLiteDatabase);
    }
}
