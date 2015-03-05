
package com.gymadmin.business;

import com.gymadmin.business.impl.CustomerBusinessImpl;
import com.gymadmin.business.impl.PaymentMethodBusinessImpl;
import com.gymadmin.business.impl.PlanBusinessImpl;

public class BusinessFactory {
    
    public static PlanBusiness getPlanBusiness(){
        return new PlanBusinessImpl();
    }
    
    public static CustomerBusiness getCustomerBusiness(){
        return new CustomerBusinessImpl();
    }
    
    public static PaymentMethodBusiness getPaymentMethodBusiness(){
        return new PaymentMethodBusinessImpl();
    }
    
}
