package javapackage.services;

import javapackage.domain.Restaurant;

import java.util.List;

public interface AdminService {
	public int createRestaurant(String restName, String restReview, Byte restCuisine, Byte restInterior, Byte restService);
	public List<Restaurant> readAllRestaurants(String sort);
	public Restaurant readRestaurantById(int id);
	public boolean updateRestaurant(int restId, String restName, String restReview, Byte restCuisine, Byte restInterior, Byte restService);
	public boolean deleteRestaurants(int[] selected_rests);
	
}