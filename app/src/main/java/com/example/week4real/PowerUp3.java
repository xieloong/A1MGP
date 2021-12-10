package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class PowerUp3 implements EntityBase,Collidable{
    private Bitmap bmp = null;
    private Bitmap scaledbmp =null;
    private float xPos = 0;
    private float xStart = 0;
    private float yPos = 0;
    private float screenHeight = 0;
    private float speed = 0;
    private boolean isDone = false;
    private boolean isInit = false;
    EarthEntity earthEntity = null;
    int ScreenWidth, ScreenHeight;

    PowerUp3(float xPosition, float yPosition, EarthEntity earthEntity)
    {
        xPos = xPosition;
        yPos = yPosition;
        this.earthEntity = earthEntity;
    }


    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {

        // New method using our own resource manager : Returns pre-loaded one if exists
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.powerup3);
        DisplayMetrics metrics=_view.getResources().getDisplayMetrics();
        ScreenHeight = metrics.heightPixels;
        ScreenWidth = metrics.widthPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp,ScreenWidth/20,ScreenHeight/20,true);


        isInit = true;
    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
            return;

        xPos -= _dt * 100;
        // Delete Powerup outside of screen
        if(xPos < -ScreenWidth)
        {
            SetIsDone(true);
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(scaledbmp,xPos,yPos, null);
    }

    @Override
    public boolean IsInit() {

        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.UI_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }



    @Override
    public EntityBase.ENTITY_TYPE GetEntityType(){ return EntityBase.ENTITY_TYPE.ENT_TICKPOWERUP;}

    public static PowerUp3 Create(float xPosition, float yPosition, EarthEntity earthEntity)
    {
        PowerUp3 result = new PowerUp3(xPosition,yPosition,earthEntity);
        EntityManager.Instance.AddEntity(result, EntityBase.ENTITY_TYPE.ENT_SEEDPOWERUP);
        return result;
    }

    @Override
    public String GetType() {
        return "SeedPowerUp";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return scaledbmp.getWidth();
    }

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType() != this.GetType()
                && _other.GetType() == "PlayerEntity") {  // Another entity
            earthEntity.SetHealthPoints(earthEntity.GetHealthPoints() + 10);
            SetIsDone(true);
        }
    }
}
