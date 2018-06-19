(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('PatientBookingDeleteController',PatientBookingDeleteController);

    PatientBookingDeleteController.$inject = ['$uibModalInstance', 'entity', 'PatientBooking'];

    function PatientBookingDeleteController($uibModalInstance, entity, PatientBooking) {
        var vm = this;

        vm.patientBooking = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PatientBooking.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
