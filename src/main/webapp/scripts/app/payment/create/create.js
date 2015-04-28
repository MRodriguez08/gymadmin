(function(){
  'use strict';

  angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('payment.create', {
                parent: 'payment',
                url: '/createPayment',
                data: {
                	roles: ['ROLE_ADMIN'], 
                    pageTitle: 'payment.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/payment/create/create.html',
                        controller: 'CreatePaymentController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('payment');
                        return $translate.refresh();
                    }]
                }
            });
    });

})();