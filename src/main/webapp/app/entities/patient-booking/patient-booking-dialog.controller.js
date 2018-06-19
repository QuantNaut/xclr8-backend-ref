(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('PatientBookingDialogController', PatientBookingDialogController);

    PatientBookingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PatientBooking'];

    function PatientBookingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PatientBooking) {
        var vm = this;

        vm.patientBooking = entity;
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
            if (vm.patientBooking.id !== null) {
                PatientBooking.update(vm.patientBooking, onSaveSuccess, onSaveError);
            } else {
                PatientBooking.save(vm.patientBooking, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclr8App:patientBookingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.bookingDateAndTime = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
