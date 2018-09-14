package com.example.xiaoyangchun.animatortest.Interface;

import android.animation.ObjectAnimator;

public interface DrawView {

    void onBegin();

    void onEnd();

    void onDraw();

    ObjectAnimator getAnimator();
}
