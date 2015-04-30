(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('UpdateCustomerController', ['$scope', '$log', '$state', '$stateParams', 'CustomerService', 'PlanService', function ( $scope, $log, $state, $stateParams, CustomerService, PlanService) {
    	
    	var i = $stateParams.id;    	
    	$scope.error = false;
    	$scope.succes = false;
    	$scope.processing = true;
    	$scope.planModel = {};
    	
    	$scope.getImage = function(img){
    		return img != null ? 'images/customers/' + img : 'images/unknown-user.png';
    	}
    	
    	PlanService.getAll({} , function (response) {
            $scope.plansList = response;
            CustomerService.get({id : $stateParams.id}).then(function (response) {
            	$scope.avatar = $scope.getImage(response.image);
    			$scope.model = response;
    			$scope.currentPlan = $scope.getCurrentPlan();
    		}).catch(function(response) {
    			alert('Error interno de la aplicacion');
    			$scope.processing = false;
            });   
            
        }, function (response) {
        	alert('Error recuperando planes');             
        });
    	
    	 	
    	
    	$scope.update = function () {
    		
    		if ($scope.profilePicture != null){
    			$scope.uploadImage(function(){
    				
    				$scope.model.currentPlan = $scope.currentPlan;
    	    		CustomerService.update($scope.model).then(function (response) {
    	    			$scope.avatar = $scope.getImage(response.image);
    	    			$scope.model = response;
    	    			bootbox.dialog({
    	    				  message: "Cliente modificado con exito",
    	    				  title: "Clientes",
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
    			});
    		}    		
        };
        
        $scope.getCurrentPlan = function () {
        	for (var i = 0; i < $scope.plansList.length; i++){
        		if ($scope.plansList[i].id == $scope.model.currentPlan.id)	
        			return $scope.plansList[i];
        	}
        }
        
        $scope.uploadImage = function(callback){
        	
        	CustomerService.uploadImage($scope.profilePicture, 
				function (evt) {
		            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
		            console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
	    		},
	    		function (data, status, headers, config) {	    			 
	        		console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
	        		callback();        		
	    		});	
        };
        
    }]);
})() ;