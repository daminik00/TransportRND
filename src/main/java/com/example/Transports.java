package com.example;
public class Transports {

    public String number; // vehicle registration number
    public String route; // routing number
    public int speed; // speed
    public int incline; // incline
    public float lat; // Latitude
    public float lng; // Longitude

    public Transports() {

    }

    public Transports(String number, String route, int speed, int incline, float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
        this.route = route;
        this.number = number;
        this.speed = speed;
        this.incline = incline;
    }

    public Transports(String number, String route, int speed, int incline, double lat, double lng) {
        this.lat = (float) lat;
        this.lng = (float) lng;
        this.route = route;
        this.number = number;
        this.speed = speed;
        this.incline = incline;
    }

    public Transports(String number, String route, int speed, int incline, long lat, long lng) {
        this.lat = (float) lat;
        this.lng = (float) lng;
        this.route = route;
        this.number = number;
        this.speed = speed;
        this.incline = incline;
    }

    public Transports(String number, String route, int speed, int incline, String lat, String lng) {
        this.lat = Float.parseFloat(lat);
        this.lng = Float.parseFloat(lng);
        this.route = route;
        this.number = number;
        this.speed = speed;
        this.incline = incline;
    }


    public float toFloat(String str) {
        char[] arrayChar = str.toCharArray();
        int length = arrayChar.length;
        String stringFl = "";

        for (int i = 0; i < length; i++) {
            if (i == 2) {
                stringFl += ".";
            }
            stringFl += Character.toString(arrayChar[i]);
        }

        return Float.parseFloat(stringFl);
    }

    public String toFloatString(String str) {
        char[] arrayChar = str.toCharArray();
        int length = arrayChar.length;
        String stringFl = "";

        for (int i = 0; i < length; i++) {
            if (i == 2) {
                stringFl += ".";
            }
            stringFl += Character.toString(arrayChar[i]);
        }

        return stringFl;
    }
}