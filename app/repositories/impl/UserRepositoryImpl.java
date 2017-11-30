package repositories.impl;

import javax.inject.Singleton;

import entities.User;
import repositories.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UserRepositoryImpl.
 */
@Singleton
public class UserRepositoryImpl implements UserRepository {

	/* (non-Javadoc)
	 * @see repositories.UserRepository#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) throws Exception {
		return User.find.query().where().like("username", username).findOne();
	}

	/* (non-Javadoc)
	 * @see repositories.UserRepository#updateUser(entities.User)
	 */
	@Override
	public void updateUser(User user) throws Exception {
		user.update();
	}

}
