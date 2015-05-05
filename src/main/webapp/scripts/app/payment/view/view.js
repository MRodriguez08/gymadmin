(function(){
  'use strict';

  angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('payment.view', {
                parent: 'payment',
                url: '/viewPayment/:id',
                data: {
                	roles: [ 'ROLE_ADMIN' , 'ROLE_USER'],
                    pageTitle: 'payment.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/payment/view/view.html',
                        controller: 'ViewPaymentController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('payment');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            });
    });

})();