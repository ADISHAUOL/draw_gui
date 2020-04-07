package com.example.draw_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

class DrawingView extends View
{
    public ViewGroup.LayoutParams params;
    private Path path=new Path();
    private Paint brush=new Paint();


    public DrawingView(Context context)
    {
        super(context);

        int paintColor=0xFF660000;
        brush.setAntiAlias(true);
        brush.setColor(paintColor);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(9F);


        params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setColor(String newColor)
    {
        invalidate();
        brush.setColor(Color.parseColor(newColor));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float pointX=event.getX();
        float pointY=event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX,pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX,pointY);
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawPath(path,brush);
    }
}

