package com.lib_fragmentdialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.lib_fragmentdialog.function.BaseDialogFragment;
import com.lib_fragmentdialog.function.DialogFactory;

/**
 * @author yangyakun
 */
public abstract class BaseActivity extends FragmentActivity {

    protected DialogFactory mDialogFactory;

    public BaseDialogFragment.BaseDialogListener getDialogListener() {
        return mDialogFactory.mListenerHolder.getDialogListener();
    }

    /**
     * 清空DialogListenerHolder中的dialog listener
     */
    public void clearDialogListener() {
        mDialogFactory.mListenerHolder.setDialogListener(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDialogFactory.mListenerHolder.saveDialogListenerKey(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogFactory = new DialogFactory(getSupportFragmentManager(), savedInstanceState);
        mDialogFactory.restoreDialogListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
