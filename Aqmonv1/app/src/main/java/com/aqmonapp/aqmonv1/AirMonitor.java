package com.aqmonapp.aqmonv1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.FlakyTest;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnDpWidthModel;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.model.TableColumnWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class AirMonitor extends AppCompatActivity {
    private static final String[] TABLE_HEADERS = { "Parameters","Date","Time","Concentration","Unit","Concentration (previous 24 Hours)/Prescribed Standard","Remarks"};
    String[] list ={"Mathura Road","Sirifort, Delhi","Aya Nagar, Delhi","R K Puram, Delhi","IGI Airport, Delhi","IGI Airport Terminal-3, Delhi","NSIT Dwarka, Delhi","Income Tax Office, Delhi","Mandir Marg, Delhi","Pusa, Delhi","Shadipur, Delhi","Punjabi Bagh, Delhi","Anand Vihar, Delhi","East Arjun Nagar-Delhi","IHBAS, Delhi","Civil Lines, Delhi","DTU","North Campus, Delhi","Burari Crossing, Delhi"};
    ApiList ap;
    public AirMonitor()
    {
        ap=new ApiList();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_monitor);

        Spinner sp= (Spinner)findViewById(R.id.appCompatSpinner);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,list);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(),"a"+ap.getList(position),Toast.LENGTH_LONG).show();
                 FetchData ft = new FetchData();
                 ft.execute(ap.getList(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private  class FetchData extends AsyncTask<String  ,String ,String[][]>
    {
        TableView tableView;
        private ProgressDialog pg=new ProgressDialog(AirMonitor.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg.setTitle("Loding");
            pg.setMessage("loding data");
            pg.show();
            tableView = (TableView) findViewById(R.id.tableView);
            //tableView.setColumnCount(6);
            TableColumnDpWidthModel columnModel = new TableColumnDpWidthModel(getApplicationContext(),6,100);
            columnModel.setColumnWidth(0,150);
            columnModel.setColumnWidth(1,150);
            columnModel.setColumnWidth(2,100);
            columnModel.setColumnWidth(3,130);
            columnModel.setColumnWidth(4,110);
            columnModel.setColumnWidth(5,100);
            tableView.setColumnModel(columnModel);
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getApplicationContext(), TABLE_HEADERS));

        }

        @Override
        protected void onPostExecute(String dataS[][]) {
            super.onPostExecute(dataS);
            if(dataS[0][0]=="null") {
                Toast.makeText(getApplicationContext(), "connection timeout", Toast.LENGTH_LONG).show();
            }
            else {
                tableView.setDataAdapter(new SimpleTableDataAdapter(getApplicationContext(), dataS));
            }
            pg.dismiss();
        }

        @Override
        protected String[][] doInBackground(String... params) {
            String area = params[0];
            Log.d("ff",area);
            String  trtd [][];
            String a[][]=new String [15][15];
            int row_size=0,column_size=0;

            org.jsoup.nodes.Document doc = null;
            try {
                doc = Jsoup.connect(area).get();
                Log.d("d",doc.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(doc!=null){

                Elements tables = doc.select("td#Td1");

                for (Element table : tables) {
                    Elements trs = table.select("tr");
                    trtd = new String[trs.size()][];
                    row_size=trs.size();
                    for (int i = 0; i < row_size; i++) {
                        Elements tds = trs.get(i).select("td");
                        trtd[i] = new String[tds.size()];
                        column_size=tds.size();
                        for (int j = 0; j < column_size; j++) {
                            trtd[i][j] = tds.get(j).text();

                        }
                    }
                    for(int row = 0; row< row_size-1; row++)
                        for(int col = 0 ;col< column_size-1; col++) {
                            a[row][col]=trtd[row+1][col];
                        }
                    // trtd now contains the desired array for this table
                }

            }
            else
            {
                a[0][0]="null";
            }



            return a;

        }
    }
    }


