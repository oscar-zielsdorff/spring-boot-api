package com.example.oz.springbootapi.repository;

import com.example.oz.springbootapi.domain.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository extends CrudRepository<Tour, Integer> {

    // Some older versions of spring require this @Param annotation
    List<Tour> findByTourPackageCode(@Param("code")String code);
}
