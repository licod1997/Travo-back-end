package com.travo.repository;

import com.travo.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {
     Spot findSpotById(Long id);
     List<Spot> findSpotByEnable( boolean status);
}
