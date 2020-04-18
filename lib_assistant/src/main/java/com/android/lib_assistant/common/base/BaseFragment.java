package com.android.lib_assistant.common.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by shehab on 18/04/20.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract void initializeViews(View v);

    protected abstract void setListeners();

    protected void replaceFragment(int containerId, Fragment fragment , String tag, String key , String value) {
        Bundle bundle=new Bundle();
        bundle.putString(key, value);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    protected void replaceFragment(int containerId, Fragment fragment , String tag, String key , Parcelable value) {
        Bundle bundle=new Bundle();
        bundle.putParcelable(key,value);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    protected void replaceFragment(int containerId, Fragment fragment , String tag) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}