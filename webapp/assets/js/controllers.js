
angular.module('GymAdminApp.controllers', [])
	.controller('PlanListCtrl', function ($scope) {
  $scope.plans = [
    {'name': 'Nexus S',
     'id' : 1},
    {'name': 'Motorola XOOM™ with Wi-Fi',
     'id' : 2},
    {'name': 'MOTOROLA XOOM™',
     'id' : 3}
  ];
});