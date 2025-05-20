package com.example.layarkita;

public class Film {
    private String title;
    private int posterResId;
    private String description;
    private String trailerUrl;

    public Film(String title, int posterResId, String description, String trailerUrl){
        this.title = title;
        this.posterResId = posterResId;
        this.description = description;
        this.trailerUrl = trailerUrl;
    }

    public String getTitle(){
        return title;
    }

    public int getPosterResId(){
        return posterResId;
    }

    public String getDescription() {
        return description;
    }

    public String  getTrailerUrl(){
        return trailerUrl;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Film film = (Film) obj;
        return title.equals(film.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
