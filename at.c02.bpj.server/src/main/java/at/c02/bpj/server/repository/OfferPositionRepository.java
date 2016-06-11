package at.c02.bpj.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import at.c02.bpj.server.entity.OfferPosition;

public interface OfferPositionRepository extends JpaRepository<OfferPosition, Long> {

    /**
     * findet alle OfferPosition, deren Name gleich dem Parameter name ist
     * 
     * @param offerPositionId
     * @return Liste der gefundenen OfferPosition
     */
    List<OfferPosition> findByofferPositionId(@Param("offerPositionId") Long Id);

}
