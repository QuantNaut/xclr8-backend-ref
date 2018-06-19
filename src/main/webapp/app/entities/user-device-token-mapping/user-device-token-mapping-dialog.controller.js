(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('UserDeviceTokenMappingDialogController', UserDeviceTokenMappingDialogController);

    UserDeviceTokenMappingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserDeviceTokenMapping'];

    function UserDeviceTokenMappingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserDeviceTokenMapping) {
        var vm = this;

        vm.userDeviceTokenMapping = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.userDeviceTokenMapping.id !== null) {
                UserDeviceTokenMapping.update(vm.userDeviceTokenMapping, onSaveSuccess, onSaveError);
            } else {
                UserDeviceTokenMapping.save(vm.userDeviceTokenMapping, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclr8App:userDeviceTokenMappingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdDate = false;
        vm.datePickerOpenStatus.updatedDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
