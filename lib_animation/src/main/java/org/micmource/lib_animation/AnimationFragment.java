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
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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
                RotateAnimation rotateAnimation = new RotateAnimation(0,360,100,100);
                rotateAnimation.setDuration(2000);
                tv_ani.setAnimation(rotateAnimation);
            }
        });
        view.findViewById(R.id.btn_translate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0,200,0,300);
                translateAnimation.setDuration(2000);
                tv_ani.setAnimation(translateAnimation);
            }
        });
        view.findViewById(R.id.btn_scale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0,2,0,2);
                scaleAnimation.setDuration(2000);
                tv_ani.setAnimation(scaleAnimation);
            }
        });
    }
}