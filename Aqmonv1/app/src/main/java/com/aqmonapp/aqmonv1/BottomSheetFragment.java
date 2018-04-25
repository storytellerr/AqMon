package com.aqmonapp.aqmonv1;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import devlight.io.library.ArcProgressStackView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {


    public BottomSheetFragment() {
        // Required empty public constructor
    }

    int bgColors[]={0xffffff00,0xff0000ff,0xffffff00,0xff0000ff};
    int mStartColors[]={0xffff00ff};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
       TextView tv=(TextView)v.findViewById(R.id.name);
        Bundle mArgs = getArguments();
        String name = mArgs.getString("name");
       // String distance = mArgs.getString("distance");
        tv.setText(name);
        final ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
        models.add(new ArcProgressStackView.Model("Circle", 25, bgColors[0], mStartColors[0]));
        models.add(new ArcProgressStackView.Model("Progress", 50, bgColors[1], mStartColors[0]));
        models.add(new ArcProgressStackView.Model("Stack", 75, bgColors[2], mStartColors[0]));
        models.add(new ArcProgressStackView.Model("View", 100, bgColors[3], mStartColors[0]));

        final ArcProgressStackView arcProgressStackView = (ArcProgressStackView) v.findViewById(R.id.apsv);
        arcProgressStackView.setModels(models);
        return v;
    }

}
