(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('ListPlanController', ['$scope', '$state', '$log', '$translate', 'PlanService', function ( $scope, $state, $log, $translate, PlanService) {
    	
    	/**
    	 * Update action
    	 */
    	$scope.delete = function( id ) {    		
    		bootbox.confirm({
    			message : $translate.instant('plan.messages.confirmation.deletion'),
    			buttons: {
    				confirm: {
    					label: $translate.instant('global.buttons.confirm'),
    				},
    				cancel: {
    					label: $translate.instant('global.buttons.cancel'),
    				}
    			},
    			callback: function(result){
    				if (result){
    					$scope.deleteCallback(id);
    				}
    			}
    		});    
        };
        
        /**
         * Deletion callback
         */
        $scope.deleteCallback = function(id){
			PlanService.delete({id : id}).then(function (response) {
    			bootbox.dialog({
    				  message: $translate.instant('plan.messages.success.delete'),
    				  title: $translate.instant('global.menu.plans.main'),
    				  buttons: {
    				    success: {
    				      label: $translate.instant('global.buttons.accept'),
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
	    		    	alert($translate.instant('global.messages.error.internalServerError'));
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
	          { displayName: $translate.instant('plan.grid.id'), field: 'id', maxWidth : 80 },
	          { displayName: $translate.instant('plan.grid.name') , field: 'name'},
	          { displayName: $translate.instant('plan.grid.description'), field: 'description' },
	          { displayName: $translate.instant('plan.grid.cost'), field: 'cost', maxWidth : 80},
	          { name: ' ', enableFiltering: false, enableSorting: false,enableHiding: false, cellTemplate:'<span title=' + $translate.instant('global.tooltip.view') + ' class="grid-action-glyphicon glyphicon glyphicon-search" aria-hidden="true" ng-click="grid.appScope.go(\'plan.view\',row.entity.id)"></span>', maxWidth : 20 },
	          { name: '  ', enableFiltering: false, enableSorting: false,enableHiding: false, cellTemplate:'<span title=' + $translate.instant('global.tooltip.update') + ' class="grid-action-glyphicon glyphicon glyphicon-pencil" aria-hidden="true" ng-click="grid.appScope.go(\'plan.update\',row.entity.id)"></span>', maxWidth : 20 },  
	          { name: '   ', enableFiltering: false, enableSorting: false,enableHiding: false, cellTemplate:'<span title=' + $translate.instant('global.tooltip.delete') + ' class=" grid-action-glyphicon glyphicon glyphicon-remove" aria-hidden="true" ng-click="grid.appScope.delete(row.entity.id)"></span>', maxWidth : 20  }
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