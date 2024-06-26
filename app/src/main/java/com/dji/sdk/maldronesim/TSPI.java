package com.dji.sdk.maldronesim;

public class TSPI {

    //To save log
    StringBuffer loggedTSPI;
    private String header;
    private String timestamp;
    private String gpsSignalStrength;

    private int satelliteCount;
    private double currentLatitude;
    private double currentLongitude;
    private double currentAltitude;
    private double currentAltitude_seaTohome;

    TSPI(){
        this.loggedTSPI = new StringBuffer();
        this.header = "Date,GPSSignalStrength,SatteliteCount,Altitude,Latitude,Longitude\n";
        this.loggedTSPI.append(header);
    }

    public void setTimestamp(String time){this.timestamp = time;}
    public void setGpsSignalStrength(String GpsSignalStrength){this.gpsSignalStrength = GpsSignalStrength; }
    public void setSatelliteCount(int satelliteCount){this.satelliteCount = satelliteCount;}

    public void setCurrentLongitude(double longitude){
        this.currentLongitude = longitude;
    }
    public void setCurrentLatitude(double latitude){
        this.currentLatitude = latitude;
    }
    public void setCurrentAltitude(double altitude){
        this.currentAltitude = altitude;
    }
    public void setCurrentAltitude_seaTohome(double altitude_seaTohome){
        this.currentAltitude_seaTohome = altitude_seaTohome;
    }

    public String getTimestamp(){
        return this.timestamp;
    }
    public String getGpsSignalStrength(){
        return gpsSignalStrength;
    }
    public double getCurrentLongitude(){
        return currentLongitude;
    }
    public double getCurrentLatitude(){
        return currentLatitude;
    }
    public double getCurrentAltitude(){
        return currentAltitude;
    }
    public double getCurrentAltitude_seaTohome() {return currentAltitude_seaTohome;}

    public String logResults(){
        if(loggedTSPI.length() == header.length()){
            loggedTSPI.append(timestamp).append(",");
            loggedTSPI.append(gpsSignalStrength).append(",");
            loggedTSPI.append(satelliteCount).append(",");
            loggedTSPI.append(currentAltitude).append(",");
            loggedTSPI.append(currentLatitude).append(",");
            loggedTSPI.append(currentLongitude).append("\n");
        } else {
            loggedTSPI.delete(0, loggedTSPI.length());
            loggedTSPI.append(timestamp).append(",");
            loggedTSPI.append(gpsSignalStrength).append(",");
            loggedTSPI.append(satelliteCount).append(",");
            loggedTSPI.append(currentAltitude).append(",");
            loggedTSPI.append(currentLatitude).append(",");
            loggedTSPI.append(currentLongitude).append("\n");
        }

        return String.valueOf(loggedTSPI);
    }
}