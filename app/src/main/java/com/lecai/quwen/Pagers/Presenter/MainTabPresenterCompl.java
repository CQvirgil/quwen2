package com.lecai.quwen.Pagers.Presenter;

import com.lecai.quwen.Pagers.Model.NetWork.Data.Data;

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
//        if (Data.getInstance().getU_unionid() != null) {
//            iMainTabPresenterView.loadFragment(1);
//        } else {
//            iMainTabPresenterView.onStartLoginActivity();
//        }
    }

    @Override
    public void TaskTabCheck() {
        iMainTabPresenterView.loadFragment(2);
//        if (Data.getInstance().getU_unionid() != null) {
//            iMainTabPresenterView.loadFragment(2);
//        } else {
//            iMainTabPresenterView.onStartLoginActivity();
//        }
    }


}