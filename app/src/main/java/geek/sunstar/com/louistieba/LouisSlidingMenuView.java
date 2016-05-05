package geek.sunstar.com.louistieba;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2015/10/19 0019.
 */
public class LouisSlidingMenuView extends HorizontalScrollView {

    private LinearLayout slidingLayout;
    private ViewGroup leftMenuView;
    private ViewGroup rightContentView;
    private  int mScreenWidth;
    private  int LeftMenuToRightScreenSpace=50;//50dp
    private  int LeftMenuWidth;
    private  boolean hasDoIt=false;

    public LouisSlidingMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
      WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;

        //转成PX
        LeftMenuToRightScreenSpace= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,LeftMenuToRightScreenSpace,context.getResources().getDisplayMetrics());

        Log.d("louis==xx===", "qq==LouisSlidingMenuView");
    }

    public LouisSlidingMenuView(Context context) {
        this(context, null);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed){
            scrollTo(LeftMenuWidth,0);
        }
        Log.d("louis==xx===","qq==onLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.d("louis==xx===", "qq==onMeasure");

    if (!hasDoIt){
        slidingLayout= (LinearLayout) getChildAt(0);
        leftMenuView= (ViewGroup) slidingLayout.getChildAt(0);
        rightContentView= (ViewGroup) slidingLayout.getChildAt(1);


        LeftMenuWidth=leftMenuView.getLayoutParams().width=mScreenWidth-LeftMenuToRightScreenSpace;
        rightContentView.getLayoutParams().width=mScreenWidth;
        hasDoIt=true;
    }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        Log.d("louis==xx===", "qq==onTouchEvent");

        int action=ev.getAction();
        //菜单隐藏在坐标的宽度

    switch (action){
    case MotionEvent.ACTION_UP:
        int  scalex=getScrollX();

        Log.d("louis==xx===", "scalex="+scalex+"LeftMenuWidth="+LeftMenuWidth);
        if (scalex>=LeftMenuWidth/2)
        {
            smoothScrollTo(LeftMenuWidth, 0);

        }else
        {

            smoothScrollTo(0,0);
        }
        //break;
        return  true;
    }
       return super.onTouchEvent(ev);

    }
}
