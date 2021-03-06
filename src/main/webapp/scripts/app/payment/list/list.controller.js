(function() { 
	'use strict';

	angular.module('gymAdminApp')
    .controller('ListPaymentController', ['$scope', '$state', '$log', '$translate', 'PaymentService', 'PaymentStateService', function ( $scope, $state, $log, $translate, PaymentService, PaymentStateService) {
    	
    	PaymentStateService.getAll({}).then(function (response) {
			$scope.paymentStatesList = response;
		}).catch(function(response) {
			alert('Error interno de la aplicacion');
			$scope.processing = false;
        });
    	
    	/**
    	 * Update action
    	 */
    	$scope.delete = function( id ) {
    		bootbox.confirm({
    			message : $translate.instant('payment.messages.confirmation.delete'),
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
    	 * Pay action
    	 */
        $scope.pay = function( rowItem ){
        	bootbox.confirm({
    			message : $translate.instant('payment.messages.confirmation.pay'),
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
    					$scope.payCallback(rowItem);
    				}
    			}
    		});
        };
        
        /**
         * Deletion callback
         */
        $scope.deleteCallback = function(id){
        	PaymentService.delete({id : id}).then(function (response) {
    			bootbox.dialog({
    				  message: $translate.instant('payment.messages.success.delete'),
    				  title: $translate.instant('global.menu.payments.main'),
    				  buttons: {
    				    success: {
    				      label: $translate.instant('global.buttons.accept'),
    				      className: "btn-primary",
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
         * Payment callback
         */
        $scope.payCallback = function(rowItem){
        	PaymentService.update(rowItem).then(function (response) {
    			$scope.model = response;
    			bootbox.dialog({
    				  message: $translate.instant('payment.messages.success.pay'),
    				  title: $translate.instant('payment.title.pay'),
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
        }
        
        
        
    	/**
    	 * go action
    	 */
    	$scope.go = function( rt , p1 ) {
    		$state.go(rt , {id: p1});
        };
        
    	$scope.paymentsList = '';
    	$scope.gridScope = $scope;
    	$scope.gridOptions = {
    		enableFiltering: true,
    		rowTemplate: '<div ng-class="{\'pending\':row.entity.state.id===1,  \'about-to-overdue\':row.entity.state.id===2,  \'overdue\':row.entity.state.id===4}" <div ng-repeat="col in colContainer.renderedColumns track by col.colDef.name"  class="ui-grid-cell" ui-grid-cell></div></div>',
	        enableSorting: true,
	        columnDefs: [
	          { displayName: $translate.instant('payment.grid.id'), field: 'id', maxWidth : 20 },	          
	          { displayName: $translate.instant('payment.grid.customer.name') , field: 'customer.name'},
	          { displayName: $translate.instant('payment.grid.state.name') , field: 'state.name'},
	          { displayName: $translate.instant('payment.grid.paymentDueDate') , field: 'paymentDueDate', cellFilter: "date:'dd/MM/yyyy HH:mm'" },
	          { displayName: $translate.instant('payment.grid.validCost') , field: 'validCost'}, 
	          { name: ' ', enableFiltering: false, enableSorting: false,enableHiding: false, cellTemplate:'<span title=' + $translate.instant('global.tooltip.view') + ' class="grid-action-glyphicon glyphicon glyphicon-search" aria-hidden="true" ng-click="grid.appScope.go(\'payment.view\',row.entity.id)"></span>', maxWidth : 5 },
	          { name: '  ', enableFiltering: false, enableSorting: false,enableHiding: false, cellTemplate:'<span title=' + $translate.instant('global.tooltip.pay') + ' class="grid-action-glyphicon glyphicon glyphicon-usd" aria-hidden="true" ng-click="grid.appScope.pay(row.entity)"></span>', maxWidth : 20 },  
	          { name: '   ', enableFiltering: false, enableSorting: false,enableHiding: false, cellTemplate:'<span  title=' + $translate.instant('global.tooltip.cancel') + ' class=" grid-action-glyphicon glyphicon glyphicon-remove" aria-hidden="true" ng-click="grid.appScope.delete(row.entity.id)"></span>', maxWidth : 20  }
	        ],
	        data: 'paymentsList',
        };
    	
    	
    	
    	/**
    	 * Refresh grid action
    	 */
    	$scope.refresh = function () {        	
            $scope.updatingData = true;
            $scope.errorMessage = '';
            PaymentService.getAll($scope.filterModel , function (response) {
                $scope.paymentsList = response;
                $scope.updatingData = false;
            }, function (response) {
            	if (response.status == 401){
            		alert(response.data);            		
            	} else {            		
            		$scope.paymentsList = response.data;
                    $scope.updatingData = false;
            	}                
            });
    		console.log($scope.paymentsList);
        };
        $scope.refresh();    
    }]);
})() ;