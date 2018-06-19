(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('BodySectionDeleteController',BodySectionDeleteController);

    BodySectionDeleteController.$inject = ['$uibModalInstance', 'entity', 'BodySection'];

    function BodySectionDeleteController($uibModalInstance, entity, BodySection) {
        var vm = this;

        vm.bodySection = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            BodySection.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
