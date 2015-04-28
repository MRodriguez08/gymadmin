/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gymadmin.persistence.dao.impl;

import org.springframework.stereotype.Component;

import com.gymadmin.persistence.dao.PaymentStateDao;
import com.gymadmin.persistence.entities.PaymentStateEntity;

/**
 *
 * @author mrodriguez
 */
@Component("paymentStateDao")
public class PaymentStateDaoImpl extends DaoImpl<Integer , PaymentStateEntity> implements PaymentStateDao {
    
}
