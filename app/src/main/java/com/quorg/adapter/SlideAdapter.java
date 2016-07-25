/**
 * @author Valkesh patel
 */
package com.quorg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quorg.R;
import com.quorg.model.SideMenuModel;

import java.util.List;

/**
 * An array adapter that knows how to render views when given CustomData classes
 */
public class SlideAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    Context mContext;
    Holder holder;

    int mSelectedItem = -1;
    private int lastPosition = 0;
    public List<SideMenuModel> mList;
    SideMenuModel val;
    private OnMNuSelectedListener mdListener;

    public interface OnMNuSelectedListener {
        void onMNuSelectedListener(int index);
    }

    public SlideAdapter(Context context, OnMNuSelectedListener mdListener,
                        List<SideMenuModel> mList) {

        mContext = context;
        this.mList = mList;
        this.mdListener = mdListener;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // Inflate the view since it does not exist
            convertView = mInflater.inflate(R.layout.drawer_layout, parent,
                    false);

            // Create and save off the holder in the tag so we get quick access
            // to inner fields
            // This must be done for performance reasons
            holder = new Holder();
            holder.imageViewCImage = (ImageButton) convertView
                    .findViewById(R.id.imgFlag);

            holder.textViewTitle = (TextView) convertView
                    .findViewById(R.id.txtCountry);
            holder.main_layer = (LinearLayout) convertView
                    .findViewById(R.id.LlMain_layer);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        // Populate the text

        val = mList.get(position);
        holder.textViewTitle.setText(val.getTitle());

        holder.imageViewCImage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lastPosition = position;
                mdListener.onMNuSelectedListener(position);

            }
        });
        holder.main_layer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lastPosition = position;
                mdListener.onMNuSelectedListener(position);
//                DashBoard.menu.toggle(false);
            }
        });

        if (lastPosition == position) {
            holder.imageViewCImage.setImageResource(mList.get(position)
                    .getImageidClicked());

        } else {
            holder.imageViewCImage.setImageResource(mList.get(position)
                    .getImageid());
//            holder.main_layer.setBackgroundResource(R.drawable.unselected_item_list);
        }

        if (mList.get(position).isClicked()) {
           // holder.main_layer.setBackgroundColor(Color.parseColor("#9DA6A5"));
        } else {
           // holder.main_layer.setBackgroundColor(Color.parseColor("#99FFFF"));
        }

//         Animation animationY = new TranslateAnimation(0, 0,
//         holder.main_layer.getHeight() / 4, 0);
//         animationY.setDuration(1000);
//         convertView.startAnimation(animationY);
//         animationY = null;

        return convertView;
    }

    /**
     * View holder for the views we need access to
     */
    private static class Holder {
        public TextView textViewTitle;
        public ImageButton imageViewCImage;
        public LinearLayout main_layer;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
