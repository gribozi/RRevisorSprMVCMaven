package javapackage.dao.restaurant;

import java.util.List;

import javapackage.domain.Restaurant;

public interface RestaurantDAO {

	public int createRestaurant(Restaurant restaurant);
	public List<Restaurant> readAllRestaurants(String sort);
	public List<Restaurant> readAllRestaurantsBySearch(String searchQuery);
	public Restaurant readRestaurantById(int id);
	public boolean updateRestaurant(Restaurant restaurant);
	public boolean deleteRestaurants(int[] selected_rests);
	
}
