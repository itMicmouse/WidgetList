package com.lib_recycleview.view.staggeredgrid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lib_recycleview.R;

import java.util.Arrays;
import java.util.List;


/**
 * @author Paul Burke (ipaulpro)
 */
public class RecyclerStaggeredGridFragment extends Fragment {


    public RecyclerStaggeredGridFragment() {
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,  StaggeredGridLayoutManager.HORIZONTAL));

    }
}
