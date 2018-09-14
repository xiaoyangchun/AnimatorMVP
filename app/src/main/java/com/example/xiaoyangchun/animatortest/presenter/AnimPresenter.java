package com.example.xiaoyangchun.animatortest.presenter;

import com.example.xiaoyangchun.animatortest.Interface.AnimCallBack;
import com.example.xiaoyangchun.animatortest.Interface.DrawView;
import com.example.xiaoyangchun.animatortest.model.AnimatorModel;

public class AnimPresenter {

    private DrawView drawView;

    public void attachView(DrawView drawView) {
        this.drawView = drawView;
    }

    public void detachView() {
        this.drawView = null;
    }

    public boolean isViewAttached() {
        return drawView!=null;
    }

    public String startAnim(int type) {
        if (isViewAttached()) {
            AnimatorModel.playAnimator(drawView.getAnimator(), type,
                    new AnimCallBack() {
                        @Override
                        public void onStart() {
                            drawView.onBegin();
                        }

                        @Override
                        public void onFinish() {
                            drawView.onEnd();
                        }

                        @Override
                        public void onDraw() {
                            drawView.onDraw();
                        }
                    });
            return AnimatorModel.getInfoText();
        }
        return null;
    }
}
