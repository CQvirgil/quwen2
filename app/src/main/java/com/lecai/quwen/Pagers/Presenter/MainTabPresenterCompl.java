package com.lecai.quwen.Pagers.Presenter;

import android.content.Intent;

public class MainTabPresenterCompl implements IMainTabPresenter {
    private IMainTabPresenterView iMainTabPresenterView;

    public MainTabPresenterCompl(IMainTabPresenterView iMainTabPresenterView) {
        this.iMainTabPresenterView = iMainTabPresenterView;
    }

    @Override
    public void NewsTabCheck() {
        iMainTabPresenterView.loadFragment(0);
    }

    @Override
    public void MineTabCheck() {
        iMainTabPresenterView.loadFragment(3);
        //iMainTabPresenterView.onStartLoginActivity();
    }

    @Override
    public void TaskTabCheck() {
        iMainTabPresenterView.loadFragment(2);
    }

}
