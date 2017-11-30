package bean;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseViewModel.
 *
 * @param <T> the generic type
 */
public class BaseViewModel<T> {
	
	/** The keyword. */
	private String keyword;
	
	/** The current page. */
	private Integer currentPage;
	
	/** The total page. */
	private Integer totalPage;
	
	/** The list entity. */
	private List<T> listEntity;
	
	/**
	 * Gets the keyword.
	 *
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	
	/**
	 * Sets the keyword.
	 *
	 * @param keyword the new keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * Sets the current page.
	 *
	 * @param currentPage the new current page
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * Gets the total page.
	 *
	 * @return the total page
	 */
	public Integer getTotalPage() {
		return totalPage;
	}
	
	/**
	 * Sets the total page.
	 *
	 * @param totalPage the new total page
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	/**
	 * Gets the list entity.
	 *
	 * @return the list entity
	 */
	public List<T> getListEntity() {
		return listEntity;
	}
	
	/**
	 * Sets the list entity.
	 *
	 * @param listEntity the new list entity
	 */
	public void setListEntity(List<T> listEntity) {
		this.listEntity = listEntity;
	}
	
}
