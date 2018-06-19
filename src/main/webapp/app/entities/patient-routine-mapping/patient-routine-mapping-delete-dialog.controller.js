(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('PatientRoutineMappingDeleteController',PatientRoutineMappingDeleteController);

    PatientRoutineMappingDeleteController.$inject = ['$uibModalInstance', 'entity', 'PatientRoutineMapping'];

    function PatientRoutineMappingDeleteController($uibModalInstance, entity, PatientRoutineMapping) {
        var vm = this;

        vm.patientRoutineMapping = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PatientRoutineMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
