package repositories;

import java.util.List;

import com.google.inject.ImplementedBy;

import entities.Clazz;
import repositories.impl.ClassRepositoryImpl;

// TODO: Auto-generated Javadoc
/**
 * The Interface ClassRepository.
 */
@ImplementedBy(ClassRepositoryImpl.class)
public interface ClassRepository {
	
	/**
	 * Gets the class by page and keyword.
	 *
	 * @param page the page
	 * @param keyword the keyword
	 * @return the class by page and keyword
	 */
	List<Clazz> getClassByPageAndKeyword(int page, String keyword);

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Clazz> findAll() throws Exception;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the clazz
	 */
	Clazz findById(int id);

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the clazz
	 */
	Clazz findByName(String name);
	
	/**
	 * Gets the total page.
	 *
	 * @param keyword the keyword
	 * @return the total page
	 */
	Integer getTotalPage(String keyword);

	/**
	 * Update class.
	 *
	 * @param clazz the clazz
	 */
	void updateClass(Clazz clazz);

	/**
	 * Insert class.
	 *
	 * @param clazz the clazz
	 * @return true, if successful
	 */
	boolean insertClass(Clazz clazz);

	/**
	 * Delete class.
	 *
	 * @param clazz the clazz
	 * @return true, if successful
	 */
	boolean deleteClass(Clazz clazz);
}
