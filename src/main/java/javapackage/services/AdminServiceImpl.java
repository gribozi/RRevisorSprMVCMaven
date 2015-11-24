package javapackage.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import javapackage.dao.restaurant.RestaurantDAO;
import javapackage.domain.Restaurant;

//@Service не нужно, так как этот бин "поднимаем" не автосканом, а в файле applicationContext.xml
public class AdminServiceImpl implements AdminService {

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
	public int createRestaurant(String restName, String restReview, Byte restCuisine, Byte restInterior, Byte restService) {
		
		int restId = restDAO.createRestaurant(new Restaurant(restName, restReview, restCuisine, restInterior, restService));
		
		if (restId != 0) {

			//// Создаем фото-папку для добавляемого ресторана
			//// filesWork.createFolder(restId);
		}

		return restId;
	}

	@Transactional
	@Override
	public List<Restaurant> readAllRestaurants(String sort) {

		return restDAO.readAllRestaurants(sort);
	}

	@Transactional
	@Override
	public Restaurant readRestaurantById(int id) {

		return restDAO.readRestaurantById(id);
	}

	@Transactional
	@Override
	public boolean updateRestaurant(int restId, String restName, String restReview, Byte restCuisine, Byte restInterior, Byte restService) {
		
		if (restDAO.updateRestaurant(new Restaurant(restId, restName, restReview, restCuisine, restInterior, restService))) {

			//// Принимаем фотографии, отправленные пользователем
			//// String photo = request.getParameter("photo");
			//// filesWork.savePhoto(photo);
			
			return true;
		}

		else
			return false;
	}

	@Transactional
	@Override
	public boolean deleteRestaurants(int[] selected_rests) {

		return restDAO.deleteRestaurants(selected_rests);
	}

}