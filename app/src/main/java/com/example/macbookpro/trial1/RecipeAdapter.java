package com.example.macbookpro.trial1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by macbookpro on 11/28/17.
 */

public class RecipeAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Recipe> mDataSource;

    public RecipeAdapter(Context context, ArrayList<Recipe> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_recipe, parent, false);
        // Get subtitle element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.publisher);

// Get detail element
        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.title);

// Get thumbnail element
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(R.id.thumbnail);

        // 1
        Recipe recipe = (Recipe) getItem(position);

// 2
        subtitleTextView.setText(recipe.publisher);
        detailTextView.setText(recipe.title);

// 3
        Glide.with(mContext).load(recipe.imagelink).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);
        return rowView;
    }

}
