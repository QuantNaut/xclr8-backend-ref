(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('NotesDialogController', NotesDialogController);

    NotesDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Notes'];

    function NotesDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Notes) {
        var vm = this;

        vm.notes = entity;
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
            if (vm.notes.id !== null) {
                Notes.update(vm.notes, onSaveSuccess, onSaveError);
            } else {
                Notes.save(vm.notes, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclr8App:notesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
