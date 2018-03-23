package com.travo.repository;

import com.travo.model.Spot;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    List<Spot> findTop5BySpotNameContaining(String value);

    List<Spot> findByEnable(Pageable pageable, boolean enable);
     Spot findSpotById(Long id);
     List<Spot> findSpotByEnable( boolean status);
}
