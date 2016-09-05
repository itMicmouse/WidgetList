package org.micmource.pointer;

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
public class TemperatureViewFragment extends Fragment {


    private TemperatureView tv_one;
    private final int PRESSED_HEIGHT = 0;
    private final int NORMAL_HEIGHT = 1;
    private final int CORNERS = 2;

    public TemperatureViewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.temperature, null);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_one = (TemperatureView) view.findViewById(R.id.tv_one);
        SeekBar seekCorners = (SeekBar) view.findViewById(R.id.seekCorners);

        setOnSeekBarChangeListener(seekCorners, CORNERS);
        tv_one.setCurrentTemp(12.3f);
    }

    public void setOnSeekBarChangeListener(SeekBar seekBar, final int id) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (id) {
                    case CORNERS:
                        tv_one.setCurrentTemp(progress);
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