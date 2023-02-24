package com.example.tealicious.Services;


import com.example.tealicious.Entity.Gallery;
import com.example.tealicious.Pojo.GalleryPojo;

import java.io.IOException;
import java.util.List;

public interface GalleryServices {
    GalleryPojo save(GalleryPojo galleryPojo) throws IOException;
    List<Gallery> fetchAll();
    void deleteById(Integer id);

}

