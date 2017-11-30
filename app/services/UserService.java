package services;

import java.io.File;

import com.google.inject.ImplementedBy;

import bean.LoginFormBean;
import bean.UpdateInfoFormBean;
import entities.User;
import play.mvc.Http.MultipartFormData.FilePart;
import services.impl.UserServiceImpl;


@ImplementedBy(UserServiceImpl.class)
public interface UserService {
	
	User checkLogin(LoginFormBean loginFormBean) throws Exception;
	
	User findByUsername(String username) throws Exception;
	
	String changeAvatar(FilePart<File> picture, String username) throws Exception;

	boolean updateUser(UpdateInfoFormBean userInfoFormBean) throws Exception;
}
