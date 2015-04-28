(function(){
  'use strict';

  angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('payment.update', {
                parent: 'plan',
                url: '/updatePayment/:id',
                data: {
                	roles: [ 'ROLE_ADMIN' , 'ROLE_USER'],
                    pageTitle: 'plan.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/plan/update/update.html',
                        controller: 'UpdatePlanController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('plan');
                        return $translate.refresh();
                    }]
                }
            });
    });

})();