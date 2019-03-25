package com.besplatka.test;

import android.app.ProgressDialog;
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
                mMainPresenter.onCreatePosterClick();
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
                mMainPresenter.onPosterClick(position);
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
}
