package com.example.xiaoyangchun.animatortest.ui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaoyangchun.animatortest.Interface.DrawView;
import com.example.xiaoyangchun.animatortest.R;
import com.example.xiaoyangchun.animatortest.presenter.AnimPresenter;
import com.example.xiaoyangchun.animatortest.widgets.GraphView;

public class MainActivity extends Activity implements DrawView{

    private AnimPresenter mPresenter;
    private GraphView mGraphView;
    private int[] animType = new int[] {
            0x0001,0x0002,0x0003,0x0004,0x0005,0x0006,0x0007,0x0008,0x0009,
    };
    private Button[]btus;
    private TextView tv;
    private View bollView;
    String info;
    private float X,Y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mGraphView = findViewById(R.id.Graph_view);
        bollView = findViewById(R.id.boll_view);
        tv = findViewById(R.id.tv);
        X = bollView.getTranslationX();
        Y = bollView.getTranslationY();
        mPresenter = new AnimPresenter();
        mPresenter.attachView(this);

        btus = new Button[9];
        LinearLayout mButtonLay = findViewById(R.id.btus_lay);
        for (int i = 0; i < 9; i++) {
            btus[i] = (Button) mButtonLay.getChildAt(i);
            final int index = i;
            btus[i].setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    bollView.setTranslationX(X);
                    bollView.setTranslationY(Y);
                    mGraphView.reset();
                    info = mPresenter.startAnim(animType[index]);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onBegin() {
        tv.setText("");
    }

    @Override
    public void onEnd() {
        tv.setText(info);
    }

    @Override
    public void onDraw() {
        mGraphView.drawPath(Y - bollView.getTranslationY());
    }

    @Override
    public ObjectAnimator getAnimator() {
        return ObjectAnimator
                .ofFloat(bollView, "translationY", 0,-mGraphView.getHeight()/2+200)
                .setDuration(2000);
    }

}
