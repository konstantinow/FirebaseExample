package com.besplatka.test;

import java.util.List;

public interface IPostersModel {
    void getPosters(OnGetPostersCallback onPostersCallback);
    void createPoster(Poster poster, OnUpdatePosterCallback onUpdatePosterCallback);
    void updatePoster(Poster poster, OnUpdatePosterCallback onUpdatePosterCallback);
    void removePoster(Poster poster, OnUpdatePosterCallback onUpdatePosterCallback);

    public interface OnGetPostersCallback extends IOnError{
        void onSuccess(List<Poster> posters);
    }

    public interface OnUpdatePosterCallback extends IOnError{
        void onSuccess();
    }

    public interface IOnError {
        void onFailure(String errorMessage);
    }
}
