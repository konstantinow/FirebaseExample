package com.besplatka.test;

public interface IMainPresenter {
    void onCreate();
    void onDestroy();
    void onPosterClick(Poster poster);
    void onDialogPosterEditClick(Poster poster);
    void onPosterCreateClick();
    void onDialogPosterRemoveClick(Poster poster);
    void onDialogPosterCancelClick();
}
