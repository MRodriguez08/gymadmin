(function(){
  'use strict';

  angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('plan.update', {
                parent: 'plan',
                url: '/updatePlan/:id',
                data: {
                	roles: ['ROLE_ADMIN'], 
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
                        $translatePartialLoader.addPart('plan_update');
                        return $translate.refresh();
                    }]
                }
            });
    });

})();