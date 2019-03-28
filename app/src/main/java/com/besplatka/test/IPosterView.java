package com.besplatka.test;

import java.util.List;

public interface IPosterView {
    void showProgress();
    void hideProgress();
    void initPoster(Poster poster);
    void showMessage(String message);
    void goBack();
    void showCreateButton();
    void showUpdateButton();
}
