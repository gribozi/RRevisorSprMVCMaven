package javapackage.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javapackage.dao.RestaurantDAO;
import javapackage.dao.RestaurantDAOImpl;
import javapackage.domain.Restaurant;

@Service
public class AdminServiceImpl implements AdminService {

	private RestaurantDAO restarauntDAOImpl = new RestaurantDAOImpl();

	@Transactional
	@Override
	public int createRestaurant(String restName, String restReview, Byte restCuisine, Byte restInterior, Byte restService) {
		
		int restId = restarauntDAOImpl.createRestaurant(new Restaurant(restName, restReview, restCuisine, restInterior, restService));
		
		if (restId != 0) {

			//// Создаем фото-папку для добавляемого ресторана
			//// filesWork.createFolder(restId);
		}

		return restId;
	}

	@Transactional
	@Override
	public List<Restaurant> readAllRestaurants(String sort) {

		return restarauntDAOImpl.readAllRestaurants(sort);
	}

	@Transactional
	@Override
	public Restaurant readRestaurantById(int id) {

		return restarauntDAOImpl.readRestaurantById(id);
	}

	@Transactional
	@Override
	public boolean updateRestaurant(int restId, String restName, String restReview, Byte restCuisine, Byte restInterior, Byte restService) {
		
		if (restarauntDAOImpl.updateRestaurant(new Restaurant(restId, restName, restReview, restCuisine, restInterior, restService))) {

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

		return restarauntDAOImpl.deleteRestaurants(selected_rests);
	}

}