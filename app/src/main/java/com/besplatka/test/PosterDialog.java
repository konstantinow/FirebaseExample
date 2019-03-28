package com.besplatka.test;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class PosterDialog {
    private Context mContext;
    private Dialog mDialog;
    private Button mBtnEdit;
    private Button mBtnRemove;
    private OnDialogButtonsClick mOnDialogButtonsClick;

    private Poster mSelectedPoster;

    public PosterDialog(Context context) {
        this.mContext = context;
        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_poster);

        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mOnDialogButtonsClick == null) {
                    return;
                }
                mOnDialogButtonsClick.onCancelClick();
            }
        });

        mBtnEdit = mDialog.findViewById(R.id.btn_edit);
        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDialogButtonsClick == null) {
                    return;
                }
                mOnDialogButtonsClick.onEditClick(mSelectedPoster);
            }
        });
        mBtnRemove = mDialog.findViewById(R.id.btn_remove);
        mBtnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDialogButtonsClick == null) {
                    return;
                }
                mOnDialogButtonsClick.onRemoveClick(mSelectedPoster);
            }
        });

        Window window = mDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    public void showDialog(Poster poster) {
        this.mSelectedPoster = poster;
        mDialog.show();
    }

    public void hideDialog() {
        mDialog.dismiss();
    }

    public void setOnDialogButtonsClick(OnDialogButtonsClick onDialogButtonsClick) {
        this.mOnDialogButtonsClick = onDialogButtonsClick;
    }

    public interface OnDialogButtonsClick {
        void onEditClick(Poster poster);

        void onRemoveClick(Poster poster);

        void onCancelClick();
    }
}
