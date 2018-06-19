(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('UserDeviceTokenMappingController', UserDeviceTokenMappingController);

    UserDeviceTokenMappingController.$inject = ['$scope', '$state', 'UserDeviceTokenMapping'];

    function UserDeviceTokenMappingController ($scope, $state, UserDeviceTokenMapping) {
        var vm = this;

        vm.userDeviceTokenMappings = [];

        loadAll();

        function loadAll() {
            UserDeviceTokenMapping.query(function(result) {
                vm.userDeviceTokenMappings = result;
                vm.searchQuery = null;
            });
        }
    }
})();
