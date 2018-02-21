package com.aqmonapp.aqmonv1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by storytellerr on 20/2/18.
 */

public class ApiList {


    private List<String> list;

    public  ApiList() {
        list = new ArrayList<String>();
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=CRRI5Mathura5Road5IMD&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Sirifort&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Aya5Nagar5IMD&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=R5K5Puram&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=IGI5Airport&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=IGI5Airport5Terminal-35IMD&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Dwarka&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=ITO&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Mandir5Marg&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Pusa5IMD&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Shadipur&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Punjabi5Bagh&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Anand5Vihar&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=East5Arjun5Nagar-Delhi5CPCB&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Ihbas&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Civil5Lines&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=DTU&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=North5Campus5IMD&StateId=6&CityId=85");
        list.add("http://www.cpcb.gov.in/CAAQM/frmCurrentDataNew.aspx?StationName=Burari5Crossing5IMD&StateId=6&CityId=85");
    }

    public String getList( int pos) {
        return list.get(pos);
    }
}
