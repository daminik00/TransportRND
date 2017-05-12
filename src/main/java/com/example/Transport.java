package com.example;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Transport {
	URL url;
	
	public ArrayList<String> transport = new ArrayList<String>();
    public ArrayList<LinkedList> bus = new ArrayList<LinkedList>();
    public ArrayList<LinkedList> minibus = new ArrayList<LinkedList>();
    public ArrayList<LinkedList> tram = new ArrayList<LinkedList>();
    public ArrayList<LinkedList> trolleybus = new ArrayList<LinkedList>();
    
    public ArrayList<Bus> Bus = new ArrayList<Bus>();
    public ArrayList<Minibus> Minibus = new ArrayList<Minibus>();
    public ArrayList<Tram> Tram = new ArrayList<Tram>();
    public ArrayList<Trolleybus> Trolleybus = new ArrayList<Trolleybus>();
    
    public String leftBut;
    public String center;
    
    public double rastCenter; 
    
    public String AllString;
    public Map<String, ArrayList> mapJSON;
	
	public Transport() {
		try {
			this.url = new URL("https://www.its-rnd.ru/pikasonline/p04ktwt0.txt");
			this.getTransport();
			this.setClasses();
			this.setJSON();
			
			this.checkSq(47.21621556520743,39.719747342169285);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Transport(String leftBut, String center) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			this.leftBut = leftBut;
			this.center = center;
			double x1 = Double.valueOf(this.leftBut.split("::")[0]);
			double y1 = Double.valueOf(this.leftBut.split("::")[1]);
			double x2 = Double.valueOf(this.center.split("::")[0]);
			double y2 = Double.valueOf(this.center.split("::")[1]);
			this.rastCenter = this.reatLatLng(x1, y1, x2, y2);
			this.url = new URL("https://www.its-rnd.ru/pikasonline/p04ktwt0.txt");
			this.getTransport();
			this.setClasses();
			this.setJSON();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkSq(double x0, double y0) {
		
		/**
		 *
		 * ?leftBut=47.21222056072565::39.709876477718346&center=47.22253107556481::39.71870496869087
		 *
		 */
		
		double x2 = Double.valueOf(this.center.split("::")[0]);
		double y2 = Double.valueOf(this.center.split("::")[1]);
		
		if (this.reatLatLng(x0, y0, x2, y2) < this.rastCenter) {
			return true;
		}
		return false;
	}
	
	public double reatLatLng(double lon, double lat, double lon2, double lat2) {  
		return 111.2 * Math.sqrt( (lon - lon2)*(lon - lon2) + (lat - lat2)*Math.cos(Math.PI*lon/180)*(lat - lat2)*Math.cos(Math.PI*lon/180));
	}
	
	public boolean getTransport() {
        try {
            BufferedReader list = new BufferedReader(new InputStreamReader(this.url.openStream()));
            String strTemp = "";
            while (null != (strTemp = list.readLine())) {
                this.transport.add(strTemp);
                String[] arrayTr = strTemp.split(",");
                LinkedList<String> forAdd = new LinkedList<String>();
                forAdd.add(arrayTr[1]);
                String lng = this.toFloatString(arrayTr[2]);
                String lat = this.toFloatString(arrayTr[3]);
                forAdd.add(lat);
                forAdd.add(lng);
                if (arrayTr[4].equals("") || arrayTr[4].equals(" ")) {
                    forAdd.add("-1");
                } else {
                    forAdd.add(arrayTr[4]);
                }

                if (arrayTr[5].equals("") || arrayTr[4].equals(" ")) {
                    forAdd.add("-1");
                } else {
                    forAdd.add(arrayTr[5]);
                }

                forAdd.add(arrayTr[6]);
                switch (arrayTr[0]) {
                    case "3" :
                        this.tram.add(forAdd);
                        break;
                    case "1" :
                        this.trolleybus.add(forAdd);
                        break;
                    case "2" :
                        this.bus.add(forAdd);
                        break;
                    case "4" :
                        this.minibus.add(forAdd);
                        break;
                    default: break;
                }

            }
           
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
	
	public void setClasses() {
        ArrayList<Bus> bus = new ArrayList<Bus>();
        ArrayList<Minibus> minibus = new ArrayList<Minibus>();
        ArrayList<Tram> tram = new ArrayList<Tram>();
        ArrayList<Trolleybus> trolleybus = new ArrayList<Trolleybus>();

        int size = this.bus.size();
        for (int i = 0; i < size; i++) {
            LinkedList busTime = this.bus.get(i);

            String number = (String) busTime.get(5);
            String lat = (String) busTime.get(1);
            String lng = (String) busTime.get(2);
            String route = (String) busTime.get(0);

            int incline = Integer.parseInt((String) busTime.get(4));
            int speed = Integer.parseInt((String) busTime.get(3));

            Bus BusTime = new Bus(number, route, speed, incline, lat, lng);

            bus.add(BusTime);
        }
        size = this.minibus.size();
        for (int i = 0; i < size; i++) {
            LinkedList minbusTime = this.minibus.get(i);

            String number = (String) minbusTime.get(5);
            String lat = (String) minbusTime.get(1);
            String lng = (String) minbusTime.get(2);
            String route = (String) minbusTime.get(0);

            int incline = Integer.parseInt((String) minbusTime.get(4));
            int speed = Integer.parseInt((String) minbusTime.get(3));

            Minibus MinbusTime = new Minibus(number, route, speed, incline, lat, lng);
            minibus.add(MinbusTime);
        }

        size = this.tram.size();
        for (int i = 0; i < size; i++) {
            LinkedList tramTime = this.tram.get(i);

            String number = (String) tramTime.get(5);
            String lat = (String) tramTime.get(1);
            String lng = (String) tramTime.get(2);
            String route = (String) tramTime.get(0);

            int incline = Integer.parseInt((String) tramTime.get(4));
            int speed = Integer.parseInt((String) tramTime.get(3));

            Tram TramTime = new Tram(number, route, speed, incline, lat, lng);
            tram.add(TramTime);
        }

        size = this.trolleybus.size();
        for (int i = 0; i < size; i++) {
            LinkedList trolTime = this.trolleybus.get(i);

            String number = (String) trolTime.get(5);
            String lat = (String) trolTime.get(1);
            String lng = (String) trolTime.get(2);
            String route = (String) trolTime.get(0);

            int incline = Integer.parseInt((String) trolTime.get(4));
            int speed = Integer.parseInt((String) trolTime.get(3));

            Trolleybus TrolTime = new Trolleybus(number, route, speed, incline, lat, lng);
            trolleybus.add(TrolTime);
        }

        this.Bus = bus;
        this.Minibus = minibus;
        this.Tram = tram;
        this.Trolleybus = trolleybus;
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
	
	public String setJSON() {
		try {
			Map<String, ArrayList> trans = new HashMap<String, ArrayList>();
			ArrayList<String> al = new ArrayList<>();
			
			ObjectMapper mapper = new ObjectMapper();
			
			al = new ArrayList<>();
			for (int i = 0; i < this.Bus.size(); i++) {
				double x0 = Double.valueOf(mapper.writeValueAsString(this.Bus.get(i)).split("lat\":")[1].split(",\"lng\"")[0]);
				double y0 = Double.valueOf(mapper.writeValueAsString(this.Bus.get(i)).split("\"lng\":")[1].split("}")[0]);
				if (this.checkSq(x0, y0)) {
					al.add(mapper.writeValueAsString(this.Bus.get(i)));
				}
			}
			trans.put("\"Bus\"", al);
			al = new ArrayList<>();
			for (int i = 0; i < this.Minibus.size(); i++) {
				double x0 = Double.valueOf(mapper.writeValueAsString(this.Minibus.get(i)).split("lat\":")[1].split(",\"lng\"")[0]);
				double y0 = Double.valueOf(mapper.writeValueAsString(this.Minibus.get(i)).split("\"lng\":")[1].split("}")[0]);
				if (this.checkSq(x0, y0)) {
					al.add(mapper.writeValueAsString(this.Minibus.get(i)));
				}
			}
			trans.put("\"Minibus\"", al);
			al = new ArrayList<>();
			for (int i = 0; i < this.Tram.size(); i++) {
				double x0 = Double.valueOf(mapper.writeValueAsString(this.Tram.get(i)).split("lat\":")[1].split(",\"lng\"")[0]);
				double y0 = Double.valueOf(mapper.writeValueAsString(this.Tram.get(i)).split("\"lng\":")[1].split("}")[0]);
				if (this.checkSq(x0, y0)) {
					al.add(mapper.writeValueAsString(this.Tram.get(i)));
				}
			}
			trans.put("\"Tram\"", al);
			al = new ArrayList<>();
			for (int i = 0; i < this.Trolleybus.size(); i++) {
				double x0 = Double.valueOf(mapper.writeValueAsString(this.Trolleybus.get(i)).split("lat\":")[1].split(",\"lng\"")[0]);
				double y0 = Double.valueOf(mapper.writeValueAsString(this.Trolleybus.get(i)).split("\"lng\":")[1].split("}")[0]);
				if (this.checkSq(x0, y0)) {
					al.add(mapper.writeValueAsString(this.Trolleybus.get(i)));
				}
			}
			trans.put("\"Trolleybus\"", al);
			this.mapJSON = trans;
			this.AllString = trans.toString().replaceAll("=", ":");
			return this.AllString;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
}