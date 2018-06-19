(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('UserDeviceTokenMappingDetailController', UserDeviceTokenMappingDetailController);

    UserDeviceTokenMappingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserDeviceTokenMapping'];

    function UserDeviceTokenMappingDetailController($scope, $rootScope, $stateParams, previousState, entity, UserDeviceTokenMapping) {
        var vm = this;

        vm.userDeviceTokenMapping = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:userDeviceTokenMappingUpdate', function(event, result) {
            vm.userDeviceTokenMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
