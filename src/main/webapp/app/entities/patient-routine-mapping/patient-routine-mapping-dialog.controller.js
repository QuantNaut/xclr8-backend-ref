(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('PatientRoutineMappingDialogController', PatientRoutineMappingDialogController);

    PatientRoutineMappingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PatientRoutineMapping'];

    function PatientRoutineMappingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PatientRoutineMapping) {
        var vm = this;

        vm.patientRoutineMapping = entity;
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
            if (vm.patientRoutineMapping.id !== null) {
                PatientRoutineMapping.update(vm.patientRoutineMapping, onSaveSuccess, onSaveError);
            } else {
                PatientRoutineMapping.save(vm.patientRoutineMapping, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclr8App:patientRoutineMappingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdDate = false;
        vm.datePickerOpenStatus.modifiedDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
