package javapackage.services;

import javapackage.domain.Restaurant;

import java.util.List;

public interface UserService {
	public List<Restaurant> readAllRestaurants(String sort);
	public List<Restaurant> readAllRestaurantsBySearch(String searchQuery);
	public Restaurant readRestaurantById(int id);
	
}