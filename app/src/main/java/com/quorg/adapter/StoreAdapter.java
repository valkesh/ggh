package com.quorg.adapter;

/**
 * Created by Valkesh patel on 19/7/16.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.quorg.R;
import com.quorg.model.NavDrawerItem;
import com.quorg.widgets.GGTextView;

import java.util.Collections;
import java.util.List;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private static MyClickListener myClickListener;
    public StoreAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        System.out.println(data.size());
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        System.out.println(current.getTitle());
        holder.title.setTextColor(context.getResources().getColor(R.color.white));

        if (position == 1) {
            holder.llNotification.setVisibility(View.VISIBLE);
            holder.tvNotification.setText("" + current.count);
        } else
            holder.llNotification.setVisibility(View.GONE);

        // Select navigation item
        if (current.isShowNotify()) {

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        GGTextView title,tvNotification;
        LinearLayout llNotification;
        View viewSelected;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (GGTextView) itemView.findViewById(R.id.title);
            tvNotification = (GGTextView) itemView.findViewById(R.id.tvNotification);
            llNotification = (LinearLayout) itemView.findViewById(R.id.llNotification);
            viewSelected = (View) itemView.findViewById(R.id.viewSelected);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
