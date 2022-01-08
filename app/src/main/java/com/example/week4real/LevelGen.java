package com.example.week4real;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;


public class
LevelGen{
    public final static LevelGen Instance = new LevelGen();
    private SurfaceView view = null;
    public int ScreenWidth, ScreenHeight;
    Platform newPlatform = null;
    float newPositionX;
    Boolean isValid = true;
    Smurf player = null;
    EarthEntity earthEntity = null;
    boolean isInit = false;
    private final float PLATFORM_HEIGHT = 306.f;
    private float PowerUp_HEIGHT;
    private final float PLAYER_DISTANCE_SPAWN_LEVEL_PART = 1000.f;

    private ArrayList<Boolean> platformlist = new ArrayList<Boolean>();
    private ArrayList<Boolean> enemyPowerupList = new ArrayList<Boolean>();



    private LevelGen() {

    }

    public void InitialisePlatforms()
    {
        if(!isInit)
        {
            newPlatform = Platform.Create(400,270);
            newPositionX = newPlatform.GetPosX();
            int startingSpawnLevelParts = 5;
            for(int i = 0; i < startingSpawnLevelParts; i++){
                CreateLevelPart();
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
        PowerUp_HEIGHT = ScreenHeight / 3;
    }

    public void Update(float _dt)
    {
        if(GameSystem.Instance.GetIsPaused())
            return;

        if(player != null && earthEntity != null)
        {
            newPositionX -= _dt * 200;

            if((newPositionX - player.defaultxPos) < PLAYER_DISTANCE_SPAWN_LEVEL_PART)
            {
                // Spawn Another Level Part
                CreateLevelPart();
            }

//            Log.i("PlayerPosX",Float.toString(player.GetPosX()));
//            Log.i("LastPlatformPosX",Float.toString(newPlatform.GetPosX()));
        }
    }

    public void CreateLevelPart() {

        newPositionX = newPositionX + 800;
        // Fill Platform List
        FillPlatforms();

        // Create Platforms
        CreatePlatforms(newPositionX);

        // Fill Enemy + Power-up List
        FillEnemyPowerup();

        // Create Enemy + Power-Up
        CreatePowerUpOrEnemy(newPositionX);
    }

    private void FillPlatforms()
    {
        // Clear the List
        platformlist.clear();
        // Fill List With false or true
        for(int index = 0; index < 2; index++)
        {
            Random ranGem = new Random();
            int rand_int = ranGem.nextInt(2-0)+0;
            if(rand_int == 1)
            {
                platformlist.add(true);
            }
            else
            {
                platformlist.add(false);
            }
        }
    }
    private void CreatePlatforms(float positionX)
    {
       // Check Platform List
        for(int index = 0; index < platformlist.size(); index++)
        {
            if(platformlist.get(index) == true)
            {
                // Create Platform based on Index
                Platform.Create(positionX,270 + index * PLATFORM_HEIGHT);
            }
        }
    }

    private void FillEnemyPowerup()
    {
        // Clear the List of power-ups and enemies
        enemyPowerupList.clear();
        // Fill List With false or true
        for(int index = 0; index < 3; index++)
        {
            Random ranGem = new Random();
            int rand_int = ranGem.nextInt(2-0)+0;
            if(rand_int == 1)
            {
                enemyPowerupList.add(true);
            }
            else
            {
                enemyPowerupList.add(false);
            }
        }
    }

    private void CreatePowerUpOrEnemy(float positionX)
    {
        // Check enemy + powerup List
        for(int index = 0; index < enemyPowerupList.size(); index++)
        {
            if(enemyPowerupList.get(index) == true)
            {
                // Choose which to spawn; power-up or enemy
                Random ranGem = new Random();
                int rand_int = ranGem.nextInt(2-0)+0;
                Log.i("RandomGen",Float.toString(rand_int));
                if(rand_int == 0)
                {
                    // Spawn Power-up
                    // Choose which power-up to spawn
                    int rand_PowerUp = GetRandomPowerUp();
                    switch(rand_PowerUp)
                    {
                        case 0:
                            // Spawn Power-Up One
                            float PowerUpPosX = GetPowerUpPosX(positionX);
                            float PowerUpPosY = GetPowerUpPosY(ScreenHeight/6 + (float) index * PowerUp_HEIGHT) ;
                            PowerUp1.Create(PowerUpPosX,PowerUpPosY,earthEntity);
                            Log.i("PowerUpPosX",Float.toString(PowerUpPosX));
                            Log.i("PowerUpPosY",Float.toString(PowerUpPosY));
                            break;
                        case 1:
                            // Spawn Power-Up Two
                            PowerUpPosX = GetPowerUpPosX(positionX);
                            PowerUpPosY = GetPowerUpPosY(ScreenHeight/6 + (float) index * PowerUp_HEIGHT) ;
                            PowerUp2.Create(PowerUpPosX,PowerUpPosY,earthEntity);
                            break;
                        case 2:
                            // Spawn Power-Up Three
                            PowerUpPosX = GetPowerUpPosX(positionX);
                            PowerUpPosY = GetPowerUpPosY(ScreenHeight/6 + (float) index * PowerUp_HEIGHT) ;
                            PowerUp3.Create(PowerUpPosX,PowerUpPosY,earthEntity);
                            break;
                        default:
                            break;
                    }
                }
                else if (rand_int == 1)
                {
                    // Spawn Enemy
                    Log.i("EnemyIndex",Integer.toString((index)));
                    float enemyPosition = ScreenHeight/6 + (float)index * PowerUp_HEIGHT;
                    Log.i("EnemyPosition",Float.toString((enemyPosition)));
                    Enemy.Create(positionX,enemyPosition,earthEntity);
                }
            }

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
        // Generate Random Number in Range 0 to 2
        Random ranGem = new Random();
        int rand_int = ranGem.nextInt(3-0)+0;
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
        Log.i("DebugPositonY", Float.toString((newPlatformY)));
        Random ranGem = new Random();
        float minY = (int) (newPlatformY - 50);
        float maxY= (int) (newPlatformY);
        float rand_float = minY + ranGem.nextFloat() *  (maxY - minY);
        return rand_float;
    }

    public void ResetLevelGen()
    {
        isInit = false;
    }
}

