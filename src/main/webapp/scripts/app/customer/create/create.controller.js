(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('CreateCustomerController', ['$scope', '$log', '$translate', '$state', 'CustomerService', 'PlanService', 'PaymentPlanService', function ( $scope, $log, $translate, $state, CustomerService, PlanService, PaymentPlanService) {
    	
    	$scope.error = false;
    	$scope.succes = false;
    	$scope.processing = true;
    	$scope.model = {};
    	
    	PlanService.getAll({} , function (response) {
            $scope.plansList = response;
        }, function (response) {
        	alert($translate.instant('customer.messages.error.retrievePlans'));             
        });
    	
    	PaymentPlanService.getAll({} , function (response) {
            $scope.paymentPlansList = response;
        }, function (response) {
        	alert($translate.instant('customer.messages.error.retrievePaymentPlans'));
        });    	
    	
    	$scope.create = function () {
            
    		CustomerService.create($scope.model).then(function (response) {
    			bootbox.dialog({
				  message: $translate.instant('customer.messages.success.create'),
				  title: $translate.instant('customer.title.create'),
				  buttons: {
				    success: {
				      label: "Aceptar",
				      className: "btn-success",
				      callback: function() {		
				    	  $state.reload();
				    	  $scope.model = {};
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
        
    }]);
})() ;