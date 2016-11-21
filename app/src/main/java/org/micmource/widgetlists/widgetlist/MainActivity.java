package org.micmource.widgetlists.widgetlist;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.example.jsandroid.JsFragment;
import com.example.lib_greendao3.GreendaoFragment;
import com.example.lib_map.StartActivity;
import com.example.lib_sqldelight.SqldeLightFragment;
import com.lib_fragmentdialog.FriendActivity;
import com.lib_recycleview.drag.grid.RecyclerGridFragmentDrag;
import com.lib_recycleview.drag.list.RecyclerListFragmentDrag;
import com.lib_recycleview.view.MineFragment;
import com.lib_recycleview.view.flowlayout.RecyclerFlowFragment;
import com.lib_recycleview.view.grid.RecyclerGridFragment;
import com.lib_recycleview.view.listview.RecyclerListFragment;
import com.lib_recycleview.view.staggeredgrid.RecyclerStaggeredGridFragment;
import com.livebutton.LiveButtonFragment;

import org.micmource.lib_animation.AnimationFragment;
import org.micmource.lib_bluetooth_printer.BluetoothPrinterActivity;
import org.micmource.lib_v4.DrawerFragment;
import org.micmource.movieseat.SeatFragment;
import org.micmource.pointer.TemperatureViewFragment;
import org.micmource.realmreserve.RealmeFragment;
import org.micmource.widgetlists.widgetlist.DevInfo.DevInfoActivity;
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
                intent = new Intent(MainActivity.this, DevInfoActivity.class);
                startActivity(intent);
                return;
            case 1:
                fragment = new RecyclerListFragmentDrag();
                break;
            case 2:
                fragment = new RecyclerGridFragmentDrag();
                break;
            case 3:
                intent = new Intent(MainActivity.this, FriendActivity.class);
                startActivity(intent);
                return;
            case 4:
                fragment = new RecyclerListFragment();
               break;
            case 5:
                fragment = new RecyclerGridFragment();
               break;
            case 6:
                fragment = new RecyclerStaggeredGridFragment();
               break;
            case 7:
                intent = new Intent(MainActivity.this, DoorActivity.class);
                startActivity(intent);
                return;
            case 8:
                fragment = new LiveButtonFragment();
                break;
            case 9:
                fragment = new RecyclerFlowFragment();
                break;
            case 10:
                fragment = new MineFragment();
                break;
            case 11:
                fragment = new RealmeFragment();
                break;
            case 12:
                fragment = new JsFragment();
                break;
            case 13:
                fragment = new SeatFragment();
                break;
            case 14:
                intent = new Intent(MainActivity.this, BluetoothPrinterActivity.class);
                startActivity(intent);
                return;
            case 15:
                fragment = new SqldeLightFragment();
                break;
            case 16:
                fragment = new TemperatureViewFragment();
                break;
            case 17:
                fragment = new AnimationFragment();
                break;
            case 18:
                intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
                return;
            case 19:
                intent = new Intent(MainActivity.this, com.zhiyijiankang.cloudclinck.lib_push.MainActivity.class);
                startActivity(intent);
                return;
            case 20:
                fragment = new DrawerFragment();
                break;
            case 21:
                fragment = new GreendaoFragment();
                break;
        }
        getFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit();


        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）

        int i = dp2px(768);
        System.out.println(768);
        int i1 = sp2px(4);
        System.out.println(i1);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

}
