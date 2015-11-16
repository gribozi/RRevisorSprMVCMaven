package javapackage.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javapackage.dao.RestaurantDAO;
import javapackage.domain.Restaurant;

@Service
public class UserServiceImpl implements UserService {

	// Здесь производится инъекция через XML-файл aplicationContext.xml
	private RestaurantDAO restDAO;

	public RestaurantDAO getRestDAO() {
		return restDAO;
	}

	public void setRestDAO(RestaurantDAO restDAO) {
		this.restDAO = restDAO;
	}

	@Transactional
	@Override
	public List<Restaurant> readAllRestaurants(String sort) {

		return restDAO.readAllRestaurants(sort);
	}

	@Transactional
	@Override
	public List<Restaurant> readAllRestaurantsBySearch(String searchQuery) {

		return restDAO.readAllRestaurantsBySearch(searchQuery);
	}

	@Transactional
	@Override
	public Restaurant readRestaurantById(int id) {

		return restDAO.readRestaurantById(id);
	}

}
