package com.example.layarkita;

import java.util.ArrayList;
import java.util.List;

public class WatchListHelper {
    private static final List<Film> watchlist = new ArrayList<>();

    public static void addToWatchList(Film film){
        if (!watchlist.contains(film)){
            watchlist.add(film);
        }
    }

    public static void removeFromWatchList(Film film){
        watchlist.remove(film);
    }

    public static List<Film> getWatchList() {
        return new ArrayList<>(watchlist);
    }
}
