(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('GoalsDeleteController',GoalsDeleteController);

    GoalsDeleteController.$inject = ['$uibModalInstance', 'entity', 'Goals'];

    function GoalsDeleteController($uibModalInstance, entity, Goals) {
        var vm = this;

        vm.goals = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Goals.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
