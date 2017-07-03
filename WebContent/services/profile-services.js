(function() {
  'use strict';

  angular
    .module('plunker')
    .service('profileService', profileServiceFn);

  profileServiceFn.$inject = ['$q', '$http'];
  function profileServiceFn($q, $http) {
    var self = this;
    
    self.getProfileDetails = function() {
        var defer = $q.defer();
        $http({
  			method: 'GET',
  			url: 'api/profile/details'
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
    
    
    self.updateProfile = function(newPro) {
      var defer = $q.defer();
      $http({
			method: 'PUT',
			url: 'api/profile/update',
			data: newPro
		})
		.success(function(data){
			if(data.status == 'success'){
				defer.resolve(data);
			}
		})
		.error(function(err){
			data.reject(err);
		});
      return defer.promise;
    };
  }
})();