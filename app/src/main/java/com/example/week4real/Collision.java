package com.example.week4real;

// Created by TanSiewLan2021

public class Collision {

    public static boolean SphereToSphere(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        float xVec = x2 - x1;
        float yVec = y2 - y1;

        float distSquared = xVec * xVec + yVec * yVec;


        float rSquared = radius1 + radius2;
        rSquared *= rSquared;

        if (distSquared > rSquared)
            return false;

        return true;
    }

    public  static boolean AABB(float left,  float top, float right, float bottom, float left2,  float top2, float right2, float bottom2) {

        return (left < right2 && right > left2 && top < bottom2 && bottom > top2);
    }
    public static boolean BoxToBox(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        // Collision X-Axis
        // Min x = xPos , Max x = x + bmp.getWidth


        // Collision Y-Axis
        // Min y = yPos , Max y = y + bmp.getHeight
       return true;
    }
}
