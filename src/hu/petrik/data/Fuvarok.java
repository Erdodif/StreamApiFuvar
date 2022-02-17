package hu.petrik.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fuvarok {
    private List<Fuvar> fuvarList;

    public Fuvarok(String fileName) {
        fuvarList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                fuvarList.add(new Fuvar(line));
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Fuvarok(List<Fuvar> fuvarList) {
        this.fuvarList = fuvarList;
    }

    public Fuvar getFuvar(int index) {
        return fuvarList.get(index);
    }

    public Stream<Fuvar> getStream() {
        return this.fuvarList.stream();
    }

    public long getfuvarCount() {
        return this.getStream().count();
    }

    public double getDistanceSum() {
        return this.getStream().mapToDouble(Fuvar::getDistance).sum();
    }

    public Stream<Fuvar> getRidesById(int id) {
        return this.getStream().filter(fuvar -> fuvar.getId() == id);
    }

    public double sumIncomeById(int id) {
        return this.getRidesById(id).mapToDouble(Fuvar::getIncome).sum();
    }

    public long countRidesById(int id) {
        return this.getRidesById(id).count();
    }

    public double getDistanceSumKMById(int id) {
        return this.getRidesById(id).mapToDouble(Fuvar::getDistanceKM).sum();
    }

    public Fuvar getLongestRide() {
        return this.getStream().max(Comparator.comparingInt(Fuvar::getRideSeconds)).get();
    }

    public Fuvar getMostGenerousRide() {
        return this.getStream().max(Comparator.comparingDouble(Fuvar::getGenerousity)).get();
    }

    public Stream<Fuvar> getInvalids() {
        return this.getStream().filter(
                fuvar -> fuvar.getRideSeconds() > 0 && fuvar.getPriceUsd() > 0.0 && fuvar.getDistance() == 0);
    }

    public long getInvalidsCount() {
        return this.getInvalids().count();
    }

    public long getInvalidTimeSum() {
        return this.getInvalids().mapToInt(Fuvar::getRideSeconds).sum();
    }

    public double getInvalidIncome() {
        return this.getInvalids().mapToDouble(Fuvar::getIncome).sum();
    }

    public boolean hasId(int id) {
        return this.getStream().anyMatch(fuvar -> fuvar.getId() == id);
    }

    public Fuvarok getSortedNonZeroDistancedRides() {
        return new Fuvarok(
                this.getStream()
                        .filter(fuvar -> fuvar.getRideSeconds() != 0)
                        .sorted(Comparator.comparingInt(Fuvar::getRideSeconds)).collect(Collectors.toList()));
    }

    public Stream<Fuvar> getRidesByMonthAndDay(int month, int day) {
        return this.getStream()
                .filter(fuvar -> fuvar.getBeginTime().getMonthValue() == month
                        && fuvar.getBeginTime().getDayOfMonth() == day);
    }

    public long getRideCountByMonthAndDay(int month, int day) {
        return this.getRidesByMonthAndDay(month,day).count();
    }

    public double getTipRatioByMonthAndDay(int month,int day) {
        return this.getRidesByMonthAndDay(month,day).mapToDouble(Fuvar::getPriceUsd).count() /
                this.getRidesByMonthAndDay(month,day).mapToDouble(Fuvar::getTipUsd).count();
    }
}
