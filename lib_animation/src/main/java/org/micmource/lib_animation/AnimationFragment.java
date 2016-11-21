package org.micmource.lib_animation;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yakun on 2016/6/20.
 */
public class AnimationFragment extends Fragment {

    private TextView tv_ani;
    private Button btn_alpha;
    private Button btn_rotate;
    private Button btn_translate;
    private Button btn_scale;

    public AnimationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.animation, null);
        inflate.setBackgroundColor(Color.WHITE);
        return inflate;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.tv_ani = (TextView) view.findViewById(R.id.tv_ani);
        view.findViewById(R.id.btn_alpha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation aa = new AlphaAnimation(1,0);
                aa.setDuration(2000);
                tv_ani.startAnimation(aa);
            }
        });
        view.findViewById(R.id.btn_rotate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation rotateAnimation = new RotateAnimation(0,360,100,00);
                rotateAnimation.setDuration(2000);
                tv_ani.startAnimation(rotateAnimation);
            }
        });
        view.findViewById(R.id.btn_translate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0,200,0,300);
                translateAnimation.setDuration(2000);
                tv_ani.startAnimation(translateAnimation);
            }
        });
        view.findViewById(R.id.btn_scale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0,2,0,2);
                scaleAnimation.setDuration(2000);
                tv_ani.startAnimation(scaleAnimation);
            }
        });
        view.findViewById(R.id.btn_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation aa = new AlphaAnimation(1,0);
                aa.setDuration(2000);

                RotateAnimation rotateAnimation = new RotateAnimation(0,360,100,00);
                rotateAnimation.setDuration(2000);

                TranslateAnimation translateAnimation = new TranslateAnimation(0,200,0,300);
                translateAnimation.setDuration(2000);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0,2,0,2);
                scaleAnimation.setDuration(2000);

                AnimationSet set = new AnimationSet(true);
                set.addAnimation(aa);
                set.addAnimation(rotateAnimation);
                set.addAnimation(translateAnimation);
                set.addAnimation(scaleAnimation);
                set.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Toast.makeText(getActivity(), "开始", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(getActivity(), "结束", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                tv_ani.startAnimation(set);
            }
        });
    }
}