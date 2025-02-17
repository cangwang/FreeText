package com.lltvcn.freefont.core.animation;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by zhaolei on 2017/12/5.
 */

public class TAnimationQueen extends BaseAnimation{
    private ArrayList<BaseAnimation> animations = new ArrayList<>();
    private Field fRunning;

    @SuppressLint("SoonBlockedPrivateApi")
    public TAnimationQueen(TextView tv) {
        super(tv);
//        try {
//            fRunning = ValueAnimator.class.getDeclaredField("mRunning");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
    }

    public void addAnimation(BaseAnimation animation){
        if(!animations.contains(animation)){
            animations.add(animation);
        }
    }

    @Deprecated
    @Override
    public ValueAnimator setDuration(long duration) {
        return this;
    }


    @Override
    public long getDuration() {
        checkDuration();
        return super.getDuration();
    }

    @Override
    public void start() {
        checkDuration();
        super.start();
//        if (fRunning == null) {
//            Log.e("TAnimationQueen", "start fRunning is null");
//            return;
//        }
//        try {
//            fRunning.setAccessible(true);
//            for (BaseAnimation animation:animations) {
//                fRunning.setBoolean(animation,true);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        fRunning.setAccessible(false);
    }

    private void checkDuration(){
        long duration = 0;
        for (BaseAnimation animation:animations) {
            duration += animation.getDuration();
        }
        if(duration!=super.getDuration()){
            super.setDuration(duration);
        }
    }

    @Override
    public void reverse() {
        checkDuration();
        super.reverse();
    }

    @Override
    public void end() {
        super.end();
//        if (fRunning == null) {
//            Log.e("TAnimationQueen", "end fRunning is null");
//            return;
//        }
//        for (BaseAnimation animation:animations) {
//            fRunning.setAccessible(true);
//            try {
//                fRunning.setBoolean(animation,false);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            fRunning.setAccessible(false);
//        }
    }


    @Override
    public void transformCanvas(int index, RectF rect, Canvas canvas, Paint paint) {
        long currentTime = (long) ((Float)getAnimatedValue()*getDuration());
        long time = 0;
        long duration;
        boolean hasTrans = false;
        for (BaseAnimation animation:animations) {
            duration = animation.getDuration();
            if(time<=currentTime&&time+duration>currentTime){
                hasTrans = true;
                Log.i("kkais","currentTime:"+currentTime+"duration:"+duration+"::time"+time);
                animation.setCurrentPlayTime(currentTime-time);
                animation.transformCanvas(index,rect,canvas,paint);
                break;
            }
            time+=duration;
        }
        if(!hasTrans){
            Log.i("ddsiis","no");
        }
    }


}
