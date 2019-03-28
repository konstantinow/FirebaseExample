package com.besplatka.test;

import java.util.List;

public interface IMainView {
    void showProgress();
    void hideProgress();
    void updatePosters(List<Poster> posters);
    void showMessage(String message);
    void showDialog(Poster poster);
    void hideDialog();
    void openEditPost(Poster poster);
}
