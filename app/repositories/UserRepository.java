package repositories;

import com.google.inject.ImplementedBy;

import entities.User;
import repositories.impl.UserRepositoryImpl;


// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
@ImplementedBy(UserRepositoryImpl.class)
public interface UserRepository {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 * @throws Exception the exception
	 */
	User findByUsername(String username) throws Exception;

	/**
	 * Update user.
	 *
	 * @param user the user
	 * @throws Exception the exception
	 */
	void updateUser(User user) throws Exception;

}
