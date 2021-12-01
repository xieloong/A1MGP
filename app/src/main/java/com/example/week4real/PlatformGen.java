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
    float UpdatePositionY;
    boolean isValid = true;

    private enum PlatformPart{
        PLATFORM_PART_ONE,
        PLATFORM_PART_TWO,
        PLATFORM_PART_THREE,
    }


    private PlatformGen()
    {
        newPlatform = Platform.Create(100,400);
    }


    public void Init(SurfaceView _view)
    {
        view = _view;
        // FInding the screen width & height to allow the images to scale according to it.
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
    }

    public void Update()
    {
        CreatePlatform();
    }

    public void CreatePlatform() {
        // Generate Random Number in Range 0 to 2
        Random ranGem = new Random();
        int rand_int = ranGem.nextInt(3-0)+0;


        switch (rand_int) {
            case 0:
                newPositionY = newPlatform.EndPositionY;
                break;
            case 1:
                newPositionY = newPlatform.EndPositionY - 200;
                break;
            case 2:
                newPositionY = newPlatform.EndPositionY + 200;
                break;
            default:
                break;

        }

        if(!isValid(newPositionY))
        {
            CreatePlatform();
        }
        else{
            UpdatePositionY = newPositionY;
            newPlatform = Platform.Create(newPlatform.EndPositionX + 500,UpdatePositionY);
            Log.i("newPositionY: ", Float.toString(UpdatePositionY));
        }

    }
    public boolean isValid(float newPositionY) {
        if (newPositionY >= ScreenHeight - 200 || newPositionY <= -ScreenHeight + 200) {
            isValid = false;
        }
        else {
            isValid = true;
        }
        return isValid;
    }
}

