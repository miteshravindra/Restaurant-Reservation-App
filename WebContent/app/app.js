(function(){
	'use strict';
	angular.module('plunker',['ngMessages','ngRoute']);
	angular.module('plunker').config(moduleConfig);
	angular.module('plunker').filter('phoneFilter',phoneFilter);
	
	moduleConfig.$inject = ['$routeProvider'];
	function moduleConfig($routeProvider){
		$routeProvider
        .when('/reservation-details', {
          templateUrl: 'partials/reservation-list.html',
          controller: 'ReservationDetailsCtrl',
          controllerAs: 'reserveDetailsVm'
        })
        .when('/profile', {
          templateUrl: 'partials/profile.html',
          controller: 'ProfileCtrl',
          controllerAs: 'profileVm'
        })
        .when('/users/:userid', {
          templateUrl: 'user-profile-tmpl.html',
          controller: 'UserProfileCtrl',
          controllerAs: 'uprofileVm'
        })
        .when('/seating', {
          templateUrl: 'partials/seating-area.html',
          controller: 'SeatingCtrl',
          controllerAs: 'seatingVm'
        })
        .when('/layout', {
          templateUrl: 'partials/layout.html',
          controller: 'LayoutCtrl',
          controllerAs: 'layoutVm'
        })
        .when('/make-reservation', {
          templateUrl: 'partials/make-reservation.html',
          controller: 'ReservationCtrl',
          controllerAs: 'reserveVm'
        })
        .when('/delete-reservation', {
          templateUrl: 'partials/delete-reservation.html',
          controller: 'ReservationCtrl',
          controllerAs: 'reserveVm'
        })
        .when('/login', {
          templateUrl: 'partials/login-temp.html',
          controller: 'LoginCtrl',
          controllerAs: 'loginVm'
        })
        .when('/update-reservation', {
          templateUrl: 'partials/update-reservation.html',
          controller: 'ReservationCtrl',
          controllerAs: 'reserveVm'
        })
        .when('/contacts', {
          templateUrl: 'partials/contacts.html',
          controller: 'ContactsCtrl',
          controllerAs: 'contactsVm'
        })
        .when('/timings', {
          templateUrl: 'partials/timings.html',
          controller: 'TimingsCtrl',
          controllerAs: 'timingsVm'
        })
        .otherwise({
          redirectTo: '/users'
        });
	}
	
	function phoneFilter(){
		return function(phoneStr){
			var result = '';
			if(phoneStr!= undefined){
				result = '(' + phoneStr.substring(0,3) + ') ' + phoneStr.substring(3,6) + '-' + phoneStr.substring(6);
				return result;
			}
		}
	}
	
})();