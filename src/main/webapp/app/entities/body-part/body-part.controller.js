(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('BodyPartController', BodyPartController);

    BodyPartController.$inject = ['$scope', '$state', 'BodyPart'];

    function BodyPartController ($scope, $state, BodyPart) {
        var vm = this;

        vm.bodyParts = [];

        loadAll();

        function loadAll() {
            BodyPart.query(function(result) {
                vm.bodyParts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
