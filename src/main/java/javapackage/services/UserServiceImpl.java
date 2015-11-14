package javapackage.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javapackage.dao.RestaurantDAO;
import javapackage.dao.RestaurantDAOImpl;
import javapackage.domain.Restaurant;

@Service
public class UserServiceImpl implements UserService {

	private RestaurantDAO restarauntDAOImpl = new RestaurantDAOImpl();

	@Transactional
	@Override
	public List<Restaurant> readAllRestaurants(String sort) {

		return restarauntDAOImpl.readAllRestaurants(sort);
	}

	@Transactional
	@Override
	public List<Restaurant> readAllRestaurantsBySearch(String searchQuery) {

		return restarauntDAOImpl.readAllRestaurantsBySearch(searchQuery);
	}

	@Transactional
	@Override
	public Restaurant readRestaurantById(int id) {

		return restarauntDAOImpl.readRestaurantById(id);
	}

}
