package com.travo.repository;

import com.travo.model.Image;
import com.travo.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findBySpot(Spot spot);

    Image findById(Long id);

}
