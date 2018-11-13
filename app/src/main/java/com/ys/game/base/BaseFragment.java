package com.ys.game.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ys.game.util.L;


public abstract class BaseFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutResource(), container, false);
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        init();
        getData();
        return mView;
    }

    protected abstract void init();
    protected abstract void getData();
    protected abstract int getLayoutResource();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    protected final <E extends View> E getView(int id) {
        try {
            return (E) mView.findViewById(id);
        } catch (ClassCastException ex) {
            L.e(ex.toString());
            throw ex;
        }
    }

    protected void show(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }
}
