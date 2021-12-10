package com.example.week4real;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class EarthEntity implements EntityBase{
    private Bitmap bmp = null;
    private Bitmap Scaledbmp = null;
    private boolean isDone = false;
    public float xPos,yPos;
    private boolean isInit = false;
    private boolean hasTouched = false;
    public static final float MAX_HEALTH_POINTS = 100.0f;
    private Healthbar healthbar;
    private float healthPoints;
    private float rate = 1.0f;
    private float duration = 0.0f;

    public EarthEntity(float xPos, float yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.healthbar = Healthbar.Create(this);
        this.healthPoints = MAX_HEALTH_POINTS;
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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.earth);
        isInit = true;
    }
    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
            return;

        healthPoints -= rate * _dt;

        if(healthPoints >= MAX_HEALTH_POINTS)
        {
            healthPoints = MAX_HEALTH_POINTS;
        }
        if(duration > 0)
        {
            rate = 0.5f;
            duration -= 1.0f * _dt;
        }
        if(duration <= 0.0)
        {
            duration = 0.0f;
            rate = 1.0f;
        }
    }
    @Override
    public void Render(Canvas _canvas){
        _canvas.drawBitmap(bmp, xPos, yPos ,null);
        return;
    }
    @Override
    public boolean IsInit(){
        return isInit;
    }
    @Override
    public int GetRenderLayer(){
        return LayerConstants.UI_LAYER;
    }
    @Override
    public void SetRenderLayer(int _newLayer){
        return;
    }

    public static EarthEntity Create(float xPos, float yPos){
        EarthEntity object = new EarthEntity(xPos,yPos);
        EntityManager.Instance.AddEntity(object, EntityBase.ENTITY_TYPE.ENT_EARTH);
        return object;
    }



    @Override
    public EntityBase.ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_EARTH;
    }


    public float GetHealthPoints() {
        return healthPoints;
    }

    public void SetHealthPoints(float healthPoints)
    {
        this.healthPoints = healthPoints;
    }

    public void Shield(float duration)
    {
        this.duration += duration;
    }
}
