package javapackage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javapackage.services.AdminService;
import javapackage.services.AdminServiceImpl;
import javapackage.services.UserService;
import javapackage.services.UserServiceImpl;

@Controller
public class MainController {

	private AdminService adminServiceImpl = new AdminServiceImpl();
	private UserService userServiceImpl = new UserServiceImpl();

	@RequestMapping(value = "/RestList")
	public String displayRestList(
			@RequestParam(value = "queary", required = false) String queary,
			@RequestParam(value = "sort", required = false) String sort, Model model) {

		// Если обрабатываем POST-запрос поиска
		if (queary != null) {
			model.addAttribute("restList", userServiceImpl.readAllRestaurantsBySearch(queary));
			model.addAttribute("quearyFromPost", queary);
		}
		// Если обрабатываеми POST-запрос сортировки
		else if (sort != null) {
			model.addAttribute("restList", userServiceImpl.readAllRestaurants(sort));
			model.addAttribute("sortFromPost", sort);
		}
		// Если обрабатываеми GET-запрос (без параметров)
		else {
			model.addAttribute("restList", userServiceImpl.readAllRestaurants("rateTotal"));
		}
		return "rest-list";
	}

	@RequestMapping(value = "/RestOne")
	public String displayRestOne(@RequestParam("rest") int restId, Model model) {
		model.addAttribute("restOne", userServiceImpl.readRestaurantById(restId));

		//// Читаем или считаем фотки из папки ресторана
		//// filesWork.readPhoto(restId);
		//// request.setAttribute("aAa", bBb);

		return "rest-one";
	}

	@RequestMapping(value = "/AdmRestList")
	public String displayAdmRestList(
			@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "checked", required = false) int[] checked, Model model) {

		// Обработка POST-запроса

		// Если удаляется один ресторан (со страницы adm-rest-one.jsp)
		if (id != 0) {
			// Формируем массив из одного ресторана (для передачи в параметр SQL-запроса)
			int[] removeOne = new int[1];
			removeOne[0] = id;

			model.addAttribute("dellOK", adminServiceImpl.deleteRestaurants(removeOne));
		}
		
		// Если удаляется несколько ресторанов (со страницы adm-rest-list.jsp)
		else if (checked != null) {
			model.addAttribute("dellOK", adminServiceImpl.deleteRestaurants(checked));
		}

		// Выполняется в любом случае, и когда POST-запросы, и когда страница открывается без параметров (GET-запрос)

		model.addAttribute("restList", adminServiceImpl.readAllRestaurants("rateTotal"));
		return "adm-rest-list";
	}

	@RequestMapping(value = "/AdmRestOne")
	public String displayAdmRestOne(
			@RequestParam(value = "id", required = false, defaultValue = "0") int restId,
			@RequestParam(value = "name", required = false) String restName,
			@RequestParam(value = "review", required = false) String restReview,
			@RequestParam(value = "cuisine", required = false) Byte restCuisine,
			@RequestParam(value = "interior", required = false) Byte restInterior,
			@RequestParam(value = "service", required = false) Byte restService, Model model) {
		
		boolean savedOK;

		// Обработка POST-запросов

		// Если id не задан, значит обрабатываем добавление
		if (restId == 0) {
			
			restId = adminServiceImpl.createRestaurant(restName, restReview, restCuisine, restInterior, restService);
			if (restId != 0) savedOK = true;
			else savedOK = false;
			
			model.addAttribute("savedOK", savedOK);
			model.addAttribute("operationType", "add");
		}

		// Если есть id, значит обрабатываем редактирование
		else {

			model.addAttribute("savedOK", adminServiceImpl.updateRestaurant(restId, restName, restReview, restCuisine, restInterior, restService));
			model.addAttribute("operationType", "edit");
		}

		// Выполняется в любом случае, и когда POST-запросы, и когда страница открывается без параметров (GET-запрос)

		model.addAttribute("restOne", adminServiceImpl.readRestaurantById(restId));

		//// Читаем или считаем фотки из папки ресторана
		//// filesWork.readPhoto(restId);
		//// model.addAttribute("aAa", bBb);

		return "adm-rest-one";
	}

	@RequestMapping(value = "/AdmRestAddEdit")
	public String displayAdmRestAddEdit(@RequestParam(value = "id", required = false, defaultValue = "0") int restId, Model model) {

		// Если GET-запрос на редактирование
		if (restId != 0) {

			model.addAttribute("restOne", adminServiceImpl.readRestaurantById(restId));

			//// Читаем или считаем фотки из папки ресторана
			//// filesWork.readPhoto(restId);
			//// request.setAttribute("aAa", bBb);
		}

		// В любом случае выводим страницу с формой
		return "adm-rest-add-edit";
	}

}