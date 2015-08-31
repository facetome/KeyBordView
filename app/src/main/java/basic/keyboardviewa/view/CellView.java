package basic.keyboardviewa.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
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
    private static final int CENTER_LOCATION = 4;
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
        mOriginY = originY;
        mTemp =temp;
    }

    public void setLocation(int location){
           mLocation = location;
    }

    public void setPressState(boolean isPressed){
         mPressState = isPressed;
    }

    public boolean getPressState(){
        return mPressState;
    }

    public void drawToCanvas(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);

        int startAngle = 0;
        float textX = 0f;
        float textY = 0f;

        switch (mLocation){
            case TOP_LOCATION:
                startAngle = 225;
                textX = mOriginX;
                textY = mOriginY - mTemp;
                paint.setColor(Color.BLUE);
                drawFan(canvas, paint, startAngle);
                drawText(canvas, paint, textX, textY);
                break;
            case RIGHT_LOCATION:
                startAngle = -45;
                textX = mOriginX + mTemp;
                textY = mOriginY;
                paint.setColor(Color.GRAY);
                drawFan(canvas, paint, startAngle);
                drawText(canvas, paint, textX, textY);
                break;
            case BUTTOM_LOCATION:
                startAngle = 45;
                textX = mOriginX;
                textY = mOriginY + mTemp;
                paint.setColor(Color.BLUE);
                drawFan(canvas, paint, startAngle);
                drawText(canvas, paint, textX, textY);
                break;
            case LEFT_LOCATION:
                startAngle = 135;
                textX = mOriginX - mTemp;
                textY = mOriginY;
                paint.setColor(Color.RED);
                drawFan(canvas, paint, startAngle);
                drawText(canvas, paint, textX, textY);
                break;
            case CENTER_LOCATION:
                drawCircle(canvas);
                drawText(canvas,paint, mOriginX, mOriginY );
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
        paint.setTextAlign(Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(20);
        paint.setTextSize(50);

        //计算文字的宽度
        Rect rectF = new Rect();
        paint.getTextBounds(mText, 0, mText.length(), rectF);
        int width = rectF.width();
        int height = rectF.height();

        switch (mLocation){
            case TOP_LOCATION:
                y = y - height;
                break;
            case  LEFT_LOCATION:
                x =x - width;
                y = y + 0.5f * height;
                break;
            case RIGHT_LOCATION:
                x= x+ width;
                y = y + 0.5f * height;
                break;
            case BUTTOM_LOCATION:
                y = y + 1.5f * height;
                break;
            case CENTER_LOCATION:
                y = y+ 0.5f * height;
                break;
            default:
                break;
        }
        canvas.drawText(mText, x, y, paint);


    }

    private void drawCircle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        if(mPressState){
            paint.setColor(Color.YELLOW);
        }
        paint.setAntiAlias(true);
        canvas.drawCircle(mOriginX, mOriginY, mTemp, paint);
    }

    public void setText(String text){
        mText = text;
    }

    //判断一点十是否在一个扇形中
    public void checkBounds(float x, float y) {
        //首先判断是否在大圆之中 ,小圆之外
        double radius = Math.sqrt(Math.pow(x - mOriginX, 2) + Math.pow(y - mOriginY, 2));
        int direction = 1001;
        if (radius < 4 * mTemp && radius > mTemp) {
            double angle = Math.toDegrees(Math.atan2((y - mOriginY) , (x-mOriginX)));
            //math.atan2没有弄懂
            Log.d("wangliangsen", angle + "");
            if (angle > -45 && angle <= 45) {
                direction = RIGHT_LOCATION;
            } else if (angle > 45 && angle <= 135) {
                direction = BUTTOM_LOCATION;
            } else if ((angle > 135 && angle <= 180) || (angle >= -180 && angle < -135)) {
                direction = LEFT_LOCATION;
            } else if(angle > -135 && angle <= -45){
                direction = TOP_LOCATION;
            }

        } else if (radius >= 0 && radius <= mTemp) {
            direction = CENTER_LOCATION;
        }
        boolean isChecked = direction == mLocation ? true : false;
        if (isChecked) {
            mPressState = true;
        } else {
            mPressState = false;
        }
    }




}
