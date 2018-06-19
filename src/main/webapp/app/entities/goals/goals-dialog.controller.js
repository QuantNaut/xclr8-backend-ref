(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('GoalsDialogController', GoalsDialogController);

    GoalsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Goals'];

    function GoalsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Goals) {
        var vm = this;

        vm.goals = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.goals.id !== null) {
                Goals.update(vm.goals, onSaveSuccess, onSaveError);
            } else {
                Goals.save(vm.goals, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclr8App:goalsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
