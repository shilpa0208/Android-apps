package com.example.user.musicapp;

import android.net.Uri;

//Class to hold the various data which is used to display in the ListView.
public class Music {

    //Private fields declared.
    private String title;
    private String artist;
    private int imageResourceId;
    private int id;
    private String webpage;
    private String wikiArtist;
    private String wikiSong;

    //Constructor initialises the various fields that are declared.
    Music(String title,String artist,int imageResourceId,int id,String webpage,String wikiSong,String wikiArtist)
    {
        this.title=title;
        this.artist=artist;
        this.imageResourceId=imageResourceId;
        this.id=id;
        this.webpage=webpage;
        this.wikiSong=wikiSong;
        this.wikiArtist=wikiArtist;
    }

    //Setter and getter methods for the respective fields are defined.
    public int getId() {  return id; }

    public String getTitle() {  return title; }

    public void setTitle(String title) { this.title = title;  }

    public String getArtist() { return artist;}

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getWebpage() { return webpage; }

    public void setWebpage() { this.webpage=webpage; }

    public void setWikiArtist(String wikiArtist) { this.wikiArtist=wikiArtist;  }

    public String getWikiArtist() { return  wikiArtist;}

    public void  setWikiSong(String wikiSong) { this.wikiSong=wikiSong;}

    public String getWikiSong() { return wikiSong;}


}
