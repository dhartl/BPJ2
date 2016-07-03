package at.c02.bpj.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import at.c02.bpj.server.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {

	@Query("select distinct offer from Offer offer left join fetch offer.offerPositions positions "
			+ "left join fetch positions.article article left join fetch article.category "
			+ "left join fetch offer.customer left join fetch offer.employee")
	List<Offer> findAllFetching();
}
