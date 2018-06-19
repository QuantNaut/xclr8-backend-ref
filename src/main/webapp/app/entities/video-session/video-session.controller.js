(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('VideoSessionController', VideoSessionController);

    VideoSessionController.$inject = ['$scope', '$state', 'VideoSession'];

    function VideoSessionController ($scope, $state, VideoSession) {
        var vm = this;

        vm.videoSessions = [];

        loadAll();

        function loadAll() {
            VideoSession.query(function(result) {
                vm.videoSessions = result;
                vm.searchQuery = null;
            });
        }
    }
})();
