(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('UserDeviceTokenMappingDeleteController',UserDeviceTokenMappingDeleteController);

    UserDeviceTokenMappingDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserDeviceTokenMapping'];

    function UserDeviceTokenMappingDeleteController($uibModalInstance, entity, UserDeviceTokenMapping) {
        var vm = this;

        vm.userDeviceTokenMapping = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserDeviceTokenMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
