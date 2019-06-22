package com.lecai.quwen.Pagers.Presenter;

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
        iMainTabPresenterView.loadFragment(1);
    }

    @Override
    public void TaskTabCheck() {
        iMainTabPresenterView.loadFragment(2);
    }

}
