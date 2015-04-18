(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('CreatePlanController', ['$scope', '$log', 'PlanService', function ( $scope, $log, PlanService) {
    	
    	$scope.planModel = {};
    	$scope.create = function () {
            console.log("Name: " + $scope.planModel.name);
        };
        
    }]);
})() ;