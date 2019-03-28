package com.besplatka.test;

public class PosterPresenter implements IPosterPresenter{
    private IPosterView mPosterView;
    private PostersModel mPostersModel;

    public PosterPresenter(IPosterView iPosterView) {
        this.mPosterView = iPosterView;
        this.mPostersModel = PostersModel.getInstance();
    }

    @Override
    public void onCreate(Poster poster) {
        if (poster != null) {
            mPosterView.initPoster(poster);
            mPosterView.showUpdateButton();
        } else {
            mPosterView.showCreateButton();
        }
    }

    @Override
    public void onDestroy() {
        mPosterView.hideProgress();
        mPosterView = null;
    }

    @Override
    public void onUpdatePosterClick(Poster poster) {
        mPosterView.showProgress();
        mPostersModel.updatePoster(poster, new IPostersModel.OnUpdatePosterCallback() {
            @Override
            public void onSuccess() {
                mPosterView.hideProgress();
                mPosterView.showMessage("Success update poster");
                mPosterView.goBack();
            }

            @Override
            public void onFailure(String errorMessage) {
                mPosterView.hideProgress();
                mPosterView.showMessage(errorMessage);
            }
        });
    }

    @Override
    public void onCreatePosterClick(Poster poster) {
        mPosterView.showProgress();
        mPostersModel.createPoster(poster, new IPostersModel.OnUpdatePosterCallback() {
            @Override
            public void onSuccess() {
                mPosterView.hideProgress();
                mPosterView.showMessage("Success create poster");
                mPosterView.goBack();
            }

            @Override
            public void onFailure(String errorMessage) {
                mPosterView.hideProgress();
                mPosterView.showMessage(errorMessage);
            }
        });
    }
}
