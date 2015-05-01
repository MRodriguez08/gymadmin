/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.SysParamDao;
import com.gymadmin.persistence.entities.SysParamEntity;

/**
 *
 * @author mrodriguez
 */
@Component("sysParamDao")
public class SysParamDaoImpl extends DaoImpl<String , SysParamEntity> implements SysParamDao {	
	
}
