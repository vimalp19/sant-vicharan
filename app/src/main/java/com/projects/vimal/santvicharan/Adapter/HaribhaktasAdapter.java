package com.projects.vimal.santvicharan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projects.vimal.santvicharan.R;
import com.projects.vimal.santvicharan.data.ProfileInfo;

import java.util.List;

/**
 * Created by vimal on 9/16/17.
 *
 * Adapter to return the list of haribhaktas available for viewing
 */

public class HaribhaktasAdapter extends ArrayAdapter<ProfileInfo> {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    public HaribhaktasAdapter(Context context, int resource, List<ProfileInfo> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity)getContext()).getLayoutInflater()
                    .inflate(R.layout.haribhakta_profile_info, parent, false);
        }

        TextView nameView = convertView.findViewById(R.id.name);
        TextView centerView = convertView.findViewById(R.id.center);

        ProfileInfo profileInfo = getItem(position);

        Log.i(CLASS_NAME, "Found first name: " + profileInfo.getFirstName());
        Log.i(CLASS_NAME, "Found last name: " + profileInfo.getLastName());
        Log.i(CLASS_NAME, "Found center: " + profileInfo.getCenter());

        nameView.setText(profileInfo.getFirstName() + " " + profileInfo.getLastName());
        centerView.setText(profileInfo.getCenter());

        return convertView;
    }
}
