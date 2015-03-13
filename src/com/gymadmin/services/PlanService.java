package com.gymadmin.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.gymadmin.business.PlanBusiness;
import com.gymadmin.persistence.dao.PlanDao;
import com.gymadmin.persistence.entities.PlanEntity;

@Path("/plan")
public class PlanService {

	@Autowired
	private PlanBusiness planBusiness;
	
	
	public PlanService(PlanBusiness planBusiness) {
		this.planBusiness = planBusiness;
	}	
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		List<PlanEntity> entities = planBusiness.findAll();
 
		return Response.status(200).entity(entities).build();
 
	}
	
}
