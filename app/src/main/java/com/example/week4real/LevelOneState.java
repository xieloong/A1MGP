package com.example.week4real;

import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import androidx.fragment.app.DialogFragment;

public class LevelOneState implements StateBase{

    Smurf player;
    EarthEntity earthEntity;
    RenderTextEntity renderTextEntity;

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
        RenderBackground.Create(); //Entity
        earthEntity = EarthEntity.Create(ScreenWidth/2 - 300,0);
        LevelGen.Instance.setEarth(earthEntity);
        LevelGen.Instance.InitialisePlatforms();
        player = Smurf.Create();
        LevelGen.Instance.setPlayer(player);
        PausebuttonEntity.Create();
       renderTextEntity = RenderTextEntity.Create();
       AudioManager.Instance.PlayeAudio(R.raw.bgmusic,10.0f);
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
            LevelGen.Instance.Update(_dt);
            EntityManager.Instance.Update(_dt);
        }
//        else if(player.IsDone())
//        {
//            if(GameOverAlertDialog.isShown)
//                return;
//
//            int finalScore = renderTextEntity.GetPoints();
//            GameSystem.Instance.SetIsPaused(true);
//            GameOverAlertDialog newGameOver = new GameOverAlertDialog();
//            newGameOver.SetScore(finalScore);
//            newGameOver.show(GamePage.Instance.getSupportFragmentManager(), "Gameover!");
//        }
        else
        {
            if(GameOverAlertDialog.isShown)
                return;

            int finalScore = renderTextEntity.GetPoints();
            GameSystem.Instance.SetIsPaused(true);
            GameOverAlertDialog newGameOver = new GameOverAlertDialog();
            newGameOver.SetScore(finalScore);
            newGameOver.show(GamePage.Instance.getSupportFragmentManager(), "Gameover!");

        }
    }
}
