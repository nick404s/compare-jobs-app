package com.nick.o4s.comparejobs;

import android.widget.Button;

abstract public class SaveCancelButton extends CancelButton {

    Button saveButton;

    protected abstract boolean performSave();


    protected void addSaveButtonListener() {
        saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(
                v -> {
                    boolean saveSuccessful = performSave();
                    if (saveSuccessful){
                        finish();
                    }
                });

    }

    protected void addSaveCancelButtonsListeners() {
        addCancelButtonListener();
        addSaveButtonListener();
    }

}
