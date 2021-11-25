package com.mh.blog.service;

import com.mh.blog.entity.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PictureService {

    List<Picture> listPicture();

    Picture savePicture(Picture picture);

    Picture getPicture(Long id);

    Page<Picture> listPicture(Pageable pageable);

    Picture updatePicture(Long id,Picture picture);

    void deletePicture(Long id);
}
