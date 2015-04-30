package com.gymadmin.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customers")
@NamedQueries( { 
	@NamedQuery(name = "CustomerEntity.findByFilters", 
		query = "SELECT e FROM CustomerEntity e " +
				"WHERE upper(e.name) LIKE :name AND upper(e.surname) LIKE :surname AND upper(e.email) LIKE :email")
})
public class CustomerEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"customers_sequence") 
	@SequenceGenerator(name = "customers_sequence", sequenceName = "customers_id_sequence", allocationSize = 1)
    private Integer id;
    
	@Column(name = "name", nullable = false, length = 100)
    private String name;
	
	@Column(name = "surname", nullable = true, length = 100)
    private String surname;
	
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    
    @Column(name = "cellphone", nullable = true, length = 15)
    private String cellphone;
    
    @Column(name = "address", nullable = true, length = 100)
    private String address;
    
    @Column(name = "image", nullable = true, length = 512)
    private String image;
    
    @Column(name = "registration_date", nullable = false)
    private Long registrationDate;
    
    @JsonIgnore
    @OneToMany(mappedBy="customer", fetch=FetchType.LAZY)
    private List<PaymentEntity> payments;

    @ManyToOne
	@JoinColumn(name = "plan_id", nullable = false)
	private PlanEntity currentPlan;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PaymentEntity> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentEntity> payments) {
		this.payments = payments;
	}

	public PlanEntity getCurrentPlan() {
		return currentPlan;
	}

	public void setCurrentPlan(PlanEntity currentPlan) {
		this.currentPlan = currentPlan;
	}

	public Long getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Long registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
