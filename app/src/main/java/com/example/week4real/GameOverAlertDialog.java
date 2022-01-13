package com.example.week4real;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.GameManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class GameOverAlertDialog extends DialogFragment {
    public static boolean isShown = false;
    private int finalScore;
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
                String playerName = input.getText().toString(); // variable to collect user input
                // add line after initializing editTextName1
                input.setHint(" Add Grayed-out Text Here");
                String defaultName = "Player";
                boolean checkInput = playerName.isEmpty();
                if(checkInput == true)
                {
                    playerName = defaultName;
                }
                GameSystem.Instance.SavedEditBegin();
                GameSystem.Instance.SetIntInSave(playerName, finalScore);
                GameSystem.Instance.AddPlayer(playerName);
                GameSystem.Instance.SaveEditEnd();


                Intent intent = new Intent();
                intent.setClass(getActivity(), Leaderboard.class);
                startActivity(intent);
//                dialog.dismiss();
                GameSystem.Instance.SetIsPaused(false);
                LevelGen.Instance.ResetLevelGen();
                StateManager.Instance.ChangeState("Mainmenu");
                isShown = false;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

//                dialog.cancel();
                StateManager.Instance.ChangeState("Mainmenu");
                GameSystem.Instance.SetIsPaused(false);
                LevelGen.Instance.ResetLevelGen();
                isShown = false;

            }
        });
        return builder.create();
    }

    public void SetScore(int score)
    {
        finalScore= score;
    }
}


