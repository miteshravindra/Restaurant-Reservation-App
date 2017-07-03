(function() {
  'use strict';

  angular
    .module('plunker')
    .service('reservationService', reservationServiceFn);

  reservationServiceFn.$inject = ['$q', '$http'];
  function reservationServiceFn($q, $http) {
    var self = this;
    self.makeReservation = function(newReserve) {
      var defer = $q.defer();
      $http({
			method: 'POST',
			url: 'api/reservation/add',
			data: newReserve
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
    
    self.deleteReservation = function(cnfCode) {
        var defer = $q.defer();
        $http({
  			method: 'DELETE',
  			url: 'api/reservation/delete/'+cnfCode,
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
      
      
      self.updateReservation = function(newReserve) {
          var defer = $q.defer();
          $http({
    			method: 'PUT',
    			url: 'api/reservation/update',
    			data: newReserve
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