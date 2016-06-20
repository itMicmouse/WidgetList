package org.micmource.widgetlists.widgetlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.lib_fragmentdialog.FriendActivity;
import com.lib_recycleview.drag.grid.RecyclerGridFragmentDrag;
import com.lib_recycleview.drag.list.RecyclerListFragmentDrag;
import com.lib_recycleview.view.grid.RecyclerGridFragment;
import com.lib_recycleview.view.listview.RecyclerListFragment;
import com.lib_recycleview.view.staggeredgrid.RecyclerStaggeredGridFragment;
import com.livebutton.LiveButtonFragment;

import org.micmource.widgetlists.widgetlist.tag.DoorActivity;

/**
 * @author yangyakun
 */
public class MainActivity extends ActionBarActivity implements MainFragment.OnListItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        if (savedInstanceState == null) {
            MainFragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }
    }

    @Override
    public void onListItemClick(int position) {
        Fragment fragment = null;
        Intent intent = null;
        switch (position) {
            case 0:
                fragment = new RecyclerListFragmentDrag();
                break;
            case 1:
                fragment = new RecyclerGridFragmentDrag();
                break;
            case 2:
                intent = new Intent(MainActivity.this, FriendActivity.class);
                startActivity(intent);
                return;
            case 3:
                fragment = new RecyclerListFragment();
               break;
            case 4:
                fragment = new RecyclerGridFragment();
               break;
            case 5:
                fragment = new RecyclerStaggeredGridFragment();
               break;
            case 6:
                intent = new Intent(MainActivity.this, DoorActivity.class);
                startActivity(intent);
            case 7:
                fragment = new LiveButtonFragment();
                return;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

}
