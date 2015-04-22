(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('ListCustomerController', ['$scope', '$state', '$log', 'CustomerService', function ( $scope, $state, $log, CustomerService) {
    	
    	/**
    	 * Update action
    	 */
    	$scope.delete = function( id ) {
    		
    		bootbox.confirm("Are you sure?", function(result) {
    			if (result){
    				CustomerService.delete({id : id}).then(function (response) {
    	    			bootbox.dialog({
    	    				  message: "Plan eliminado con exito",
    	    				  title: "Planes",
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
	        enableSorting: true,
	        columnDefs: [
	          { displayName:'Id', field: 'id', maxWidth : 20 },
	          { displayName:'Nombre', field: 'name', minWidth : 80},
	          { displayName:'Apellido', field: 'surname', minWidth : 80 },
	          { displayName:'Plan', field: 'currentPlan.name', minWidth : 160},
	          { name: ' ', enableFiltering: false, enableSorting: false,enableHiding: false, cellTemplate:'<span class="glyphicon glyphicon-pencil" aria-hidden="true" ng-click="grid.appScope.go(\'customer.update\',row.entity.id)"></span>', maxWidth : 5 },  
	          { name: '  ', enableFiltering: false, enableSorting: false,enableHiding: false, cellTemplate:'<span class="glyphicon glyphicon-remove" aria-hidden="true" ng-click="grid.appScope.delete(row.entity.id)"></span>', maxWidth : 5  }
	        ],
	        data: 'plansList',
        };
    	
    	
    	
    	/**
    	 * Refresh grid action
    	 */
    	$scope.refresh = function () {        	
            $scope.updatingPlans = true;
            $scope.errorMessage = '';
            CustomerService.getAll({} , function (response) {
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