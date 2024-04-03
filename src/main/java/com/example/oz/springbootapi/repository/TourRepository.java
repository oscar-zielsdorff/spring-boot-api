package com.example.oz.springbootapi.repository;

import com.example.oz.springbootapi.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer> {

    // Some older versions of spring require this @Param annotation
    Page<Tour> findByTourPackageCode(@Param("code")String code, Pageable pageable);
}
