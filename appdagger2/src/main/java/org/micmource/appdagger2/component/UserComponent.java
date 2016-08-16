package org.micmource.appdagger2.component;

import org.micmource.appdagger2.MainActivity;
import org.micmource.appdagger2.module.ActivityScope;
import org.micmource.appdagger2.module.UserModule;

import dagger.Component;

/**
 * Created by yakun on 2016/8/16.
 */
@ActivityScope
@Component(modules = {UserModule.class})
public interface UserComponent {
    void inject(MainActivity activity);
}
