package javapackage.models;

public class Restaurant implements Comparable<Restaurant> {
	private int id;
	private String name;
	private String review;
	private byte rateCuisine;
	private byte rateInterior;
	private byte rateService;
	private String geo;
	
	public Restaurant(int rId, String rName, byte rRateCuisine, byte rRateInterior, byte rRateService) {
		id = rId;
		name = rName;
		rateCuisine = rRateCuisine;
		rateInterior = rRateInterior;
		rateService = rRateService;
	}
	
	// Уточнить, правильно ли создавать разные конструкторы под разные варианты (например при генерации из БД)
	public Restaurant(int rId, String rName, String rReview, byte rRateCuisine, byte rRateInterior, byte rRateService) {
		id = rId;
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
	
	@Override
	public int compareTo(Restaurant rest) {
		
		Restaurant tmp = (Restaurant)rest;
		
		// Так как сортировка обратная, меняем местами знаки сравнивания
		
		if (this.getRateTotal() < tmp.getRateTotal()) {
			/* текущее меньше полученного */
			return 1;
		}
		else if (this.getRateTotal() > tmp.getRateTotal())
		{
			/* текущее больше полученного */
			return -1;
		}
		
		/* текущее равно полученному */
		return 0;  
	}
	
	
/*	Реализация сортировки списка через локальное определение компоратора
	Ппроизоводится во внешнем модуле, обычно прямо перед сортировкой)
	
	Collections.sort(restlList, new Comparator<Restaurant>() {
		@Override
		public int compare(Restaurant rest1, Restaurant rest2) {
			Float rate1 = (Float) rest1.getRateTotal();
			Float rate2 = (Float) rest2.getRateTotal();
			
			// Так как сортировка обратная, меняем местами rate1 и rate2
			return rate2.compareTo(rate1);
		}
	});
	
*/

// Collections.sort(restList);

}