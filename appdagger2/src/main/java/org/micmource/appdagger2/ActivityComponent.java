package org.micmource.appdagger2;

import dagger.Component;

/**
 * Created by yakun on 2016/8/23.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
