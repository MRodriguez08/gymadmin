(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('ListPaymentController', ['$scope', '$state', '$log', '$translate', 'PaymentService', function ( $scope, $state, $log, $translate, PaymentService) {
    	
    	/**
    	 * Update action
    	 */
    	$scope.delete = function( id ) {
    		
    		bootbox.confirm($translate.instant('payment.messages.confirmation.deletion'), function(result) {
    			if (result){
    				PaymentService.delete({id : id}).then(function (response) {
    	    			bootbox.dialog({
    	    				  message: $translate.instant('payment.messages.success.delete'),
    	    				  title: "Pagos",
    	    				  buttons: {
    	    				    success: {
    	    				      label: "Aceptar",
    	    				      className: "btn-success",
    	    				      callback: function() {		
    	    				    	  $state.reload();
    	    				    	  $scope.planModel = {};
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
    			}
    		});         
    		
    		
    		
        };
        
    	/**
    	 * Update action
    	 */
    	$scope.go = function( rt , p1 ) {
    		$state.go(rt , {id: p1});
        };
        
    	$scope.plansList = '';
    	$scope.gridScope = $scope;
    	$scope.gridOptions = {
    		enableFiltering: true,
    		rowTemplate: '<div ng-class="{\'pending\':row.entity.state.id===1,  \'about-to-overdue\':row.entity.state.id===3,  \'overdue\':row.entity.state.id===4}" <div ng-repeat="col in colContainer.renderedColumns track by col.colDef.name"  class="ui-grid-cell" ui-grid-cell></div></div>',
	        enableSorting: true,
	        columnDefs: [
	          { displayName: $translate.instant('payment.grid.id'), field: 'id', maxWidth : 20 },	          
	          { displayName: $translate.instant('payment.grid.customer.name') , field: 'customer.name'},
	          { displayName: $translate.instant('payment.grid.state.name') , field: 'state.name'},
	          { displayName: $translate.instant('payment.grid.paymentDueDate') , field: 'paymentDueDate', cellFilter: "date:'dd/MM/yyyy HH:mm'" },
	          { displayName: $translate.instant('payment.grid.validCost') , field: 'validCost'}, 
	          { name: ' ', enableFiltering: false, cellTemplate:'<span class="grid-action-glyphicon glyphicon glyphicon-pencil" aria-hidden="true" ng-click="grid.appScope.go(\'plan.update\',row.entity.id)"></span>', maxWidth : 20 },  
	          { name: '  ', enableFiltering: false, cellTemplate:'<span class=" grid-action-glyphicon glyphicon glyphicon-remove" aria-hidden="true" ng-click="grid.appScope.delete(row.entity.id)"></span>', maxWidth : 20  }
	        ],
	        data: 'plansList',
        };
    	
    	
    	
    	/**
    	 * Refresh grid action
    	 */
    	$scope.refresh = function () {        	
            $scope.updatingPlans = true;
            $scope.errorMessage = '';
            PaymentService.getAll({} , function (response) {
                $scope.plansList = response;
                $scope.updatingPlans = false;
            }, function (response) {
            	if (response.status == 401){
            		alert(response.data);            		
            	} else {            		
            		$scope.plansList = response.data;
                    $scope.updatingPlans = false;
            	}                
            });
        };
        $scope.refresh();    
    }]);
})() ;