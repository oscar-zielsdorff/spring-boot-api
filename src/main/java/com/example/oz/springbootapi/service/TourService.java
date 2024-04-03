package com.example.oz.springbootapi.service;

import com.example.oz.springbootapi.domain.Difficulty;
import com.example.oz.springbootapi.domain.Region;
import com.example.oz.springbootapi.domain.Tour;
import com.example.oz.springbootapi.domain.TourPackage;
import com.example.oz.springbootapi.repository.TourPackageRepository;
import com.example.oz.springbootapi.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    /**
     * Create a new tour.
     * @return the new tour or an existing one if found
     */
    public Tour createTour(String title, String description, Integer price, String duration,
                           Difficulty difficulty, Region region, String tourPackageName) {

        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour package does not exist"));

        return tourRepository.save(new Tour(title, description, price, duration, difficulty, region, tourPackage));
    }

    /**
     * Retrieves all stored tours.
     * @return every tour object
     */
    public Iterable<Tour> findAll() {
        return tourRepository.findAll();
    }

    /**
     * Get the total number of tours saved.
     * @return the total count of tours
     */
    public long count() {
        return tourRepository.count();
    }
}
