package org.micmource.appdagger2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yakun on 2016/8/16.
 */
public class UserAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<String> users;

    @Inject
    public UserAdapter(LayoutInflater inflater, List<String> users) {
        this.inflater = inflater;
        this.users = users;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
