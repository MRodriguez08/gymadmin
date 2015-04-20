(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('ListPlanController', ['$scope', '$log', 'PlanService', function ( $scope, $log, PlanService) {
    	
    	/**
    	 * Update action
    	 */
    	$scope.delete = function( ) {
            alert('updating plan...');
        };
        
    	$scope.plansList = '';
    	$scope.gridScope = $scope;
    	$scope.gridOptions = {
	        enableSorting: true,
	        columnDefs: [
	          { displayName:'Id', field: 'id', maxWidth : 80 },
	          { displayName:'Nombre', field: 'name'},
	          { displayName:'Descripcion', field: 'description' },
	          { displayName:'Costo', field: 'cost', maxWidth : 80},
	          { name: ' ', cellTemplate:'<span class="glyphicon glyphicon-remove" aria-hidden="true" ng-click="grid.appScope.delete()"></span>', maxWidth : 20 },  
	          { name: '  ', cellTemplate:'<span class="glyphicon glyphicon-pencil" aria-hidden="true" ng-click="grid.appScope.delete()"></span>', maxWidth : 20  }
	        ],
	        data: 'plansList',
        };
    	
    	
    	
    	/**
    	 * Refresh grid action
    	 */
    	$scope.refresh = function () {        	
            $scope.updatingPlans = true;
            $scope.errorMessage = '';
            PlanService.getAll({} , function (response) {
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