package com.travo.repository;

import com.travo.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by asus on 3/22/2018.
 */
public interface DiaryRepository extends JpaRepository<Diary,Long> {

}
