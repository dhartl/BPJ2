package at.c02.bpj.server.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "offerPosition")
public class OfferPosition extends ModLogEntity<Long> {

    private static final long serialVersionUID = 8923374409266935290L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offerPositionId")
    private Long offerPositionId;
    @JsonIgnore
	@ManyToOne(optional = false)
	// in EntitätenKlassen am Server wird der JOIN in der Datenbank deklariert.
    @JoinColumn(name = "offerId")
    private Offer offer;
    @Column(name = "posNr", nullable = false)
    private Integer posNr;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "amount", nullable = false)
    private Integer amount;
	@ManyToOne(optional = false)
    @JoinColumn(name = "articleId")
    private Article article;

    @Override
    public Long getId() {
	return getOfferPositionId();
    }

    public Long getOfferPositionId() {
	return offerPositionId;
    }

    public void setOfferPositionId(Long offerPositionId) {
	this.offerPositionId = offerPositionId;
    }

    public Offer getOffer() {
	return offer;
    }

    public void setOffer(Offer offer) {
	this.offer = offer;
    }

    public Integer getPosNr() {
	return posNr;
    }

    public void setPosNr(Integer posNr) {
	this.posNr = posNr;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public Integer getAmount() {
	return amount;
    }

    public void setAmount(Integer amount) {
	this.amount = amount;
    }

    public Article getArticle() {
	return article;
    }

    public void setArticle(Article article) {
	this.article = article;
    }

}
