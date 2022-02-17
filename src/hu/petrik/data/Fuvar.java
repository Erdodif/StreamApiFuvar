package hu.petrik.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Fuvar {
    private int id;
    private LocalDateTime beginTime;
    private int rideSeconds;
    private double distance;
    private double priceUsd;
    private double tipUsd;
    private boolean card;

    public Fuvar(String line){
        String[] data = line.split(";");
        this.id = Integer.parseInt(data[0]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.beginTime = LocalDateTime.parse(data[1], formatter);
        this.rideSeconds = Integer.parseInt(data[2]);
        this.distance = Double.parseDouble(data[3].replace(',','.'));
        this.priceUsd = Double.parseDouble(data[4].replace(',','.'));
        this.tipUsd = Double.parseDouble(data[5].replace(',','.'));
        this.card = Objects.equals(data[6], "bankkártya");
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public int getRideSeconds() {
        return rideSeconds;
    }
    public void setRideSeconds(int rideSeconds) {
        this.rideSeconds = rideSeconds;
    }

    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPriceUsd() {
        return priceUsd;
    }
    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public double getTipUsd() {
        return tipUsd;
    }
    public void setTipUsd(double tipUsd) {
        this.tipUsd = tipUsd;
    }

    public boolean isCard() {
        return card;
    }
    public void setCard(boolean card) {
        this.card = card;
    }

    public double getIncome(){
        return this.priceUsd + this.tipUsd;
    }
    public double getDistanceKM(){
        return this.distance *1.6;
    }
    public double getGenerousity(){
        return this.tipUsd/this.getPriceUsd();
    }

    @Override
    public String toString() {
        return "Fuvar" +
                "\n\tid: " + id +
                "\n\tbeginTime: " + beginTime +
                "\n\trideSeconds: " + rideSeconds +
                "\n\tdistance: " + distance +
                "\n\tpriceUsd: " + priceUsd +
                "\n\ttipUsd: " + tipUsd +
                "\n\tcard: " + card ;
    }
}
