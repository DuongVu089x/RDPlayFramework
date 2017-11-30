package services.impl;

import java.io.File;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import bean.LoginFormBean;
import bean.UpdateInfoFormBean;
import entities.User;
import io.ebean.Ebean;
import loggers.LogUtils;
import play.mvc.Http.MultipartFormData.FilePart;
import repositories.UserRepository;
import services.UserService;

public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Inject
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User checkLogin(LoginFormBean loginFormBean) throws Exception {
		User user = this.findByUsername(loginFormBean.getUsername());
		if (user != null && user.getPassword().equals(loginFormBean.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public User findByUsername(String username) throws Exception {
		return userRepository.findByUsername(username);
	}

	@Override
	public String changeAvatar(FilePart<File> picture, String username) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (picture != null) {
			String fileName = picture.getFilename();
			File file = picture.getFile();
			String result = saveFile(file, fileName);
			if (result != null && !result.equals("")) {
				Ebean.beginTransaction();
				try {
					User user = this.findByUsername(username);
					user.setAvatar(fileName);
					userRepository.updateUser(user);
					Ebean.commitTransaction();
					return fileName;
				} catch (Exception e) {
					Ebean.rollbackTransaction();
					throw e;
				} finally {
					Ebean.endTransaction();
				}
			}
		}
		return null;
	}

	private String saveFile(File file, String fileName) throws Exception {
		try {
			fileName = System.currentTimeMillis() + fileName;
			FileUtils.moveFile(file, new File("public/images/avatars", fileName));
			return fileName;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean updateUser(UpdateInfoFormBean userInfoFormBean) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (!userInfoFormBean.getPassword().equals(userInfoFormBean.getConfirmPassword())) {
			return false;
		}
		Ebean.beginTransaction();
		try {
			User user = this.findByUsername(userInfoFormBean.getUsername());
			if (user == null) {
				return false;
			}
			user.setPassword(userInfoFormBean.getPassword());
			userRepository.updateUser(user);
			Ebean.commitTransaction();
			return true;
		} catch (Exception e) {
			Ebean.rollbackTransaction();
			throw e;
		} finally {
			Ebean.endTransaction();
			LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

}
