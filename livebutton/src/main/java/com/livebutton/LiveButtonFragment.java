package com.livebutton;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * Created by yakun on 2016/6/20.
 */
public class LiveButtonFragment extends Fragment {


    private LiveButton button;
    private final int PRESSED_HEIGHT = 0;
    private final int NORMAL_HEIGHT = 1;
    private final int CORNERS = 2;

    public LiveButtonFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.livebutton, null);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = (LiveButton) view.findViewById(R.id.button);
        SeekBar seekPressedHeight = (SeekBar) view.findViewById(R.id.seekPressedHeight);
        SeekBar seekNormalHeight = (SeekBar) view.findViewById(R.id.seekNormalHeight);
        SeekBar seekCorners = (SeekBar) view.findViewById(R.id.seekCorners);

        setOnSeekBarChangeListener(seekPressedHeight, PRESSED_HEIGHT);
        setOnSeekBarChangeListener(seekNormalHeight, NORMAL_HEIGHT);
        setOnSeekBarChangeListener(seekCorners, CORNERS);
    }

    public void setOnSeekBarChangeListener(SeekBar seekBar, final int id) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (id) {
                    case PRESSED_HEIGHT:
                        button.setPressedHeight(seekBar.getProgress());
                        break;
                    case NORMAL_HEIGHT:
                        button.setNormalHeight(seekBar.getProgress());
                        break;
                    case CORNERS:
                        button.setCorners(seekBar.getProgress());
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}