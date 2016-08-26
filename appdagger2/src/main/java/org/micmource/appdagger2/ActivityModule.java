package org.micmource.appdagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yakun on 2016/8/23.
 */
@Module
public class ActivityModule {
    private MainActivity mainActivity;

    public ActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Provides
    public MainActivity provideMainActivity() {
        return mainActivity;
    }
    @Provides
    public User provideUser() {
        return new User("from Provides");
    }

    @Provides
    public DaggerPresenter provideDaggerPresenter(MainActivity mainActivity,User user) {
        return new DaggerPresenter(mainActivity,user);
    }

}
