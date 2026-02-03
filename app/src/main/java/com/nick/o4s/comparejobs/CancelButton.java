package com.nick.o4s.comparejobs;

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
