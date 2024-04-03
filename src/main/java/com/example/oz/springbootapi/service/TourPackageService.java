package com.example.oz.springbootapi.service;

import com.example.oz.springbootapi.domain.Tour;
import com.example.oz.springbootapi.domain.TourPackage;
import com.example.oz.springbootapi.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService {

    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    /**
     * Create a tour package.
     * @param code the code identifying the package
     * @param name the name of the package
     * @return a new or existing tour package
     */
    public TourPackage createTourPackage(String code, String name) {
        return tourPackageRepository.findById(code)
                .orElse(tourPackageRepository.save(new TourPackage(code, name)));
    }

    /**
     * Retrieve all saved tour packages.
     * @return all the tour packages
     */
    public Iterable<TourPackage> findAll() {
        return tourPackageRepository.findAll();
    }

    /**
     * Return a specific tour package based on its name.
     * @param name the case-sensitive name of the package to search for
     * @return The tour package found
     * @throws RuntimeException tour package was not found
     */
    public TourPackage findByName(String name) {
        return tourPackageRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Tour package named \"" + name + "\" was not found"));
    }

    /**
     * Return a tour package based on its code.
     * @param code the code representing the tour package.
     * @return the matching tour package
     * @throws RuntimeException tour package was not found
     */
    public TourPackage findByCode(String code) {
        return tourPackageRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("\"Tour package with code \"" + code + "\" was not found"));
    }

    /**
     * Get the total number of tour packages.
     * @return the count of saved tour packages
     */
    public long total() {
        return tourPackageRepository.count();
    }
}
