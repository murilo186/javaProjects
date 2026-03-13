package com.biblioteca.domain;

public class Book {

    private int id;
    private String title;
    private String edition;
    private String description;
    private int totalQty;
    private int availableQty;

    public Book(int id, String title, String edition, String description, int totalQty) {
        this(id, title, edition, description, totalQty, totalQty);
    }

    public Book(int id, String title, String edition, String description, int totalQty, int availableQty){
        if (id <= 0 ){
            throw new IllegalArgumentException("Id must be greater than zero");
        }
         if (title == null || title.isBlank()){
            throw new IllegalArgumentException("Title needed");
        }
         if (totalQty < 0 ){
            throw new IllegalArgumentException("Book quantity can not be below zero");
        }
            if (edition == null || edition.isBlank()){
            throw new IllegalArgumentException("Edition needed");
        }
            if (description == null || description.isBlank()){
            throw new IllegalArgumentException("Description needed");
        }
        if (availableQty < 0 || availableQty > totalQty){
            throw new IllegalArgumentException("Available quantity must be between zero and total quantity");
        }
        

        this.id = id;
        this.title = title;
        this.edition = edition;
        this.description = description;
        this.totalQty = totalQty;
        this.availableQty = availableQty;
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    
    }
    public String getEdition(){
        return edition;
    }
    public String getDescription(){
        return description;
    }
    public int getTotalQty(){
        return totalQty;
    }
    public int getAvailableQty(){
        return availableQty;
    }

    public int borrowOne(){
        if (availableQty <= 0){
            throw new IllegalArgumentException("Book is not available for loan");
        }
        return --availableQty;
    }
    public int returnOne(){
        if (availableQty >= totalQty){
            throw new IllegalArgumentException("Available quantity cannot exceed total quantity");
        }
        return ++availableQty;

    }
}
