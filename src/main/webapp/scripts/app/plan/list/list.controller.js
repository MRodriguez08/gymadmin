'use strict';

angular.module('gymAdminApp')
    .controller('PlanController', function ($rootScope, $scope, $state, $timeout, PlanService) {
    	$scope.plans = {};
    	$scope.refresh = function () {        	
            $scope.updatingPlans = true;
            $scope.errorMessage = '';
            PlanService.getAll().then(function (response) {
                $scope.plans = response;
                $scope.updatingPlans = false;
            }, function (response) {
            	if (response.status == 401){
            		$scope.errorMessage = 'Maria Noel';
            		$state.go('error' , {errorMessage : 'asdassa'});
            		
            	} else {            		
            		$scope.plans = response.data;
                    $scope.updatingPlans = false;
            	}                
            });
        };
        $scope.refresh();
    });
