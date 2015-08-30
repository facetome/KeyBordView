package basic.keyboardviewa.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by basic on 2015/8/27.
 * 该view的子view
 */
public class CellView {

    private static final int TOP_LOCATION = 0;
    private static final int LEFT_LOCATION = 1;
    private static final int RIGHT_LOCATION = 2;
    private static final int BUTTOM_LOCATION = 3;
    private static final int SWEEP_DEGREE = 90;

    private boolean mPressState = false;
    private int mLocation;

    private float mLeft;
    private float mTop;
    private float mRight;
    private float mButtom;
    private String mText;
    private float mOriginX;
    private float mOriginY;
    private float mTemp;



    public CellView(float left, float top, float right, float buttom, float originX, float
            originY, float temp){
        mLeft = left;
        mButtom =buttom;
        mRight =right;
        mTop = top;
        mOriginX = originX;
        mOriginY = originX;
        mTemp =temp;
    }

    public void setLocation(int location){
           mLocation = location;
    }

    public void setPressState(boolean isPressed){
         mPressState = isPressed;
    }

    public void drawToCanvas(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(90);
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);

        int startAngle = 0;
        float width = mRight - mLeft;
        float height = mButtom - mTop;
        float textX = 0f;
        float textY = 0f;

        switch (mLocation){
            case TOP_LOCATION:
                startAngle = 225;
                textX = mOriginX - 20;
                textY = mOriginY - mTemp - 20;
                paint.setColor(Color.BLUE);
                drawFan(canvas, paint, startAngle);
                drawText(canvas, paint, textX, textY);
                break;
            case RIGHT_LOCATION:
                startAngle = -45;
                textX = mOriginX + mTemp +20;
                textY = mOriginY + 10;
                paint.setColor(Color.GRAY);
                drawFan(canvas, paint, startAngle);
                drawText(canvas, paint, textX, textY);
                break;
            case BUTTOM_LOCATION:
                startAngle = 45;
                textX = mOriginX - 20;
                textY = mOriginY + mTemp + 50;
                paint.setColor(Color.BLUE);
                drawFan(canvas, paint, startAngle);
                drawText(canvas, paint, textX, textY);
                break;
            case LEFT_LOCATION:
                startAngle = 135;
                textX = mOriginX - mTemp -70 ;
                textY = mOriginY  + 10 ;
                paint.setColor(Color.RED);
                drawFan(canvas, paint, startAngle);
                drawText(canvas, paint, textX, textY);
                break;
            default:
                break;
        }
    }

    private void drawFan(Canvas canvas, Paint paint, int startAngle){
        RectF oval = new RectF(mLeft, mTop, mRight, mButtom);
        if(mPressState){
              paint.setColor(Color.YELLOW);
        }
        canvas.drawArc(oval, startAngle, SWEEP_DEGREE, true, paint);

    }

    private void drawText(Canvas canvas, Paint paint, float x, float y){
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(20);
        paint.setTextSize(50);
        canvas.drawText(mText, x, y, paint);


    }

    public void setText(String text){
        mText = text;
    }


}
