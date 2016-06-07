package at.c02.bpj.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.c02.bpj.server.entity.OfferPosition;

public interface OfferPositionRepository extends JpaRepository<OfferPosition, Long> {

}