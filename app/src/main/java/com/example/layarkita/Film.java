package com.example.layarkita;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "films")
public class Film {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int posterResId;
    private String description_id;
    private String description_en;
    private String description_de;
    private String trailerUrl;

    public Film(String title, int posterResId, String description_id, String description_en, String description_de, String trailerUrl){
        this.title = title;
        this.posterResId = posterResId;
        this.description_id = description_id;
        this.description_en = description_en;
        this.description_de = description_de;
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

    public String getDescription_id() {
        return description_id;
    }

    public void setDescription_id(String description_id){
        this.description_id = description_id;
    }

    public String getDescription_en(){
        return description_en;
    }

    public void setDescription_en(String description_en){
        this.description_en = description_en;
    }

    public String getDescription_de(){
        return description_de;
    }

    public void setDescription_de(String description_de){
        this.description_de = description_de;
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
