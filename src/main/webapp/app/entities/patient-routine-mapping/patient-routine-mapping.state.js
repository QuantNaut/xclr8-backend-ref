(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('patient-routine-mapping', {
            parent: 'entity',
            url: '/patient-routine-mapping',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PatientRoutineMappings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/patient-routine-mapping/patient-routine-mappings.html',
                    controller: 'PatientRoutineMappingController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('patient-routine-mapping-detail', {
            parent: 'entity',
            url: '/patient-routine-mapping/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PatientRoutineMapping'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/patient-routine-mapping/patient-routine-mapping-detail.html',
                    controller: 'PatientRoutineMappingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PatientRoutineMapping', function($stateParams, PatientRoutineMapping) {
                    return PatientRoutineMapping.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'patient-routine-mapping',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('patient-routine-mapping-detail.edit', {
            parent: 'patient-routine-mapping-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/patient-routine-mapping/patient-routine-mapping-dialog.html',
                    controller: 'PatientRoutineMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PatientRoutineMapping', function(PatientRoutineMapping) {
                            return PatientRoutineMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('patient-routine-mapping.new', {
            parent: 'patient-routine-mapping',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/patient-routine-mapping/patient-routine-mapping-dialog.html',
                    controller: 'PatientRoutineMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                routineId: null,
                                patientId: null,
                                createdDate: null,
                                modifiedDate: null,
                                isActive: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('patient-routine-mapping', null, { reload: 'patient-routine-mapping' });
                }, function() {
                    $state.go('patient-routine-mapping');
                });
            }]
        })
        .state('patient-routine-mapping.edit', {
            parent: 'patient-routine-mapping',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/patient-routine-mapping/patient-routine-mapping-dialog.html',
                    controller: 'PatientRoutineMappingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PatientRoutineMapping', function(PatientRoutineMapping) {
                            return PatientRoutineMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('patient-routine-mapping', null, { reload: 'patient-routine-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('patient-routine-mapping.delete', {
            parent: 'patient-routine-mapping',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/patient-routine-mapping/patient-routine-mapping-delete-dialog.html',
                    controller: 'PatientRoutineMappingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PatientRoutineMapping', function(PatientRoutineMapping) {
                            return PatientRoutineMapping.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('patient-routine-mapping', null, { reload: 'patient-routine-mapping' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
