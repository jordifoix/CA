package com.chillasso.chillasso.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.chillasso.chillasso.Class.Group;
import com.chillasso.chillasso.R;

import java.util.List;

/**
 * Created by Enric on 06/04/2017.
 */

public class GroupSpinnerAdapter implements SpinnerAdapter {

    private List<Group> groups;
    private Context context;

    public GroupSpinnerAdapter(Context context,List<Group> groups) {
        this.groups = groups;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.groups_spinner_item,parent,false);
        TextView group_name_textView = (TextView) view.findViewById(R.id.group_name_textView);
        group_name_textView.setText(groups.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return groups.isEmpty();
    }
}
