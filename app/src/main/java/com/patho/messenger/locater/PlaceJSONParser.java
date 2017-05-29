package com.patho.messenger.locater;

/**
 * Created by eren on 22.03.2017.
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceJSONParser {

        public List<HashMap<String,String>> parse(JSONObject jObject){

            JSONArray jPlaces = null;
            try {
                jPlaces = jObject.getJSONArray("results");
                Log.v("Rplace",jPlaces.toString());
                Log.v("Robje",jObject.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return getPlaces(jPlaces);
        }

        private List<HashMap<String, String>> getPlaces(JSONArray jPlaces){
            int placesCount = jPlaces.length();
            List<HashMap<String, String>> placesList = new ArrayList<HashMap<String,String>>();
            HashMap<String, String> place = null;

            for(int i=0; i<placesCount;i++){
                try {
                    place = getPlace((JSONObject)jPlaces.get(i));
                    placesList.add(place);
                    System.out.println(place.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return placesList;
        }

        private HashMap<String, String> getPlace(JSONObject jPlace){

            HashMap<String, String> place = new HashMap<String, String>();
            String placeName = "-NA-";
            String vicinity="-NA-";
            String latitude="";
            String longitude="";

            try {
                if(!jPlace.isNull("name")){
                    placeName = jPlace.getString("name");
                }
                if(!jPlace.isNull("vicinity")){
                    vicinity = jPlace.getString("vicinity");
                }
                latitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
                longitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");
                place.put("place_name", placeName);
                place.put("vicinity", vicinity);
                place.put("lat", latitude);
                place.put("lng", longitude);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return place;
        }
}

