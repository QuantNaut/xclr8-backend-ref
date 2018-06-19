(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('sensor-data', {
            parent: 'entity',
            url: '/sensor-data?page&sort&search',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'SensorData'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sensor-data/sensor-data.html',
                    controller: 'SensorDataController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('sensor-data-detail', {
            parent: 'entity',
            url: '/sensor-data/{id}',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'SensorData'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sensor-data/sensor-data-detail.html',
                    controller: 'SensorDataDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'SensorData', function($stateParams, SensorData) {
                    return SensorData.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'sensor-data',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('sensor-data-detail.edit', {
            parent: 'sensor-data-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sensor-data/sensor-data-dialog.html',
                    controller: 'SensorDataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SensorData', function(SensorData) {
                            return SensorData.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sensor-data.new', {
            parent: 'sensor-data',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sensor-data/sensor-data-dialog.html',
                    controller: 'SensorDataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                patientUsername: null,
                                doctorUsername: null,
                                mode: null,
                                sessionId: null,
                                routine: null,
                                createdBy: null,
                                xData: null,
                                yData: null,
                                zData: null,
                                wData: null,
                                dateAndTime: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('sensor-data', null, { reload: 'sensor-data' });
                }, function() {
                    $state.go('sensor-data');
                });
            }]
        })
        .state('sensor-data.edit', {
            parent: 'sensor-data',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sensor-data/sensor-data-dialog.html',
                    controller: 'SensorDataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SensorData', function(SensorData) {
                            return SensorData.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sensor-data', null, { reload: 'sensor-data' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sensor-data.delete', {
            parent: 'sensor-data',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sensor-data/sensor-data-delete-dialog.html',
                    controller: 'SensorDataDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['SensorData', function(SensorData) {
                            return SensorData.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sensor-data', null, { reload: 'sensor-data' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
