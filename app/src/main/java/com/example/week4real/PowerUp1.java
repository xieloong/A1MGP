package com.example.week4real;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.Toast;




public class PowerUp1 implements EntityBase, Collidable {
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
    int Width, Height;
    PowerUp1(float xPosition, float yPosition, EarthEntity earthEntity)
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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.powerup1);
        DisplayMetrics metrics=_view.getResources().getDisplayMetrics();
        ScreenHeight = metrics.heightPixels;
        ScreenWidth = metrics.widthPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp,ScreenWidth/20,ScreenHeight/20,true);
        Width = scaledbmp.getWidth();
        Height = scaledbmp.getHeight();

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
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_TICKPOWERUP;}

    public static PowerUp1 Create(float xPosition, float yPosition, EarthEntity earthEntity)
    {
        PowerUp1 result = new PowerUp1(xPosition,yPosition,earthEntity);
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_ACPOWERUP);
        return result;
    }

    @Override
    public String GetType() {
        return "AirConPowerUp";
    }

    @Override
    public float GetPosX() {
        return xPos - (Width * 0.5f);
    }

    @Override
    public float GetPosY() {
        return yPos - (Height * 0.5f);
    }

    @Override
    public float GetBottom() {
        return GetPosY() + Height;
    }

    @Override
    public float GetRight() {
        return GetPosX() + Width;
    }


    @Override
    public float GetRadius() {
        return (scaledbmp.getWidth() * 0.5f);
    }

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType() != this.GetType()
                && _other.GetType() == "SmurfEntity") {  // Another entity
            earthEntity.SetHealthPoints(earthEntity.GetHealthPoints() + 20);

//            CharSequence text = "AirConPowerUp Acquired";
//            Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
//            toast.show();
            SetIsDone(true);
        }
    }
}
