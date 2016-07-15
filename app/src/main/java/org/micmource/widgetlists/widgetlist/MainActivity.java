package org.micmource.widgetlists.widgetlist;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.jsandroid.JsFragment;
import com.lib_fragmentdialog.FriendActivity;
import com.lib_recycleview.drag.grid.RecyclerGridFragmentDrag;
import com.lib_recycleview.drag.list.RecyclerListFragmentDrag;
import com.lib_recycleview.view.MineFragment;
import com.lib_recycleview.view.flowlayout.RecyclerFlowFragment;
import com.lib_recycleview.view.grid.RecyclerGridFragment;
import com.lib_recycleview.view.listview.RecyclerListFragment;
import com.lib_recycleview.view.staggeredgrid.RecyclerStaggeredGridFragment;
import com.livebutton.LiveButtonFragment;

import org.micmource.realmreserve.RealmeFragment;
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
                return;
            case 7:
                fragment = new LiveButtonFragment();
                break;
            case 8:
                fragment = new RecyclerFlowFragment();
                break;
            case 9:
                fragment = new MineFragment();
                break;
            case 10:
                fragment = new RealmeFragment();
                break;
            case 11:
                fragment = new JsFragment();
                break;

        }
        getFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

}
