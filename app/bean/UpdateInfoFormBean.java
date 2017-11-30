package bean;

import javax.validation.constraints.Size;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateInfoFormBean.
 */
public class UpdateInfoFormBean {

	/** The username. */
	@Size(max = 50, min = 6)
	public String username;

	/** The password. */
	@Size(max = 50, min = 6)
	public String password;

	/** The confirm password. */
	@Size(max = 50, min = 6)
	public String confirmPassword;

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the confirm password.
	 *
	 * @return the confirm password
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets the confirm password.
	 *
	 * @param confirmPassword the new confirm password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
