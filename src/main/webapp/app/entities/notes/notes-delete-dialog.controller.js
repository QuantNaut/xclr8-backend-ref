(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('NotesDeleteController',NotesDeleteController);

    NotesDeleteController.$inject = ['$uibModalInstance', 'entity', 'Notes'];

    function NotesDeleteController($uibModalInstance, entity, Notes) {
        var vm = this;

        vm.notes = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Notes.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
