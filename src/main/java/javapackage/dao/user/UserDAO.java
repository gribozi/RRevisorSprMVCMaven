package javapackage.dao.user;

import javapackage.domain.User;

public interface UserDAO {

	User readUserByName (String username);

}
