
/**
 * GymAdmin frontend logic
 */
(function(){
		
	var app = angular.module('gymAdmin', ['ngRoute']);
	
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
        })
        // route for the home page
        .when('/plans', {
            templateUrl : 'pages/plans.html',
            controller  : 'planController'
        })
        // route for the home page
        .when('/login', {
            templateUrl : 'pages/login.html',
            controller  : 'navigationController'
        }).otherwise('/');        
    });
	
	app.controller('navigationController',

	  function($rootScope, $scope, $http, $location) {

	  var authenticate = function(credentials, callback) {

	    var headers = credentials ? {authorization : "Basic "
	        + btoa(credentials.username + ":" + credentials.password)
	    } : {};

	    $http.get('/user', {headers : headers}).success(function(data) {
	      if (data.name) {
	        $rootScope.authenticated = true;
	      } else {
	        $rootScope.authenticated = false;
	      }
	      callback && callback();
	    }).error(function() {
	      $rootScope.authenticated = false;
	      callback && callback();
	    });

	  }

	  authenticate();
	  $scope.credentials = {};
	  $scope.login = function() {
	      authenticate($scope.credentials, function() {
	        if ($rootScope.authenticated) {
	          $location.path("/");
	          $scope.error = false;
	        } else {
	          $location.path("/login");
	          $scope.error = true;
	        }
	      });
	  };
	  $scope.logout = function() {
		  $http.post('logout', {}).success(function() {
		    $rootScope.authenticated = false;
		    $location.path("/");
		  }).error(function(data) {
		    $rootScope.authenticated = false;
		  });
		}
	});
	
	// create the controller and inject Angular's $scope
	app.controller('mainController', function($scope) {

        // create a message to display in our view
        $scope.message = 'Everyone come and see how good I look!';
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
