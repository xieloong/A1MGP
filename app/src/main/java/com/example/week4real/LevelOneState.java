package com.example.week4real;

import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class LevelOneState implements StateBase{

    Smurf player;
    EarthEntity earthEntity;

    @Override
    public String GetName() {
        return "LevelOne";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        int ScreenWidth = metrics.widthPixels;
        int ScreenHeight = metrics.heightPixels;
        RenderBackground.Create(); //EntitY
        PlatformGen.Instance.InitialisePlatforms();
        player = Smurf.Create();
        PlatformGen.Instance.setPlayer(player);
        PausebuttonEntity.Create();
        RenderTextEntity.Create();
        earthEntity = EarthEntity.Create(ScreenWidth/2,20);
        // Example to include another Renderview for Pause Button //test for push
    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }

    @Override
    public void Update(float _dt) {
        if(earthEntity.GetHealthPoints() > 0)
        {
            PlatformGen.Instance.Update();
            EntityManager.Instance.Update(_dt);
        }
    }
}
