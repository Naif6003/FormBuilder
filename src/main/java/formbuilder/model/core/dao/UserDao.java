package formbuilder.model.core.dao;

import java.util.List;

import formbuilder.model.core.User;

public interface UserDao {

	User getUser(Integer id);

	List<User> getUsers();

	User saveUser(User user);

	void delete(int id);

	User saveUserSignup(User user);

    User getUserByUsername( String username );


}
