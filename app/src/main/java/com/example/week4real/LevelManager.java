package com.example.week4real;

import android.view.SurfaceView;

public class LevelManager {

    public final static LevelManager Instance = new LevelManager();
    private SurfaceView view = null;

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

    public void Update(float _dt, LevelNo Level)
    {
        switch(Level)
        {
            case LEVEL_ONE:
                break;
            case LEVEL_TWO:
                break;
            case LEVEL_THREE:
                break;
        }
    }
}
