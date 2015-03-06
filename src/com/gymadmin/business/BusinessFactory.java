
package com.gymadmin.business;

import com.gymadmin.business.impl.CustomerBusinessImpl;
import com.gymadmin.business.impl.PaymentMethodBusinessImpl;
import com.gymadmin.business.impl.PlanServiceImpl;

public class BusinessFactory {
    
    public static PlanService getPlanBusiness(){
        return new PlanServiceImpl();
    }
    
    public static CustomerBusiness getCustomerBusiness(){
        return new CustomerBusinessImpl();
    }
    
    public static PaymentMethodBusiness getPaymentMethodBusiness(){
        return new PaymentMethodBusinessImpl();
    }
    
}
