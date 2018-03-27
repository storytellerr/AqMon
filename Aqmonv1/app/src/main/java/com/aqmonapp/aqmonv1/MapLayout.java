package com.aqmonapp.aqmonv1;


        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.util.EntityUtils;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;


public class MapLayout extends AppCompatActivity  implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_map_layout);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        new fetch().execute();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.

        LatLng r = new LatLng(28.7041, 77.1025);
        googleMap.addMarker(new MarkerOptions().position(r)
                .title("Marker "));
        // googleMap.moveCamera(CameraUpdateFactory.newLatLng(r));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(r, 10));
    }

    class fetch extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            List<String> l = new ArrayList<>();
            //ObjectMapper objectMapper = new ObjectMapper();

            try {


                HttpGet httppost = new HttpGet("https://api.data.gov.in/resource/3b01bcb8-0b14-4abf-b6f2-c1bfd384ba69?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&offset=45");
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    try {
                        JSONObject jsono = new JSONObject(data);
                        List<String> listdata = new ArrayList<>();
                        JSONArray jArray = jsono.getJSONArray("records");
                        if (jArray != null) {
                            for (int i = 0; i < jArray.length(); i++) {
                                listdata.add(jArray.getString(i));
                                JSONObject j = jArray.getJSONObject(i);
                                Log.d("dd", j.getString("pollutant_max"));
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            } catch (IOException e) {
                e.printStackTrace();


                return null;
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

}


