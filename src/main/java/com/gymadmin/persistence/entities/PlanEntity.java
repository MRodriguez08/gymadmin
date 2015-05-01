package com.gymadmin.persistence.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "plans")
@NamedQueries( { 
	@NamedQuery(name = "PlanEntity.findByName", 
			query = "SELECT e FROM PlanEntity e " +
					"WHERE e.name = :name"),
	@NamedQuery(name = "PlanEntity.findByFilters", 
			query = "SELECT e FROM PlanEntity e " +
					"WHERE upper(e.name) LIKE :name AND upper(e.description) LIKE :description")
})
public class PlanEntity implements Serializable  {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"plan_sequence") 
	@SequenceGenerator(name = "plan_sequence", sequenceName = "plan_id_sequence", allocationSize = 1)
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 100, unique=true)
    private String name;
    
    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Float cost;
    
    @JsonIgnore
    @OneToMany(mappedBy="plan", fetch=FetchType.LAZY)
    private List<PaymentEntity> payments;
    
    @JsonIgnore
    @OneToMany(mappedBy="plan", fetch=FetchType.LAZY)
    private List<CustomerEntity> customers;
    
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public List<PaymentEntity> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentEntity> payments) {
		this.payments = payments;
	}

	public List<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	} 
	
	public String toString(){
		return this.name;
	}
    
}
