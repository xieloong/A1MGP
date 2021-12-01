package com.example.week4real;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class LevelManager {

    public final static LevelManager Instance = new LevelManager();
    private SurfaceView view = null;
    LevelNo Level = LevelNo.LEVEL_NONE;
    boolean LEVEL_ONE_INITIALISED = false;
    //boolean LEVEL_TWO_INITIALISED = false;
    //boolean LEVEL_THREE_INITIALISED = false;
    private LevelManager()
    {
    }

    public enum LevelNo
    {
        LEVEL_ONE,
        LEVEL_TWO,
        LEVEL_THREE,
        LEVEL_NONE,
    }

    public void Init(SurfaceView _view)
    {
        view = _view;
    }

    public void Update()
    {
        switch(Level)
        {
            case LEVEL_ONE:
                    StateManager.Instance.ChangeState("LevelOne"); // Default is like a loading page
                    PlatformGen.Instance.Update();
                break;
            case LEVEL_TWO:
                break;
            case LEVEL_THREE:
                break;
            case LEVEL_NONE:
                break;
        }
        /*
        if (TouchManager.Instance.IsDown()) {
            //Example of touch on screen in the main game to trigger back to Main menu
            StateManager.Instance.ChangeState("Mainmenu");
            ResetLevel();
        }
        */
    }
    public void SetLevel(LevelNo level){
        Level = level;
    }
    public void ResetLevel(){
        Level = LevelNo.LEVEL_NONE;
    }
}
