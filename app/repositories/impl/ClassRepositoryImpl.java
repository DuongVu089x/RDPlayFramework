package repositories.impl;

import java.util.List;

import javax.inject.Singleton;

import entities.Clazz;
import io.ebean.Ebean;
import repositories.ClassRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassRepositoryImpl.
 */
@Singleton
public class ClassRepositoryImpl implements ClassRepository {

	/* (non-Javadoc)
	 * @see repositories.ClassRepository#getClassByPageAndKeyword(int, java.lang.String)
	 */
	@Override
	public List<Clazz> getClassByPageAndKeyword(int page, String keyword) {
		return Clazz.find.query().where().ilike("name", "%" + keyword + "%").orderBy("id desc").setFirstRow(page)
				.setMaxRows(10).findPagedList().getList();
	}

	/* (non-Javadoc)
	 * @see repositories.ClassRepository#findAll()
	 */
	@Override
	public List<Clazz> findAll() throws Exception {
		return Clazz.find.all();
	}

	/* (non-Javadoc)
	 * @see repositories.ClassRepository#findById(int)
	 */
	@Override
	public Clazz findById(int id) {
		return Clazz.find.byId(id);
	}

	/* (non-Javadoc)
	 * @see repositories.ClassRepository#findByName(java.lang.String)
	 */
	@Override
	public Clazz findByName(String name) {
		return Clazz.find.query().where().like("name", name).findOne();
	}

	/* (non-Javadoc)
	 * @see repositories.ClassRepository#getTotalPage(java.lang.String)
	 */
	@Override
	public Integer getTotalPage(String keyword) {
		String sql = "SELECT COUNT(*) as total " + "FROM clazz c " + "WHERE c.name LIKE :keyword";
		int total = Ebean.createSqlQuery(sql).setParameter("keyword", "%" + keyword + "%").findOne()
				.getInteger("total");
		return total;
	}

	/* (non-Javadoc)
	 * @see repositories.ClassRepository#updateClass(entities.Clazz)
	 */
	@Override
	public void updateClass(Clazz clazz) {
		clazz.update();
	}

	/* (non-Javadoc)
	 * @see repositories.ClassRepository#insertClass(entities.Clazz)
	 */
	@Override
	public boolean insertClass(Clazz clazz) {
		try {
			clazz.insert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see repositories.ClassRepository#deleteClass(entities.Clazz)
	 */
	@Override
	public boolean deleteClass(Clazz clazz) {
		return clazz.delete();
	}

}
