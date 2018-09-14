package com.example.xiaoyangchun.animatortest.model;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.xiaoyangchun.animatortest.Interface.AnimCallBack;

public class AnimatorModel {

    public final static int ACC_DEC_INERPOLATOR = 0x0001;
    public final static int ACC_INTERPOLATOR = 0x0002;
    public final static int ANTI_INTERPOLATOR = 0x0003;
    public final static int ANTI_OVER_SHOOT_INTERPOLATOR = 0x0004;
    public final static int BOUNCE_INTEPOLATOR = 0x0005;
    public final static int CYCLE_INTERPOLATOR = 0x0006;
    public final static int DEC_INTERPOLATOR = 0x0007;
    public final static int LINEAR_INTERPOLATOR = 0x0008;
    public final static int OVER_SHOOT_INTERPOLATOR = 0x0009;

    public static Interpolator interpolator;
    public static ObjectAnimator mAnimator;

    private static String txt;
    public static String[] info = new String[]{
            "中间快两头慢",
            "逐渐加速",
            "开始的时候向后然后向前甩",
            "开始的时候向后然后向前甩一定值后返回最后的值",
            "动画结束的时候弹起",
            "动画循环播放特定的次数，速率改变沿着正弦曲线",
            "在动画开始的地方快然后慢",
            "线性改变",
            "向前甩一定值后再回到原来位置"
    };

    public static void playAnimator(ObjectAnimator animator, int type, final AnimCallBack callBack) {
        interpolator = getInerpolator(type);
        if (mAnimator != null) {
            mAnimator.cancel();
        }
        mAnimator = animator;
        animator.setInterpolator(interpolator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                callBack.onDraw();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (callBack != null) {
                    callBack.onStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (callBack != null) {
                    callBack.onFinish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    private static Interpolator getInerpolator(int type) {
        switch (type) {
            case ACC_DEC_INERPOLATOR:
                txt = info[0];
                return new AccelerateDecelerateInterpolator();
            case ACC_INTERPOLATOR:
                txt = info[1];
                return new AccelerateInterpolator(2.0f);
            case ANTI_INTERPOLATOR:
                txt = info[2];
                return new AnticipateInterpolator(2.0f);
            case ANTI_OVER_SHOOT_INTERPOLATOR:
                txt = info[3];
                return new AnticipateOvershootInterpolator(2.0f);
            case BOUNCE_INTEPOLATOR:
                txt = info[4];
                return new BounceInterpolator();
            case CYCLE_INTERPOLATOR:
                txt = info[5];
                return new CycleInterpolator(2);
            case DEC_INTERPOLATOR:
                txt = info[6];
                return new DecelerateInterpolator(2.0f);
            case LINEAR_INTERPOLATOR:
                txt = info[7];
                return new LinearInterpolator();
            case OVER_SHOOT_INTERPOLATOR:
                txt = info[8];
                return new OvershootInterpolator(2.0f);
        }
        return null;
    }

    public static String getInfoText() {
        return interpolator.getClass().getSimpleName() + "\n" + txt;
    }

}
