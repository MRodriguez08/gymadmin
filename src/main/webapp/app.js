
/**
 * GymAdmin frontend logic
 */
(function(){
		
	var app = angular.module('gymAdmin', ['ngRoute']);
	
	// create the controller and inject Angular's $scope
	app.controller('mainController', function($scope) {

        // create a message to display in our view
        $scope.message = 'Everyone come and see how good I look!';
    });
	
	
	// configure our routes
	app.config(function($routeProvider) {
        $routeProvider
        
        //route for the contact page
        .when('/payments', {
            templateUrl : 'pages/payments.html',
            controller  : 'paymentController'
        })
        
        // route for the home page
        .when('/plans', {
            templateUrl : 'pages/plans.html',
            controller  : 'planController'
        })

        // route for the about page
        .when('/customers', {
            templateUrl : 'pages/customers.html',
            controller  : 'customerController'
        });        
    });

    // create the controller and inject Angular's $scope
    app.controller('planController', function($scope) {
        // create a message to display in our view
        $scope.message = 'Plansssssss';
    });

    app.controller('paymentController', function($scope) {
        $scope.message = 'Paymentsssss';
    });

    app.controller('customerController', function($scope) {
        $scope.message = 'Customerssss';
    });
	
}());
