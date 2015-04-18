(function(){
  'use strict';

  angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('createPlan', {
                parent: 'plan',
                url: '/createPlan',
                data: {
                    roles: [], 
                    pageTitle: 'plan.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/plan/create/create.html',
                        controller: 'CreatePlanController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('plan_create');
                        return $translate.refresh();
                    }]
                }
            });
    });

})();