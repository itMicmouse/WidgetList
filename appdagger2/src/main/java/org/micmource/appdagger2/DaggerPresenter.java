package org.micmource.appdagger2;

/**
 * Created by yakun on 2016/8/23.
 */
public class DaggerPresenter {
    MainActivity mainActivity;
    User user;

    public DaggerPresenter(MainActivity mainActivity, User user) {
        this.mainActivity = mainActivity;
        this.user = user;
    }
    public void setUserName(){
        mainActivity.showUserName(user.name);
    }
}
