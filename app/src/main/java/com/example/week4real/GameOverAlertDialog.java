package com.example.week4real;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.GameManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class GameOverAlertDialog extends DialogFragment {
    public static boolean isShown = false;
    EditText input; // user input bar


    @Override
    public Dialog onCreateDialog(Bundle savedInstancedState) {
        isShown = true;
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Game Over!");
        builder.setMessage("Enter Your Name!");

        input = new EditText(this.getActivity());
        builder.setView((input));

        // Set Positive Button
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String txt = input.getText().toString(); // variable to collect user input
                // add line after initializing editTextName1
                input.setHint(" Add Grayed-out Text Here");
                Toast.makeText(getActivity(),txt,Toast.LENGTH_LONG).show();
                isShown = false;
                dialog.dismiss();
                StateManager.Instance.ChangeState("Mainmenu");
                GameSystem.Instance.SetIsPaused(false);
                LevelGen.Instance.ResetLevelGen();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                isShown = false;
                dialog.cancel();
                StateManager.Instance.ChangeState("Mainmenu");
                GameSystem.Instance.SetIsPaused(false);
                LevelGen.Instance.ResetLevelGen();

            }
        });
        return builder.create();
    }

}


