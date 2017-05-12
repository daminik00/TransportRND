package com.example;

public class Bus extends Transports {
	public Bus(String number, String route, int speed, int incline, float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
        this.route = route;
        this.number = number;
        this.speed = speed;
        this.incline = incline;
    }

    public Bus(String number, String route, int speed, int incline, double lat, double lng) {
        this.lat = (float) lat;
        this.lng = (float) lng;
        this.route = route;
        this.number = number;
        this.speed = speed;
        this.incline = incline;
    }

    public Bus(String number, String route, int speed, int incline, long lat, long lng) {
        this.lat = (float) lat;
        this.lng = (float) lng;
        this.route = route;
        this.number = number;
        this.speed = speed;
        this.incline = incline;
    }

    public Bus(String number, String route, int speed, int incline, String lat, String lng) {
        this.lat = Float.parseFloat(lat);
        this.lng = Float.parseFloat(lng);
        this.route = route;
        this.number = number;
        this.speed = speed;
        this.incline = incline;
    }
}
