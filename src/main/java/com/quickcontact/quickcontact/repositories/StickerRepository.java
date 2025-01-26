package com.quickcontact.quickcontact.repositories;

import com.quickcontact.quickcontact.entities.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long> {

}
