package basic.keyboardviewa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by basic on 2015/8/27.
 */
public class KeyBoardView extends View {

    private boolean mIsInitView = false;
    private int mViewWidth;
    private int mViewHeight;
    private CellView[] mCellGroup = new CellView[5];
    private int mTemp;
    private String[] mContent = new String[]{"北", "西", "东", "南", "中"};
    private OnClickListener mListener;


    public KeyBoardView(Context context) {
        super(context);
    }

    public KeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (!mIsInitView) {
            initView();
        }
        drawCellGroup(canvas);
    }

    private void initView() {
        mIsInitView = true;
        mViewWidth = getWidth();
        mViewHeight = getHeight();

        mTemp = mViewWidth >= mViewHeight ? mViewHeight : mViewWidth;
        int leftPadding = 0;
        int topPadding = 0;
        if (mTemp <= mViewHeight) {
            topPadding = (mViewHeight - mTemp) / 2;
        }
        if (mTemp <= mViewWidth) {
            leftPadding = (mViewWidth - mTemp) / 2;
        }

        //获取整个扇形绘制界面的外接正方形
        int left = leftPadding;
        int top = topPadding;
        int right = left + mTemp;
        int buttom = top + mTemp;

        for (int i = 0; i < mCellGroup.length; i++) {
            mCellGroup[i] = new CellView(left, top, right, buttom, mViewWidth / 2, mViewHeight /
                    2, mTemp / 4);
            mCellGroup[i].setLocation(i);
            mCellGroup[i].setText(mContent[i]);
        }

    }

    private void drawCellGroup(Canvas canvas) {
        for (int i = 0; i < mCellGroup.length; i++) {
            mCellGroup[i].drawToCanvas(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        float x = event.getX();
        float y = event.getY();

        boolean isNeedDraw = true;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isNeedDraw = true;
                break;
            case MotionEvent.ACTION_UP:
                isNeedDraw = false;
                break;
            case MotionEvent.ACTION_MOVE:
                isNeedDraw = false;
                break;
            default:
                break;
        }

        for (int i = 0; i < mCellGroup.length; i++) {
            if (isNeedDraw) {
                mCellGroup[i].checkBounds(x, y);
                if (mCellGroup[i].getPressState()) {
                    if (mListener != null) {
                        mListener.onClick(i);
                    }
                }
            }
        }
        invalidate();
        return true;
    }

    public void setOnClickListener(OnClickListener listener) {
        mListener = listener;
    }

    public interface OnClickListener {
        void onClick(int witch);
    }
}
