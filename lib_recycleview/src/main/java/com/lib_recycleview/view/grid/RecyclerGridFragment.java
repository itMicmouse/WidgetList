package com.lib_recycleview.view.grid;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lib_recycleview.R;

import java.util.Arrays;
import java.util.List;


/**
 * @author yangyakun
 */
public class RecyclerGridFragment extends Fragment {

    public RecyclerGridFragment() {
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
        final RecyclerListAdapter adapter = new RecyclerListAdapter(getActivity(), strings);

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setBackgroundColor(Color.WHITE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        final int spanCount = getResources().getInteger(R.integer.grid_columns);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        DividerGridItemDecoration dividerItemDecoration = new DividerGridItemDecoration(getActivity());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
