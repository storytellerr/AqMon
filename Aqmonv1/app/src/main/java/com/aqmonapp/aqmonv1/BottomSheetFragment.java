package com.aqmonapp.aqmonv1;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
    ProgressBar progrss ;
    TextView tv;
    ArcProgressStackView arcProgressStackView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        tv=(TextView)v.findViewById(R.id.name);
        Bundle mArgs = getArguments();
        String name = mArgs.getString("name");
        arcProgressStackView = (ArcProgressStackView) v.findViewById(R.id.apsv);
        progrss = (ProgressBar) v.findViewById(R.id.progressbar_bottomsheet);
        tv.setText(name);

        // String distance = mArgs.getString("distance");

        return v;
    }

    public  class datafetch extends AsyncTask<String,Void,Map<String,Integer>>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Map<String,Integer> map) {
            super.onPostExecute(map);

            final ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
            Set<String> key = map.keySet();
            Iterator<String> value = key.iterator();

            while (value.hasNext()) {
                String pollutant = value.next();
                int amount = map.get(pollutant);
                models.add(new ArcProgressStackView.Model(pollutant,amount, bgColors[1], mStartColors[0]));
                Log.d(pollutant, " => " + amount);
            }
            arcProgressStackView.setModels(models);
        }

        @Override
        protected Map<String,Integer> doInBackground(String... params) {
            try {
                URL url = new URL("");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();
                if (statusCode == 200) {
                    InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader read = new InputStreamReader(it);
                    BufferedReader buff = new BufferedReader(read);
                    StringBuilder data = new StringBuilder();
                    String chunks;
                    while ((chunks = buff.readLine()) != null) {
                        data.append(chunks);
                    }
                } else {
                    //Handle else
                }
            }catch (Exception e){
                
            }
            return null;
        }
    }

}
