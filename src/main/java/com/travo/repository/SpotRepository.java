package com.travo.repository;

import com.travo.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    List<Spot> findTop5BySpotNameContaining(String value);
}
