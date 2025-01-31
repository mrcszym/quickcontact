package com.quickcontact.quickcontact.repositories;

import com.quickcontact.quickcontact.entities.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long> {

    @Query("SELECT s FROM Sticker s WHERE s.customer.id = :customerId")
    List<Sticker> findStickersByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT s FROM Sticker s LEFT JOIN FETCH s.messages WHERE s.id = :stickerId")
    Optional<Sticker> findStickerById(@Param("stickerId") Long stickerId);

}
