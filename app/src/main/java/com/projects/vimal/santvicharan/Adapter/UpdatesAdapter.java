package com.projects.vimal.santvicharan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projects.vimal.santvicharan.R;
import com.projects.vimal.santvicharan.data.Update;

import java.util.List;

/**
 * Created by vimal on 9/23/17.
 */

public class UpdatesAdapter extends ArrayAdapter<Update> {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    public UpdatesAdapter(Context context, int resource, List<Update> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity)getContext()).getLayoutInflater()
                    .inflate(R.layout.haribhakta_upate, parent, false);
        }

        TextView dateView = convertView.findViewById(R.id.updateDate);
        TextView noteView = convertView.findViewById(R.id.updateNote);

        Update update = getItem(position);

        Log.i(CLASS_NAME, "Found Date: " + update.getDate());
        Log.i(CLASS_NAME, "Found note: " + update.getNote());
        Log.i(CLASS_NAME, "Found note id: " + update.getId());

        dateView.setText(update.getDate().toString());
        noteView.setText(update.getNote());

        return convertView;
    }
}
