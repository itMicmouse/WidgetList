package org.itmicmource.androidjs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.widget.SeekBar;

/**
 * Created by yakun on 2016/6/20.
 */
public class LiveButtonFragment extends WebViewFragment {

    private final int PRESSED_HEIGHT = 0;
    private final int NORMAL_HEIGHT = 1;
    private final int CORNERS = 2;

    public LiveButtonFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout, null);
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        
    }
}
