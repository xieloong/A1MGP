package com.example.week4real;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;


public class LevelGen{
    public final static LevelGen Instance = new LevelGen();
    private SurfaceView view = null;
    public int ScreenWidth, ScreenHeight;
    Platform newPlatform = null;
    float newPositionY;
    boolean isValid = true;
    Smurf player = null;
    EarthEntity earthEntity = null;
    boolean isInit = false;
    private final float PLATFORM_HEIGHT = 200.f;
    private final float PLAYER_DISTANCE_SPAWN_LEVEL_PART = 1000.f;


    private LevelGen() {

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

        if(player != null && earthEntity != null)
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
            float newPositionX = newPlatform.GetPosX() + 800;
            newPlatform = Platform.Create(newPositionX,newPositionY);
            int rand_PowerUp = GetRandomPowerUp();
            switch(rand_PowerUp)
            {
                case 0:
                    // Don't Spawn Power-up
                    break;
                case 1:
                    // Spawn Power-Up One
                    float PowerUpPosX = GetPowerUpPosX(newPositionX);
                    float PowerUpPosY = GetPowerUpPosY(newPositionY);
                    PowerUp1.Create(PowerUpPosX,PowerUpPosY,earthEntity);
                    Log.i("PowerUpPosX",Float.toString(PowerUpPosX));
                    Log.i("PowerUpPosY",Float.toString(PowerUpPosY));
                    break;
                case 2:
                    // Spawn Power-Up Two
                    PowerUpPosX = (float) GetPowerUpPosX(newPositionX);
                    PowerUpPosY = (float) GetPowerUpPosY(newPositionY);
                    PowerUp2.Create(PowerUpPosX,PowerUpPosY,earthEntity);
                    break;
                case 3:
                    // Spawn Power-Up Three
                    PowerUpPosX = (float) GetPowerUpPosX(newPositionX);
                    PowerUpPosY = (float) GetPowerUpPosY(newPositionY);
                    PowerUp3.Create(PowerUpPosX,PowerUpPosY,earthEntity);
                    break;
            }
//            Log.i("newPositionY: ", Float.toString(UpdatePositionY));
        }
    }
    public boolean isValid(float newPositionY) {
        if (newPositionY >= ScreenHeight - 200 || newPositionY <= 300) {
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

    public void setEarth(EarthEntity earthEntity)
    {
        this.earthEntity = earthEntity;
    }

    public int GetRandomPowerUp()
    {
        // return int;
        // Generate Random Number in Range 0 to 3
        Random ranGem = new Random();
        int rand_int = ranGem.nextInt(4-0)+0;
        return rand_int;
    }

    public float GetPowerUpPosX(float newPlatformX)
    {
        Random ranGem = new Random();
        float minX = (int) (newPlatformX - 100);
        float maxX = (int) (newPlatformX + 400);
        float rand_float = minX + ranGem.nextFloat() *  (maxX - minX);
        return rand_float;
    }
    public float GetPowerUpPosY(float newPlatformY)
    {
        Random ranGem = new Random();
        float minY = (int) (newPlatformY - 200);
        float maxY= (int) (newPlatformY - 50);
        float rand_float = minY + ranGem.nextFloat() *  (maxY - minY);
        return rand_float;
    }
}

