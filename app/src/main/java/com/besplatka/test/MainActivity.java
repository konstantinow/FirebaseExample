package com.besplatka.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private IMainPresenter mMainPresenter = new MainPresenter(this);
    private PosterAdapter mPostersAdapter;
    private ProgressDialog mProgressDialog;
    private PosterDialog mPosterDialog;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        mMainPresenter.onCreate();

        mFloatingActionButton = findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.onPosterCreateClick();
            }
        });
        mPosterDialog = new PosterDialog(this);
        mPosterDialog.setOnDialogButtonsClick(new PosterDialog.OnDialogButtonsClick() {
            @Override
            public void onEditClick(Poster poster) {
                mMainPresenter.onDialogPosterEditClick(poster);
            }

            @Override
            public void onRemoveClick(Poster poster) {
                mMainPresenter.onDialogPosterRemoveClick(poster);
            }

            @Override
            public void onCancelClick() {
                mMainPresenter.onDialogPosterCancelClick();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDestroy();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_posters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPostersAdapter = new PosterAdapter(this, null);
        mPostersAdapter.setClickListener(new PosterAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mMainPresenter.onPosterClick(mPostersAdapter.getItem(position));
            }
        });
        recyclerView.setAdapter(mPostersAdapter);
    }

    @Override
    public void showProgress() {
        mProgressDialog = ProgressDialog.show(
                this,
                getString(R.string.progress_dialog_loading),
                getString(R.string.progress_dialog_wait)
        );
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog == null) {
            return;
        }
        mProgressDialog.dismiss();
        mProgressDialog = null;
    }

    @Override
    public void updatePosters(List<Poster> posters) {
        mPostersAdapter.updateData(posters);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(Poster poster) {
        mPosterDialog.showDialog(poster);
    }

    @Override
    public void hideDialog() {
        mPosterDialog.hideDialog();
    }

    @Override
    public void openEditPost(Poster poster) {
        Intent intent = new Intent(this, PosterActivity.class);
        if (poster != null) {
            intent.putExtra(PosterActivity.KEY_POSTER, poster);
        }
        startActivity(intent);
    }
}
