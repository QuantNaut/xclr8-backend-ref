(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('VideoSessionDeleteController',VideoSessionDeleteController);

    VideoSessionDeleteController.$inject = ['$uibModalInstance', 'entity', 'VideoSession'];

    function VideoSessionDeleteController($uibModalInstance, entity, VideoSession) {
        var vm = this;

        vm.videoSession = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            VideoSession.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
