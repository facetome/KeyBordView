package basic.keyboardviewa.view;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

import basic.keyboardviewa.R;


/**
 * Created by acer on 2015/9/1.
 */
public class TestDialog extends DialogFragment implements KeyBoardView.OnClickListener {
    private KeyBoardView mKeyBoardView;
    private KeyBoardView.OnClickListener mListener;

    public static TestDialog newInstance() {
        return new TestDialog();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.BaseDialog);
        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null, false);
        mKeyBoardView = (KeyBoardView) parent.findViewById(R.id.key);
        mKeyBoardView.setOnClickListener(this);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        dialog.setContentView(parent);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        return dialog;
    }

    public void setOnKeyClickListener(KeyBoardView.OnClickListener listener) {
        mListener = listener;
    }


    @Override
    public void onClick(int witch) {
        if (mListener != null) {
            mListener.onClick(witch);
        }
        dismiss();
    }
}
