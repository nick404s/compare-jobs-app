package edu.gatech.seclass.jobcompare6300;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CancelButton extends AppCompatActivity {
    Button cancelButton;

    protected void addCancelButtonListener() {
        cancelButton = findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(
                v -> finish());
    }
}
