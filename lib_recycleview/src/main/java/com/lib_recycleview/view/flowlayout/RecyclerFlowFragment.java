package com.lib_recycleview.view.flowlayout;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib_recycleview.R;
import com.lib_recycleview.layoutmanage.Alignment;
import com.lib_recycleview.layoutmanage.FlowLayoutManager;

import java.util.Arrays;
import java.util.List;


/**
 * @author Paul Burke (ipaulpro)
 */
public class RecyclerFlowFragment extends Fragment {

    FlowLayoutManager flowLayoutManager;

    RecyclerView recyclerView;

    public RecyclerFlowFragment() {
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
        RecyclerListAdapter adapter = new RecyclerListAdapter(1, DemoUtil.generate(2000, 3, 13, 1, false));

        recyclerView = (RecyclerView) view;
        recyclerView.setBackgroundColor(Color.WHITE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        flowLayoutManager = new FlowLayoutManager().singleItemPerLine();
        flowLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(flowLayoutManager);

    }

    private void loadSettingsFromSharedPref() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String itemsPerLineString = sharedPreferences.getString("pref_key_align", getString(R.string.pref_max_items_per_line_default));
        int itemsPerLine = Integer.valueOf(itemsPerLineString);

        String alignmentString = sharedPreferences.getString("pref_key_alignment", getString(R.string.pref_alignment_default));
        int alignmentInt = Integer.valueOf(alignmentString);
        Alignment[] alignments = Alignment.values();
        Alignment selectedAlignment = Alignment.LEFT;
        for (Alignment alignment : alignments) {
            if (alignment.ordinal() == alignmentInt) {
                selectedAlignment = alignment;
                break;
            }
        }
//		boolean showMeta = sharedPreferences.getBoolean(getString(R.string.pref_key_show_meta), false);

        flowLayoutManager.maxItemsPerLine(itemsPerLine);
        flowLayoutManager.setAlignment(selectedAlignment);
        RecyclerListAdapter demoAdapter = (RecyclerListAdapter)recyclerView.getAdapter();
//		demoAdapter.setShowMeta(showMeta);
        String maxLinesPerItemString = sharedPreferences.getString("pref_key_max_lines_per_item", getString(R.string.pref_max_lines_per_item_default));
        int maxLinesPerItem = Integer.valueOf(maxLinesPerItemString);
        demoAdapter.newItems(maxLinesPerItem, DemoUtil.generate(demoAdapter.getItemCount(), 3, 13, maxLinesPerItem, false));
        recyclerView.getAdapter().notifyItemRangeChanged(0, recyclerView.getAdapter().getItemCount());
    }
}
