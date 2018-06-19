(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('body-part', {
            parent: 'entity',
            url: '/body-part',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'BodyParts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/body-part/body-parts.html',
                    controller: 'BodyPartController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('body-part-detail', {
            parent: 'entity',
            url: '/body-part/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'BodyPart'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/body-part/body-part-detail.html',
                    controller: 'BodyPartDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'BodyPart', function($stateParams, BodyPart) {
                    return BodyPart.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'body-part',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('body-part-detail.edit', {
            parent: 'body-part-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/body-part/body-part-dialog.html',
                    controller: 'BodyPartDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BodyPart', function(BodyPart) {
                            return BodyPart.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('body-part.new', {
            parent: 'body-part',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/body-part/body-part-dialog.html',
                    controller: 'BodyPartDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                bodyPart: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('body-part', null, { reload: 'body-part' });
                }, function() {
                    $state.go('body-part');
                });
            }]
        })
        .state('body-part.edit', {
            parent: 'body-part',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/body-part/body-part-dialog.html',
                    controller: 'BodyPartDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BodyPart', function(BodyPart) {
                            return BodyPart.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('body-part', null, { reload: 'body-part' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('body-part.delete', {
            parent: 'body-part',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/body-part/body-part-delete-dialog.html',
                    controller: 'BodyPartDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['BodyPart', function(BodyPart) {
                            return BodyPart.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('body-part', null, { reload: 'body-part' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
