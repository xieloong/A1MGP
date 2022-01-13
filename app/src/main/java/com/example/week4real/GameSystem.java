package com.example.week4real;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

import java.util.ArrayList;

// Created by TanSiewLan2021

public class GameSystem {
    public final static GameSystem Instance = new GameSystem();
    public static final String SHARED_PREF_ID ="GameSaveFile";
    public ArrayList<String> playerNames = new ArrayList<String>();
    // Game stuff
    private boolean isPaused = false;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {
    }

    public void Update(float _deltaTime)
    {
    }

    public void Init(SurfaceView _view)
    {
        // Get our shared preferences (Save file)
        sharedPref = GamePage.Instance.getSharedPreferences(SHARED_PREF_ID,0);
        // We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new Mainmenu());
        StateManager.Instance.AddState(new MainGameSceneState());
        StateManager.Instance.AddState(new Optionsmenu());
        StateManager.Instance.AddState(new Leaderboard());
        StateManager.Instance.AddState(new LevelOneState());
    }

    public void SetIsPaused(boolean _newIsPaused)
    {
        isPaused = _newIsPaused;
    }

    public boolean GetIsPaused()
    {
        return isPaused;
    }


    public void SavedEditBegin()
    {
        // Safety Check, only allow if not already editing
        if(editor != null)
            return;

        // Start the Editing
        editor = sharedPref.edit();
    }

    public void SaveEditEnd()
    {
        //Check if has editor
        if(editor == null)
            return;

        editor.commit();
        editor = null; // Safety to ensure other functions will fail once commit done
    }

    public void SetIntInSave(String _key, int _value)
    {
        if(editor == null)
            return;

            editor.putInt(_key, _value);
    }

    public int GetIntFromSave(String _key)
    {
       return sharedPref.getInt(_key,10);
    }

    // Save Name
    public void SetPlayerNameInSave(String _key, String _value)
    {
        if(editor == null)
            return;

        editor.putString(_key, _value);
    }

    public String GetPlayerNameFromSave(String _key)
    {
        return sharedPref.getString(_key,"Player");
    }

    public void AddPlayer(String playerName)
    {
        playerNames.add(playerName);
    }

}
