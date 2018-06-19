(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('BodySectionDialogController', BodySectionDialogController);

    BodySectionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'BodySection'];

    function BodySectionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, BodySection) {
        var vm = this;

        vm.bodySection = entity;
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
            if (vm.bodySection.id !== null) {
                BodySection.update(vm.bodySection, onSaveSuccess, onSaveError);
            } else {
                BodySection.save(vm.bodySection, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclr8App:bodySectionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
