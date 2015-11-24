package javapackage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="restaurants")
public class Restaurant {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int id;

	private String name;
	
	private String review;
	
	@Column(name="cuisine_rating")
	private byte rateCuisine;
	
	@Column(name="interior_rating")
	private byte rateInterior;
	
	@Column(name="service_rating")
	private byte rateService;
	
	private String geo;
	
	public Restaurant(){
	}
	
	public Restaurant(int rId, String rName, String rReview, byte rRateCuisine, byte rRateInterior, byte rRateService) {
		id = rId;
		name = rName;
		review = rReview;
		rateCuisine = rRateCuisine;
		rateInterior = rRateInterior;
		rateService = rRateService;
	}
	
	public Restaurant(String rName, String rReview, byte rRateCuisine, byte rRateInterior, byte rRateService) {
		name = rName;
		review = rReview;
		rateCuisine = rRateCuisine;
		rateInterior = rRateInterior;
		rateService = rRateService;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getReview() {
		return review.replaceAll("\n", "<br />");
	}
	
	public String getReviewAdm() {
		return review;
	}
	
	public byte getRateCuisine() {
		return rateCuisine;
	}
	
	public byte getRateInterior() {
		return rateInterior;
	}
	
	public byte getRateService() {
		return rateService;
	}
	
	public String getGeo() {
		return geo;
	}
	
	public float getRateTotal() {
		float rateTotal = (float) (rateCuisine * 0.4 + rateInterior * 0.3 + rateService * 0.3);
		return rateTotal;
	}

}