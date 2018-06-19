(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('VideoSessionDialogController', VideoSessionDialogController);

    VideoSessionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'VideoSession'];

    function VideoSessionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, VideoSession) {
        var vm = this;

        vm.videoSession = entity;
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
            if (vm.videoSession.id !== null) {
                VideoSession.update(vm.videoSession, onSaveSuccess, onSaveError);
            } else {
                VideoSession.save(vm.videoSession, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('xclr8App:videoSessionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
