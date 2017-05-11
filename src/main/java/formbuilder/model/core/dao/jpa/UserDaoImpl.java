package formbuilder.model.core.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import formbuilder.model.core.User;
import formbuilder.model.core.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF') or principal.username == returnObject.username")
	public User getUser(Integer id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> getUsers() {
		return entityManager.createQuery("from User order by id", User.class).getResultList();
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF') or principal.username == #user.username")
	public User saveUser(User user) {
		return entityManager.merge(user);
	}

	@Override
	@Transactional
	public User saveUserSignup(User user) {
		return entityManager.merge(user);
	}


	@Override
	@Transactional
	public void delete(int id) {
		entityManager.remove(getUser(id));
	}

	@Override
	public User getUserByUsername(String username) {
		// This method is mainly used by the security code which usually needs
		// both the user credentials (i.e. username and password) and the
		// granted authorities (i.e. roles), so here we load the roles
		// collection "eagerly" using a join fetch to avoid a second query.
		String query = "from User user left join fetch user.roles " + "where lower(username) = :username";

		List<User> users = entityManager.createQuery(query, User.class).setParameter("username", username.toLowerCase())
				.getResultList();
		return users.size() == 0 ? null : users.get(0);
	}


}
