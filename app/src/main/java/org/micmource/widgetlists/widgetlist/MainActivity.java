/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.micmource.widgetlists.widgetlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import org.micmource.widgetlists.widgetlist.fragmentdialog.FriendActivity;
import org.micmource.widgetlists.widgetlist.recycleview.view.staggeredgrid.RecyclerStaggeredGridFragment;
import org.micmource.widgetlists.widgetlist.tag.DoorActivity;
import org.micmource.widgetlists.widgetlist.widget.drag.grid.RecyclerGridFragment;
import org.micmource.widgetlists.widgetlist.widget.drag.list.RecyclerListFragment;

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
                fragment = new RecyclerListFragment();
                break;

            case 1:
                fragment = new RecyclerGridFragment();
                break;
            case 2:
                intent = new Intent(MainActivity.this, FriendActivity.class);
                startActivity(intent);
                return;
            case 3:
                fragment = new org.micmource.widgetlists.widgetlist.recycleview.view.listview.RecyclerListFragment();
               break;
            case 4:
                fragment = new org.micmource.widgetlists.widgetlist.recycleview.view.grid.RecyclerGridFragment();
               break;
            case 5:
                fragment = new RecyclerStaggeredGridFragment();
               break;
            case 6:
                intent = new Intent(MainActivity.this, DoorActivity.class);
                startActivity(intent);
                return;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

}
