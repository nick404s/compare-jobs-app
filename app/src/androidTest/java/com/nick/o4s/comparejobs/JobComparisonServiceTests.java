package com.nick.o4s.comparejobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class JobComparisonServiceTests {

    private static final String TAG = "JobComparisonServiceTests";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("edu.gatech.seclass.jobcompare6300", appContext.getPackageName());
    }

    @Test
    public void calculateScoreShouldReturnCorrectValue() {
        JobComparisonService jobComparisonService = new JobComparisonService();
        JobInfo jobInfo = new JobInfo("Title", "Company", "Location", 1,
                50000, 5000, 100, 2000, 10, 50, true);
        WeightInfo weightInfo = new WeightInfo();

        double result = jobComparisonService.calculateScore(jobInfo, weightInfo);

        Log.d(TAG, "calculateScore result: " + result);

        assertTrue(result == 949162.39);
    }

    @Test
    public void calculateAdjustedAmountShouldReturnCorrectValue() {
        JobComparisonService jobComparisonService = new JobComparisonService();

        double result = jobComparisonService.calculateAdjustedAmount(50000, 1);

        Log.d(TAG, "calculateAdjustedAmount result: " + result);

        assertTrue(result == 5000000.0);
    }

    @Test
    public void calculateMaxPercentageSumShouldReturnCorrectValue() {
        JobComparisonService jobComparisonService = new JobComparisonService();

        double result = jobComparisonService.calculateMaxPercentageSum(50000);

        Log.d(TAG, "calculateMaxPercentageSum result: " + result);


        assertTrue(result == 7500.0);
    }
}
