package org.micmource.lib_greendao3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import org.micmource.lib_greendao3.entity.DaoSession;
import org.micmource.lib_greendao3.entity.Name;
import org.micmource.lib_greendao3.entity.NameDao;
import org.micmource.lib_greendao3.greendaoapp.GreendaoApplication;

/**
 * Created by yakun on 2016/6/20.
 */
public class GreendaoFragment extends Fragment {



    public GreendaoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.greendao3, null);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaoSession daoSession = GreendaoApplication.getDaoSession();
        final NameDao nameDao = daoSession.getNameDao();
        view.findViewById(R.id.insertone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name name = new Name();
                name.setName("yakun");
                name.setRepos(222);
                nameDao.insert(name);
            }
        });
    }
}