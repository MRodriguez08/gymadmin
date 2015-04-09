'use strict';

angular.module('gymAdminApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
