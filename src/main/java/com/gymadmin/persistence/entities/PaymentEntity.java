package com.gymadmin.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class PaymentEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"payments_sequence") 
	@SequenceGenerator(name = "payments_sequence", sequenceName = "payments_id_sequence", allocationSize = 1)
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	private PaymentStateEntity state;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;
	
	@ManyToOne
	@JoinColumn(name = "plan_id", nullable = false)
	private PlanEntity plan;
	
	@Column(name = "valid_cost", nullable = false)
    private Float validCost;
	
	@Column(name = "payment_date", nullable = true)
    private Date paymentDate;
	
	@Column(name = "payment_due_date", nullable = false)
    private Date paymentDueDate;
	
	@ManyToOne
	@JoinColumn(name = "payment_plan_id", nullable = false)
	private PaymentPlanEntity paymentPlan;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentStateEntity getState() {
		return state;
	}

	public void setState(PaymentStateEntity state) {
		this.state = state;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public Float getValidCost() {
		return validCost;
	}

	public void setValidCost(Float validCost) {
		this.validCost = validCost;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public PlanEntity getPlan() {
		return plan;
	}

	public void setPlan(PlanEntity plan) {
		this.plan = plan;
	}

	public PaymentPlanEntity getPaymentPlan() {
		return paymentPlan;
	}

	public void setPaymentPlan(PaymentPlanEntity paymentMethod) {
		this.paymentPlan = paymentMethod;
	}
    
}
