package com.example.week4real;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class EarthEntity implements EntityBase{
    private Bitmap bmp = null;
    private Bitmap Scaledbmp = null;
    private boolean isDone = false;
    private float xPos,yPos;
    private boolean isInit = false;
    private boolean hasTouched = false;
    public static final int MAX_HEALTH_POINTS = 10;
    private Healthbar healthbar;
    private int healthPoints;
    public EarthEntity(Context context,float xPos, float yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.healthbar = Healthbar.Create(context,this);
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
    }
    @Override
    public void Update(float _dt) {

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

    public static EarthEntity Create(Context context, float xPos, float yPos){
        EarthEntity object = new EarthEntity(context,xPos,yPos);
        EntityManager.Instance.AddEntity(object, EntityBase.ENTITY_TYPE.ENT_EARTH);
        return object;
    }



    @Override
    public EntityBase.ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_EARTH;
    }

    @Override
    public float GetPositionX(){
        return xPos;
    }

    @Override
    public float GetPositionY(){
        return yPos;
    }

    public int GetHealthPoints() {
        return healthPoints;
    }
}
