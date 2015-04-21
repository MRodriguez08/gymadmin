(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('ListPlanController', ['$scope', '$state', '$log', 'PlanService', function ( $scope, $state, $log, PlanService) {
    	
    	/**
    	 * Update action
    	 */
    	$scope.delete = function( ) {
            alert('updating plan...');
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
	        enableSorting: true,
	        columnDefs: [
	          { displayName:'Id', field: 'id', maxWidth : 80 },
	          { displayName:'Nombre', field: 'name'},
	          { displayName:'Descripcion', field: 'description' },
	          { displayName:'Costo', field: 'cost', maxWidth : 80},
	          { name: ' ', cellTemplate:'<span class="glyphicon glyphicon-pencil" aria-hidden="true" ng-click="grid.appScope.go(\'plan.update\',row.entity.id)"></span>', maxWidth : 20 },  
	          { name: '  ', cellTemplate:'<span class="glyphicon glyphicon-remove" aria-hidden="true" ng-click="grid.appScope.delete(row.entity.id)"></span>', maxWidth : 20  }
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