(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('RoutineDeleteController',RoutineDeleteController);

    RoutineDeleteController.$inject = ['$uibModalInstance', 'entity', 'Routine'];

    function RoutineDeleteController($uibModalInstance, entity, Routine) {
        var vm = this;

        vm.routine = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Routine.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
