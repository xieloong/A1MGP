package com.example.week4real;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class LevelOneState implements StateBase{
    @Override
    public String GetName() {
        return "LevelOne";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create(); //Entity
        Player.Create(100,800);
        RenderTextEntity.Create();
        // Example to include another Renderview for Pause Button
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
        EntityManager.Instance.Update(_dt);
    }
}
