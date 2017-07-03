(function() {
  'use strict';

  angular
    .module('plunker')
    .service('contactsService', contactsServiceFn);

  contactsServiceFn.$inject = ['$q', '$http'];
  function contactsServiceFn($q, $http) {
    var self = this;
    self.getContacts = function() {
      var defer = $q.defer();
      $http({
			method: 'GET',
			url: 'api/reservation/all'
		})
		.success(function(data){
			if(data.status == 'success'){
				defer.resolve(data.payload);
			}
		})
		.error(function(err){
			data.reject(err);
		});
      return defer.promise;
    };
    
  }
})();