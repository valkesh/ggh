/**
 * @author valkesh patel
 */
package com.quorg.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quorg.R;


public class HomeFragment extends Fmanager {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
//        recyclerView = (RecyclerView)v.findViewById(R.id.rv_store);


        return v;
    }


}
