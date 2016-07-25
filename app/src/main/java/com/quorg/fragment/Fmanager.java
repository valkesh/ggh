/**
 * @author saltinteractive
 */
package com.quorg.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.quorg.R;


public class Fmanager extends Fragment {

    public void replaceFragment(Fragment fragment, boolean addToBackStack,
                                String tag) {

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.replace(R.id.FlContainer_framelayout, fragment, tag);
        transaction.commit();
        getChildFragmentManager().executePendingTransactions();
    }

    public boolean popFragment() {
        boolean isPop = false;
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            getChildFragmentManager().popBackStack();
        }
        return isPop;
    }

}
