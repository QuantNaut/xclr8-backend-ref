(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('BodySectionController', BodySectionController);

    BodySectionController.$inject = ['$scope', '$state', 'BodySection'];

    function BodySectionController ($scope, $state, BodySection) {
        var vm = this;

        vm.bodySections = [];

        loadAll();

        function loadAll() {
            BodySection.query(function(result) {
                vm.bodySections = result;
                vm.searchQuery = null;
            });
        }
    }
})();
