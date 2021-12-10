package com.example.week4real;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class JumpButtonEntity implements EntityBase{

    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos,yPos;
    private boolean isInit = false;
    private boolean hasTouched = false;


    JumpButtonEntity(float xPosition,float yPosition){
        xPos = xPosition;
        yPos = yPosition;
    }

    @Override
    public boolean IsDone(){
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone){
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view){
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.jumpbutton);
    }

    @Override
    public void Update(float _dt){
        // Update Player when Button is Pressed using TouchManager
        xPos -= 10*_dt;
    }
    @Override
    public void Render(Canvas _canvas){
        // Basic Rendering
        _canvas.drawBitmap(bmp, xPos, yPos ,null);
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

    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_JUMPBUTTON;
    }

    public static JumpButtonEntity Create(float xPosition, float yPosition){
        JumpButtonEntity object = new JumpButtonEntity(xPosition,yPosition);
        EntityManager.Instance.AddEntity(object,ENTITY_TYPE.ENT_JUMPBUTTON);
        return object;
    }

    public static JumpButtonEntity Create(int _layer,float xPosition, float yPosition){
        JumpButtonEntity object = Create(xPosition,yPosition);
        object.SetRenderLayer(_layer);
        return object;
    }

}
