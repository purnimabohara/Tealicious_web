package com.example.tealicious.Repo;


import com.example.tealicious.Entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GalleryRepo extends JpaRepository<Gallery, Integer> {

    Optional<Gallery> findGalleryByName(String name);
}

