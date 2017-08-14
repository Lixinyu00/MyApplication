package com.example.user.text;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author LiXinyu
 * @date 2017/8/10 9:23.
 */

public class AnimText extends Activity implements View.OnClickListener {

    private ImageView imageView;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animtext);
        initViews();
        initListener();
    }

    private void initViews() {
        imageView = (ImageView) findViewById(R.id.im_1);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
    }

    private void initListener() {
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ObjectAnimator.ofFloat(v, "rotationX", 0.0f, 360.0f).setDuration(500).start();
        switch (v.getId()) {
            case R.id.btn_1:
                /**
                 * 基本动画实现
                 */
                ObjectAnimator.ofFloat(imageView, "rotationY", 0.0f, 360.0f).setDuration(500).start();
                imageView.setPivotX(0);//设置旋转轴0~100
                break;
            case R.id.btn_2:
                /**
                 * 单动画实现多效果
                 */
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                        0f, 1f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                        0, 1f);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                        0, 1f);
                ObjectAnimator.ofPropertyValuesHolder(imageView, pvhX, pvhY, pvhZ).setDuration(1000).start();
                break;
            case R.id.btn_3:
                /**
                 * 多动画依次播放
                 */
                ObjectAnimator anim31 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 2f, 1.0f);
                ObjectAnimator anim32 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 2f, 1.0f);
                ObjectAnimator anim33 = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0f, 1.0f);
                AnimatorSet animset3 = new AnimatorSet();
                animset3.setDuration(2000);
                animset3.setInterpolator(new LinearInterpolator());
                animset3.playSequentially(anim31, anim32, anim33);
                animset3.start();
                break;
            case R.id.btn_4:
                /**
                 * 多动画同时播放
                 */
                ObjectAnimator anim41 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 2f, 1.0f);
                ObjectAnimator anim42 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 2f, 1.0f);
                ObjectAnimator anim43 = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0f, 1.0f);
                AnimatorSet animset4 = new AnimatorSet();
                animset4.setDuration(2000);
                animset4.setInterpolator(new LinearInterpolator());
                animset4.playTogether(anim41, anim42, anim43);
                animset4.start();
                break;
            case R.id.btn_5:
                /**
                 * 多动画自定义顺序
                 */
                float cx = imageView.getX();
                float cy = imageView.getY();
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView, "y", cy, 0.0f);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 1.5f, 1.0f);
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(imageView, "x", cx, 0.0f);
                ObjectAnimator anim4 = ObjectAnimator.ofFloat(imageView, "y", 0.0f, cy);
                ObjectAnimator anim5 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 1.5f, 1.0f);
                ObjectAnimator anim6 = ObjectAnimator.ofFloat(imageView, "x", 0.0f, cx);
                ObjectAnimator anim7 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 2.0f, 1.0f);
                ObjectAnimator anim8 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 2.0f, 1.0f);
                AnimatorSet animset = new AnimatorSet();
                animset.play(anim1).with(anim2);
                animset.play(anim3).after(anim1);
                animset.play(anim3).with(anim4);
                animset.play(anim3).with(anim5);
                animset.play(anim6).after(anim3);
                animset.play(anim6).with(anim7);
                animset.play(anim6).with(anim8);
                animset.setDuration(2000);
                animset.start();
                break;
            case R.id.btn_6:
                /**
                 * xml定义动画
                 */
                Animator animator= AnimatorInflater.loadAnimator(this,R.animator.scalex);
                imageView.setPivotX(50);
                imageView.setPivotY(50);
                animator.setTarget(imageView);
                animator.start();
        }
    }
}
