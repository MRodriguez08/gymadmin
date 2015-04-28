(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('CreatePaymentController', ['$scope', '$log', '$translate', '$state', 'PaymentService', 'CustomerService', 'PaymentPlanService', function ( $scope, $log, $translate, $state, PaymentService, CustomerService, PaymentPlanService) {
    	
    	$scope.init = function(){
	    	$scope.error = false;
	    	$scope.succes = false;
	    	$scope.processing = true;
	    	$scope.model = {};
	    	$scope.model.paymentPlan = {discount : 0}
	    	$scope.model.customer = {currentPlan : {cost : 0}};
    	}
    	
    	CustomerService.getAll({} , function (response) {
            $scope.customersList = response;
        }, function (response) {
        	alert($translate.instant('payment.messages.error.retrieveCustomers'));             
        });  
    	
    	PaymentPlanService.getAll({} , function (response) {
            $scope.paymentPlansList = response;
        }, function (response) {
        	alert($translate.instant('payment.messages.error.retrievePaymentPlans'));
        });
    	
    	$scope.updateValidCost = function(){
    		$scope.model.validCost = PaymentService.getFinalCost($scope.model.customer.currentPlan.cost, $scope.model.paymentPlan.discount);
    	}
    	
    	$scope.create = function () {
    		$scope.model.plan = $scope.model.customer.currentPlan;
    		
    		
    		PaymentService.create($scope.model).then(function (response) {
    			bootbox.dialog({
    				  message: $translate.instant('payment.messages.success.create'),
    				  title: $translate.instant('payment.title.create'),
    				  buttons: {
    				    success: {
    				      label: "Aceptar",
    				      className: "btn-success",
    				      callback: function() {		
    				    	  $state.reload();
    				    	  $scope.init();
    				      }
    				    }
    				  }
    			});		    	
    		}).catch(function(response) {
    			switch(response.status) {
	    		    case 500:
	    		    	alert('Error interno de la aplicacion');
	    		        break;
	    		    case 400:
	    		    	$scope.error = true;
	    		    	$scope.errorMessage = response.data.message;
	    		        break;
	    		    default:
	    		        
	    		}
    			$scope.processing = false;
            });
    		
        };
        
        $scope.init();
        $scope.updateValidCost();
    }]);
})() ;
