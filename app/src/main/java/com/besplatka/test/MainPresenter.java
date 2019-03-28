package com.besplatka.test;

import java.util.List;

public class MainPresenter implements IMainPresenter {
    private IMainView mMainView;
    private PostersModel mPostersModel;

    public MainPresenter(IMainView iMainView) {
        this.mMainView = iMainView;
        //TODO: DI не прикручивал
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
        mMainView.hideDialog();
        mMainView = null;
    }

    @Override
    public void onPosterClick(Poster poster) {
        mMainView.showDialog(poster);
    }

    @Override
    public void onDialogPosterEditClick(Poster poster) {
        mMainView.hideDialog();
        mMainView.openEditPost(poster);
    }

    @Override
    public void onPosterCreateClick() {
        mMainView.openEditPost(null);
    }

    @Override
    public void onDialogPosterRemoveClick(Poster poster) {
        mMainView.showProgress();
        mPostersModel.removePoster(poster, new IPostersModel.OnUpdatePosterCallback() {
            @Override
            public void onSuccess() {
                mMainView.hideProgress();
                mMainView.hideDialog();
            }

            @Override
            public void onFailure(String errorMessage) {
                mMainView.hideProgress();
                mMainView.hideDialog();
                mMainView.showMessage(errorMessage);
            }
        });
    }

    @Override
    public void onDialogPosterCancelClick() {
        mMainView.hideDialog();
    }
}
