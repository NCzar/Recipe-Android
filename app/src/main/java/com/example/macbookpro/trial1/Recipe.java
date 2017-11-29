package com.example.macbookpro.trial1;

/**
 * Created by macbookpro on 11/28/17.
 */

public class Recipe {

    String imagelink;
    String publisher;
    String title;

    public Recipe(){

    }

    public Recipe(String title,String publisher,String imagelink){
        this.title=title;
        this.publisher = publisher;
        this.imagelink = imagelink;

    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(){
        this.publisher=publisher;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(){
        this.title=title;
    }

    public String getImagelink(){
        return title;
    }

    public void setImagelink(){
        this.imagelink=imagelink;
    }
}
