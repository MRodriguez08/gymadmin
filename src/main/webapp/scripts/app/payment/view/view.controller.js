(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('ViewPaymentController', ['$scope', '$log', '$state', '$stateParams', 'PaymentService', function ( $scope, $log, $state, $stateParams, PaymentService) {
    		
    	$scope.error = false;
    	$scope.succes = false;
    	$scope.processing = true;
    	$scope.planModel = {};
    	
    	try {
    		PaymentService.get({id : $stateParams.id}).then(function (response) {
    			$scope.model = response;
    		}).catch(function(response) {
    			throw $translate.instant('global.messages.error.internalServerError');
    			$scope.processing = false;
            });
		} catch (e) {
			alert(e);
		}
    	
        
    }]);
})() ;