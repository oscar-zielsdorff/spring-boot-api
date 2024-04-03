package com.example.oz.springbootapi.service;

import com.example.oz.springbootapi.domain.Difficulty;
import com.example.oz.springbootapi.domain.Region;
import com.example.oz.springbootapi.domain.Tour;
import com.example.oz.springbootapi.domain.TourPackage;
import com.example.oz.springbootapi.repository.TourPackageRepository;
import com.example.oz.springbootapi.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for interacting with tours.
 */
@Service
public class TourService {

    private final TourRepository tourRepository;
    private final TourPackageRepository tourPackageRepository;

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

        Optional<Tour> existingTour = tourRepository.findByTitle(title);
        if (existingTour.isPresent()) {
            return existingTour.get();
        }

        Optional<TourPackage> existingTourPackage = tourPackageRepository.findByName(tourPackageName);
        if (existingTourPackage.isPresent()) {
            return tourRepository.save(new Tour(title, description, price, duration, difficulty,region, existingTourPackage.get()));
        }
        else {
            throw new RuntimeException("Could not create tour, specified tour package was not found");
        }
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
