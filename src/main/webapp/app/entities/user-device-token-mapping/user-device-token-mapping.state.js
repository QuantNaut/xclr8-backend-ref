(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-device-token-mapping', {
            parent: 'entity',
            url: '/user-device-token-mapping',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserDeviceTokenMappings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-device-token-mapping/user-device-token-mappings.html',
                    controller: 'UserDeviceTokenMappingController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('user-device-token-mapping-detail', {
            parent: 'entity',
            url: '/user-device-token-mapping/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserDeviceTokenMapping'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-device-token-mapping/user-device-token-mapping-detail.html',
                    controller: 'UserDeviceTokenMappingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'UserDeviceTokenMapping', function($stateParams, UserDeviceTokenMapping) {
                    return UserDeviceTokenMapping.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-device-token-mapping',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-device-token-mapping-detail.edit', {
            parent: 'user-device-token-mapping-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-device-token-mapping/user-device-token-mapping-dialog.html',
                    controller: 'UserDeviceTokenMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserDeviceTokenMapping', function(UserDeviceTokenMapping) {
                            return UserDeviceTokenMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-device-token-mapping.new', {
            parent: 'user-device-token-mapping',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-device-token-mapping/user-device-token-mapping-dialog.html',
                    controller: 'UserDeviceTokenMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                deviceToken: null,
                                userId: null,
                                deviceType: null,
                                createdDate: null,
                                updatedDate: null,
                                isActive: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-device-token-mapping', null, { reload: 'user-device-token-mapping' });
                }, function() {
                    $state.go('user-device-token-mapping');
                });
            }]
        })
        .state('user-device-token-mapping.edit', {
            parent: 'user-device-token-mapping',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-device-token-mapping/user-device-token-mapping-dialog.html',
                    controller: 'UserDeviceTokenMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserDeviceTokenMapping', function(UserDeviceTokenMapping) {
                            return UserDeviceTokenMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-device-token-mapping', null, { reload: 'user-device-token-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-device-token-mapping.delete', {
            parent: 'user-device-token-mapping',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-device-token-mapping/user-device-token-mapping-delete-dialog.html',
                    controller: 'UserDeviceTokenMappingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserDeviceTokenMapping', function(UserDeviceTokenMapping) {
                            return UserDeviceTokenMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-device-token-mapping', null, { reload: 'user-device-token-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
