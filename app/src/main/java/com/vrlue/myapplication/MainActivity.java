package com.vrlue.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends ActionBarActivity {
    private ViewFlipper flipper;
    private int[] resId = {R.mipmap.a, R.mipmap.b, R.mipmap.c};
    private float startX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flipper = (ViewFlipper) findViewById(R.id.flipper);
        /*
         * 动态导入的方式为ViewFlipper加入子View
        * */
        for (int i = 0; i < resId.length; i++) {
            flipper.addView(getImageView(resId[i]));
        }
/*
* 为ViewFlipper去添加动画效果
* */
        flipper.setInAnimation(this, R.anim.right_in);
        flipper.setOutAnimation(this, R.anim.right_out);
        flipper.setFlipInterval(5000);
        flipper.startFlipping();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //手指落下
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE://判断向左滑动还是向右滑动
                flipper.setFlipInterval(5000);
                flipper.startFlipping();
                break;
                //手指离开
            case MotionEvent.ACTION_UP:
                if (event.getX() - startX > 100) {
                    flipper.setInAnimation(this, R.anim.left_in);
                    flipper.setOutAnimation(this, R.anim.left_out);
                    flipper.showPrevious();//显是钱一页
                    flipper.stopFlipping();
                    flipper.startFlipping();
                } else if (startX - event.getX() > 100) {
                    flipper.setInAnimation(this, R.anim.right_in);
                    flipper.setOutAnimation(this, R.anim.right_out);
                    flipper.showNext();
                    flipper.stopFlipping();
                    flipper.startFlipping();

                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private ImageView getImageView(int resId) {
        ImageView image = new ImageView(this);
        image.setBackgroundResource(resId);
        return image;
    }
}


