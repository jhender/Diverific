package com.diverific.app;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Dive")
public class Dive extends ParseObject {

    public ParseFile getPhoto() {

        return getParseFile("DivePhoto");
    }


    public String getDiveName() {
        return getString("DiveName");
    }

    public String getDivePrice() { return getString("DivePrice"); }

    public ParseGeoPoint getLocation() {
        return getParseGeoPoint("DiveGeoPoint");
    }

    public static ParseQuery<Dive> getQuery() {
        return ParseQuery.getQuery(Dive.class);
    }

//    public String getDescription() {
//        return getString("description");
//    }

//    public void setDescription(String value) {
//        put("description", value);
//    }

//    public void setStatus(String value) {
//        put("status", value);
//    }
}