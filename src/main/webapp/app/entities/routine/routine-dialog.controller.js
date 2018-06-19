(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('RoutineDialogController', RoutineDialogController);

    RoutineDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Routine'];

    function RoutineDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Routine) {
        var vm = this;

        vm.routine = entity;
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
            if (vm.routine.id !== null) {
                Routine.update(vm.routine, onSaveSuccess, onSaveError);
            } else {
                Routine.save(vm.routine, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclr8App:routineUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.creationDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
