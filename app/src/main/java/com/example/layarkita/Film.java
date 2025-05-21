package com.example.layarkita;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "films")
public class Film {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int posterResId;
    private String description;
    private String trailerUrl;

    public Film(String title, int posterResId, String description,String trailerUrl){
        this.title = title;
        this.posterResId = posterResId;
        this.description = description;
        this.trailerUrl = trailerUrl;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getPosterResId(){
        return posterResId;
    }

    public void setPosterResId(int posterResId){
        this.posterResId = posterResId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String  getTrailerUrl(){
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl){
        this.trailerUrl = trailerUrl;
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
