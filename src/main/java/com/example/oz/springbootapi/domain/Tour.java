package com.example.oz.springbootapi.domain;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents a tour offered to customers.
 */
@Entity
public class Tour {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;

    @Column(length = 2000)
    private String description;

    private Integer price;
    private String duration;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    private Region region;

    @ManyToOne
    private TourPackage tourPackage;

    public Tour(String title, String description, Integer price, String duration, Difficulty difficulty,
                Region region, TourPackage tourPackage) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tourPackage = tourPackage;
        this.difficulty = difficulty;
        this.region = region;
    }

    protected Tour() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(TourPackage tourPackage) {
        this.tourPackage = tourPackage;
    }
}
