package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class platform implements EntityBase{

    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos,yPos;
    private boolean isInit = false;
    private boolean hasTouched = false;
    private int renderLayer = 0;

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
        bmp = BitmapFactory.decodeResource(_view.getResources(),R.mipmap.ic_launcher_round);
        isInit = true;
    }
    @Override
     public void Update(float _dt) {
        return;
    }
    @Override
    public void Render(Canvas _canvas){
        return;
    }
    @Override
    public boolean IsInit(){
        return isInit;
    }
    @Override
    public int GetRenderLayer(){
        return renderLayer;
    }
    @Override
    public void SetRenderLayer(int _newLayer){
        renderLayer = _newLayer;
    }

    public static platform Create(){
        platform object = new platform();
        EntityManager.Instance.AddEntity(object,ENTITY_TYPE.ENT_PLATFORM);
        return object;
    }

    public static platform Create(int _layer){
        platform object = Create();
        object.SetRenderLayer(_layer);
        return object;
    }


    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_PLATFORM;
    }
}
