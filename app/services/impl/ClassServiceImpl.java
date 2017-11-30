package services.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Singleton;

import bean.BaseViewModel;
import databases.DatabaseExecutionContext;
import entities.Clazz;
import io.ebean.Ebean;
import loggers.LogUtils;
import repositories.ClassRepository;
import services.ClassService;

@Singleton
public class ClassServiceImpl implements ClassService {

	private ClassRepository classRepository;
	private final DatabaseExecutionContext executionContext;

	@Inject
	public ClassServiceImpl(ClassRepository classRepository, DatabaseExecutionContext executionContext) {
		this.classRepository = classRepository;
		this.executionContext = executionContext;
	}

	@Override
	public CompletionStage<List<Clazz>> getClassByPageAndKeyword(int page, String keyword) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		return CompletableFuture
				.supplyAsync(() -> classRepository.getClassByPageAndKeyword(page, keyword), executionContext)
				.thenApplyAsync(list -> {
					LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
					return list;
				});
	}

	@Override
	public List<Clazz> findAll() throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		List<Clazz> result = classRepository.findAll();
		LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
		return result;
	}

	@Override
	public CompletionStage<BaseViewModel<Clazz>> findByPageAndKeyword(BaseViewModel<Clazz> baseViewModel)
			throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		return CompletableFuture.supplyAsync(() -> classRepository
				.getClassByPageAndKeyword(baseViewModel.getCurrentPage(), baseViewModel.getKeyword()), executionContext)
				.thenApply(list -> {
					int totalPage = (int) Math
							.ceil((float) classRepository.getTotalPage(baseViewModel.getKeyword()) / 10);
					baseViewModel.setListEntity(list);
					baseViewModel.setTotalPage(totalPage);
					LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
					return baseViewModel;
				});
	}

	@Override
	public CompletionStage<Clazz> getById(Integer id) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		return CompletableFuture.supplyAsync(() -> classRepository.findById(id), executionContext).thenApply(result -> {
			LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
			return result;
		});
	}

	@Override
	public void update(Clazz clazz) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		Ebean.beginTransaction();
		try {
			classRepository.updateClass(clazz);
			Ebean.commitTransaction();
		} catch (Exception e) {
			Ebean.rollbackTransaction();
			throw e;
		} finally {
			Ebean.endTransaction();
			LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public CompletionStage<Boolean> insertClass(Clazz clazz) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		return CompletableFuture.supplyAsync(() -> classRepository.findByName(clazz.getName()), executionContext)
				.thenApply(classResult -> {
					if (classResult != null) {
						return false;
					}
					Ebean.beginTransaction();
					try{
						Ebean.commitTransaction();
						boolean result = classRepository.insertClass(clazz);
						return result;
					} catch (Exception e) {
						Ebean.rollbackTransaction();
						throw e;
					} finally{
						Ebean.endTransaction();
						LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
					}
					
				});
	}

	@Override
	public CompletionStage<Boolean> deleteClass(Integer id) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		return CompletableFuture.supplyAsync(() -> classRepository.findById(id),  executionContext).thenApply(classResult -> {
					if (classResult == null) {
						return false;
					}
					Ebean.beginTransaction();
					try{
						boolean result = classRepository.deleteClass(classResult);
						Ebean.commitTransaction();
						return result;
					}catch (Exception e) {
						Ebean.rollbackTransaction();
						throw e;
					}finally {
						Ebean.endTransaction();
						LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
					}
				});
	}
}
