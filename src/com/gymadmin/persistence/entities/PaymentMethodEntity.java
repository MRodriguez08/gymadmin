package com.gymadmin.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "payment_methods")
@NamedQueries( { 
	@NamedQuery(name = "PaymentMethodEntity.findByName", 
			query = "SELECT e FROM PaymentMethodEntity e " +
					"WHERE e.name = :name"),
	@NamedQuery(name = "PaymentMethodEntity.findByFilters", 
			query = "SELECT e FROM PaymentMethodEntity e " +
					"WHERE upper(e.name) LIKE :name AND upper(e.description) LIKE :description")
})
public class PaymentMethodEntity extends BaseEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"payment_method_sequence") 
	@SequenceGenerator(name = "payment_method_sequence", sequenceName = "payment_method_id_sequence", allocationSize = 1)
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 100, unique=true)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "months_count")
    private Integer monthsCount;

    @Column(name = "discount", nullable = true)
    private Integer discount;
    
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

	public Integer getMonthsCount() {
		return monthsCount;
	}

	public void setMonthsCount(Integer monthsCount) {
		this.monthsCount = monthsCount;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
}
