package org.micmource.lib_greendao3.dbhelper.impl;

import org.greenrobot.greendao.AbstractDao;
import org.micmource.lib_greendao3.dbhelper.BaseDbHelper;
import org.micmource.lib_greendao3.entity.Name;

/**
 * Created by yakun on 2016/12/15.
 */

public class NameHelper extends BaseDbHelper<Name,Long> {
    public NameHelper(AbstractDao dao) {
        super(dao);
    }
}
