package com.example.week4real;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;


public class PlatformGen{
    public final static PlatformGen Instance = new PlatformGen();
    private SurfaceView view = null;
    public int ScreenWidth, ScreenHeight;
    Platform newPlatform = null;
    float newPositionY;
    boolean isValid = true;
    Smurf player = null;
    boolean isInit = false;
    private final float PLATFORM_HEIGHT = 200.f;
    private final float PLAYER_DISTANCE_SPAWN_LEVEL_PART = 1000.f;
    private enum PlatformPart{
        PLATFORM_PART_ONE,
        PLATFORM_PART_TWO,
        PLATFORM_PART_THREE,
    }


    private PlatformGen() {

    }

    public void InitialisePlatforms()
    {
        if(!isInit)
        {
            newPlatform = Platform.Create(200,400);
            int startingSpawnLevelParts = 5;
            for(int i = 0; i < startingSpawnLevelParts; i++){
                CreatePlatform();
            }
            isInit = true;
        }
    }

    public void Init(SurfaceView _view)
    {
        view = _view;
        // Finding the screen width & height to allow the images to scale according to it.
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
    }

    public void Update()
    {
        if(GameSystem.Instance.GetIsPaused())
            return;

        if(player != null)
        {
            if((newPlatform.GetPosX() - player.GetPosX()) < PLAYER_DISTANCE_SPAWN_LEVEL_PART)
            {
                // Spawn Another Level Part
                CreatePlatform();
            }

//            Log.i("PlayerPosX",Float.toString(player.GetPosX()));
//            Log.i("LastPlatformPosX",Float.toString(newPlatform.GetPosX()));
        }
    }

    public void CreatePlatform() {
        // Generate Random Number in Range 0 to 2
        Random ranGem = new Random();
        int rand_int = ranGem.nextInt(3-0)+0;


        switch (rand_int) {
            case 0:
                newPositionY = newPlatform.GetPosY() - PLATFORM_HEIGHT;
                break;
            case 1:
                newPositionY = newPlatform.GetPosY();
                break;
            case 2:
                newPositionY = newPlatform.GetPosY() + PLATFORM_HEIGHT;
                break;
            default:
                break;
        }

        if(!isValid(newPositionY))
        {
            CreatePlatform();
        }
        else{
            newPlatform = Platform.Create(newPlatform.GetPosX() + 800,newPositionY);
//            Log.i("newPositionY: ", Float.toString(UpdatePositionY));
        }
    }
    public boolean isValid(float newPositionY) {
        if (newPositionY >= ScreenHeight - 200 || newPositionY <= 100) {
            isValid = false;
        }
        else {
            isValid = true;
        }
        return isValid;
    }

    public void setPlayer(Smurf player) {
        this.player = player;
    }
}

