package com.besplatka.test;

import java.util.List;

public class MainPresenter implements IMainPresenter{
    private IMainView mMainView;
    private PostersModel mPostersModel;

    public MainPresenter(IMainView iMainView) {
        this.mMainView = iMainView;
        this.mPostersModel = PostersModel.getInstance();
    }

    @Override
    public void onCreate() {
        mMainView.showProgress();
        mPostersModel.getPosters(new IPostersModel.OnGetPostersCallback() {
            @Override
            public void onSuccess(List<Poster> posters) {
                mMainView.updatePosters(posters);
                mMainView.hideProgress();
            }

            @Override
            public void onFailure(String errorMessage) {
                mMainView.hideProgress();
                mMainView.showMessage(errorMessage);
            }
        });
    }

    @Override
    public void onDestroy() {
        mMainView.hideProgress();
        mMainView = null;
    }

    @Override
    public void onPosterClick(int position) {
        mMainView.showMessage("onPosterClick");
    }

    @Override
    public void onCreatePosterClick() {
        mMainView.showMessage("onCreatePosterClick");
    }
}
