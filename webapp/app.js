
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
        // route for the home page
        .when('/', {
            templateUrl : 'pages/plan.html',
            controller  : 'planController'
        })

        // route for the about page
        .when('/about', {
            templateUrl : 'pages/plan.html',
            controller  : 'aboutController'
        })

        // route for the contact page
        .when('/contact', {
            templateUrl : 'pages/plan.html',
            controller  : 'contactController'
        });
    });

    // create the controller and inject Angular's $scope
    app.controller('planController', function($scope) {
        // create a message to display in our view
        $scope.message = 'Plans!!';
    });

    app.controller('aboutController', function($scope) {
        $scope.message = 'Look! I am an about page.';
    });

    app.controller('contactController', function($scope) {
        $scope.message = 'Contact us! JK. This is just a demo.';
    });
	
}());
