'use strict';

angular.module('gymAdminApp')
    .controller('PlanController', function ($rootScope, $scope, $state, $timeout, PlanService) {
    	    	   	
    	$scope.plansList = '';
    	$scope.gridOptions = {
    	        enableSorting: true,
    	        columnDefs: [
    	          { displayName:'Id', field: 'id', width:100 },
    	          { displayName:'Nombre', field: 'name', width:150 },
    	          { displayName:'Descripcion', field: 'description', width:150 },
    	          { displayName:'Costo', field: 'cost', width:100 }
    	        ],
    	        data: 'plansList',
            };
    	$scope.refresh = function () {        	
            $scope.updatingPlans = true;
            $scope.errorMessage = '';
            PlanService.getAll().then(function (response) {
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
    });
