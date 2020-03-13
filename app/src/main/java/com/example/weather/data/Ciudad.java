package com.example.weather.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
//Esta clase es el modelado de el Json a una clase de Java
public class Ciudad {
    public static Gson g = new Gson();
    public String cod;
    public int message;
    public int cnt;
    //Esta lista contiene los pronosticos de los proximos días.
    public List<list> list;
    public city city;

    public static Ciudad getCiudad(JSONObject response) {
    Log.d("Response",response.toString());
        Ciudad ciudad = g.fromJson(response.toString(), Ciudad.class);
        Log.d("ciudad",response.toString());

        return ciudad;
    }
    public static class city {
        public int id;
        public String name;
        public coord coord;
        public String country;
        public int timezone;
        public int sunrise;
        public int sunset;

        public city() {
        }

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
    public static class coord {
        public double lat;
        public double lon;



        public coord() {
        }

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
    public static class list {
        public int dt;
        public String name;
        public main main;
        public List<weather> weather;
        public clouds clouds;
        public wind wind;
        public sys sys;
        public String dt_txt;

        public list() {
        }

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
    public static class main {
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int preassure;
        public int sea_level;
        public int grdn_level;
        public int humidity;
        public double temp_kf;



        public main() {
        }

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
    public static class weather {
        public int id;
        public String main;
        public String description;
        public String icon;




        public weather() {
        }

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
    public static class clouds {
        public int all;



        public clouds() {
        }

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
    public static class wind {
        public double speed;
        public int deg;



        public wind() {
        }

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
    public static class sys {
        public String pod;



        public sys() {
        }

        @NonNull
        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
}
