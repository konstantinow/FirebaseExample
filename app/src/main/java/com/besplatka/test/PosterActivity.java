package com.besplatka.test;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PosterActivity extends AppCompatActivity implements IPosterView {

    public static String KEY_POSTER = "key_poster";

    private IPosterPresenter mPosterPresenter = new PosterPresenter(this);

    private ProgressDialog mProgressDialog;
    private EditText mEtTitle;
    private EditText mEtDescription;
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtCost;
    private Button mBtnComplete;
    private EditText mEtCity;

    private Poster mPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        mEtTitle = findViewById(R.id.et_title);
        mEtDescription = findViewById(R.id.et_description);
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_phone);
        mEtCity = findViewById(R.id.et_city);
        mEtCost = findViewById(R.id.et_cost);
        mBtnComplete = findViewById(R.id.btn_complete);

        mBtnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mPoster != null ? mPoster.getId() : null;
                Poster poster = new Poster(
                        id,
                        mEtTitle.getText().toString(),
                        mEtDescription.getText().toString(),
                        mEtName.getText().toString(),
                        mEtPhone.getText().toString(),
                        mEtCity.getText().toString(),
                        Integer.parseInt(mEtCost.getText().toString())
                );
                if (mBtnComplete.getText().toString().equals(getString(R.string.update_poster_button))) {
                    mPosterPresenter.onUpdatePosterClick(poster);
                } else {
                    mPosterPresenter.onCreatePosterClick(poster);
                }

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(KEY_POSTER)) {
            mPosterPresenter.onCreate((Poster) extras.get(KEY_POSTER));
        } else {
            mPosterPresenter.onCreate(null);
        }
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
    public void initPoster(Poster poster) {
        mPoster = poster;

        mEtTitle.setText(poster.getTitle());
        mEtDescription.setText(poster.getDescription());
        mEtName.setText(poster.getName());
        mEtPhone.setText(poster.getPhone());
        mEtCity.setText(poster.getCity());
        mEtCost.setText(String.valueOf(poster.getCost()));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goBack() {
        finish();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showCreateButton() {
        mBtnComplete.setText("Create");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showUpdateButton() {
        mBtnComplete.setText("Update");
    }
}
