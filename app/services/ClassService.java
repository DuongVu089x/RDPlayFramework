package services;

import java.util.List;
import java.util.concurrent.CompletionStage;

import com.google.inject.ImplementedBy;

import bean.BaseViewModel;
import entities.Clazz;
import services.impl.ClassServiceImpl;

@ImplementedBy(ClassServiceImpl.class)
public interface ClassService {
	
	CompletionStage<List<Clazz>> getClassByPageAndKeyword(int page, String keyword) throws Exception;

	
	List<Clazz> findAll() throws Exception;


	CompletionStage<BaseViewModel<Clazz>> findByPageAndKeyword(BaseViewModel<Clazz> baseViewModel) throws Exception;


	CompletionStage<Clazz> getById(Integer id) throws Exception;


	void update(Clazz clazz) throws Exception;


	CompletionStage<Boolean> insertClass(Clazz clazz) throws Exception;


	CompletionStage<Boolean> deleteClass(Integer id) throws Exception;
}
