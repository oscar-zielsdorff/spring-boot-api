package com.example.oz.springbootapi.repository;

import com.example.oz.springbootapi.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for managing tours.
 */
public interface TourRepository extends CrudRepository<Tour, Integer>, PagingAndSortingRepository<Tour, Integer> {


    // Older versions of spring require this @Param annotation

    /**
     * Returns a list of tours associated with a specific tour package code.
     * @param code the tour package code
     * @param pageable the pagination options
     * @return a paged collection of matching tours
     */
    Page<Tour> findByTourPackageCode(@Param("code")String code, Pageable pageable);

    /**
     * Look for a tour based on its title.
     * @param title the title to look for
     * @return Optional of a matching Tour
     */
    Optional<Tour> findByTitle(String title);
}
