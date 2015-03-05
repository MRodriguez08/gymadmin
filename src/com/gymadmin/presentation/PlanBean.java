package com.gymadmin.presentation;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.gymadmin.business.BusinessFactory;
import com.gymadmin.business.PlanBusiness;
import com.gymadmin.persistence.entities.PlanEntity;
 
 
@ManagedBean
@SessionScoped
public class PlanBean {
	
	public PlanBusiness planBusiness;
	private HashMap<String, String> filters = new HashMap<String, String>();
	
    private List<PlanEntity> plans = new ArrayList<PlanEntity>();
 
    public PlanBean(){
    	planBusiness = BusinessFactory.getPlanBusiness();
    }
 
    @PostConstruct
    public void filter(){
    	filters.put("name", "");
    	filters.put("description", "");
        plans = planBusiness.findAll();
    }

	public List<PlanEntity> getPlans() {
		return plans;
	}

	public void setPlans(List<PlanEntity> plans) {
		this.plans = plans;
	}
	
}