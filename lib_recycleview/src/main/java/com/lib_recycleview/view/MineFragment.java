package com.lib_recycleview.view;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib_recycleview.R;
import com.lib_recycleview.layoutmanager.MineLayoutManager;
import com.lib_recycleview.view.listview.DividerItemDecoration;
import com.lib_recycleview.view.listview.RecyclerListAdapter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by yakun on 2016/6/30.
 */
public class MineFragment  extends Fragment {


    public MineFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new RecyclerView(container.getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> strings = Arrays.asList(getResources().getStringArray(R.array.dummy_items));
        RecyclerListAdapter adapter = new RecyclerListAdapter(getActivity(),strings);

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setBackgroundColor(Color.WHITE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new MineLayoutManager());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
