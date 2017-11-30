package entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import io.ebean.Finder;
import io.ebean.Model;


// TODO: Auto-generated Javadoc
/**
 * The Class Student.
 */
@Entity
public class Student extends Model{
	/**
	 * The student id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/** The name. */
	@NotNull
	@Size(min = 2, max = 255)
	@Pattern(regexp = "^[a-zA-Z\\d\\p{L}]+( [a-zA-Z\\d\\p{L}]+)*$")
	private String name;

	/** The create name. */
	private String createName;

	/** The update name. */
	private String updateName;

	/** The code. */
	@NotNull
	@Size(min = 2, max = 11)
	@Pattern(regexp = "\\d+")
	private String code;
	
	/** The address. */
	@NotNull
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z\\d,.\\p{L}]+( [a-zA-Z\\d,.\\p{L}]+)*$", message = "{Pattern.student.address}")
	private String address;

	/** The average score. */
	@DecimalMin("0.0")
	@DecimalMax("10.0")
	@Digits(integer = 2, fraction = 10)
	private BigDecimal averageScore;

	/** The date of birth. */
	@Past
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;
	
	/** The is active. */
	@NotNull
	private Integer isActive;

	/** The classes. */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "student_class", joinColumns = {
			@JoinColumn(name = "studentId", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "classId", nullable = false, updatable = false) })
	private List<Clazz> classes;

	/** The Constant find. */
	public static final Finder<Integer, Student> find = new Finder<>(Student.class);
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the creates the name.
	 *
	 * @return the creates the name
	 */
	public String getCreateName() {
		return createName;
	}

	/**
	 * Sets the creates the name.
	 *
	 * @param createName the new creates the name
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * Gets the update name.
	 *
	 * @return the update name
	 */
	public String getUpdateName() {
		return updateName;
	}

	/**
	 * Sets the update name.
	 *
	 * @param updateName the new update name
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the average score.
	 *
	 * @return the average score
	 */
	public BigDecimal getAverageScore() {
		return averageScore;
	}

	/**
	 * Sets the average score.
	 *
	 * @param averageScore the new average score
	 */
	public void setAverageScore(BigDecimal averageScore) {
		this.averageScore = averageScore;
	}

	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public Integer getIsActive() {
		return isActive;
	}

	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the classes.
	 *
	 * @return the classes
	 */
	public List<Clazz> getClasses() {
		return classes;
	}

	/**
	 * Sets the classes.
	 *
	 * @param classes the new classes
	 */
	public void setClasses(List<Clazz> classes) {
		this.classes = classes;
	}
}
