package com.example.tourmanagement.repository;

import com.example.tourmanagement.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepo extends JpaRepository<Tour, Long> {
    @Query("SELECT p FROM Tour p WHERE CONCAT(p.detailRoute, ' ', p.tourDescription, ' ',p.tourName) LIKE %?1%")
    public Page<Tour> searchPageable(Pageable pageable, String keyword);
}
