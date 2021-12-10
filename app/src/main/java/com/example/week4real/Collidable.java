package com.example.week4real;

// Created by TanSiewLan2021

public interface Collidable {
    String GetType();

    float GetPosX();
    float GetPosY();
    float GetBottom();
    float GetRight();
    float GetRadius();

    void OnHit(Collidable _other);

}

