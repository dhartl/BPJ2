package at.c02.bpj.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import at.c02.bpj.server.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {

	@Query("select offer from Offer offer join fetch offer.offerPositions positions "
			+ "join fetch positions.article article join fetch article.category "
			+ "join fetch offer.customer join fetch offer.employee")
	List<Offer> findAllFetching();
}
