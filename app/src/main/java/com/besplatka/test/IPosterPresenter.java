package com.besplatka.test;

public interface IPosterPresenter {
    void onCreate(Poster poster);
    void onDestroy();
    void onUpdatePosterClick(Poster poster);
    void onCreatePosterClick(Poster poster);
}
