(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('patient-booking', {
            parent: 'entity',
            url: '/patient-booking?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PatientBookings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/patient-booking/patient-bookings.html',
                    controller: 'PatientBookingController',
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
                }],
            }
        })
        .state('patient-booking-detail', {
            parent: 'entity',
            url: '/patient-booking/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PatientBooking'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/patient-booking/patient-booking-detail.html',
                    controller: 'PatientBookingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PatientBooking', function($stateParams, PatientBooking) {
                    return PatientBooking.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'patient-booking',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('patient-booking-detail.edit', {
            parent: 'patient-booking-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/patient-booking/patient-booking-dialog.html',
                    controller: 'PatientBookingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PatientBooking', function(PatientBooking) {
                            return PatientBooking.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('patient-booking.new', {
            parent: 'patient-booking',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/patient-booking/patient-booking-dialog.html',
                    controller: 'PatientBookingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                doctorId: null,
                                patientId: null,
                                bookingDateAndTime: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('patient-booking', null, { reload: 'patient-booking' });
                }, function() {
                    $state.go('patient-booking');
                });
            }]
        })
        .state('patient-booking.edit', {
            parent: 'patient-booking',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/patient-booking/patient-booking-dialog.html',
                    controller: 'PatientBookingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PatientBooking', function(PatientBooking) {
                            return PatientBooking.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('patient-booking', null, { reload: 'patient-booking' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('patient-booking.delete', {
            parent: 'patient-booking',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/patient-booking/patient-booking-delete-dialog.html',
                    controller: 'PatientBookingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PatientBooking', function(PatientBooking) {
                            return PatientBooking.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('patient-booking', null, { reload: 'patient-booking' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
