(function() {
  'use strict';

  angular
    .module('plunker')
    .service('reservationService', reservationServiceFn);

  reservationServiceFn.$inject = ['$q', '$http'];
  function reservationServiceFn($q, $http) {
    var self = this;
    self.getReservationDetails = function() {
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
    
      
    self.getTables = function() {
        var defer = $q.defer();
        $http({
  			method: 'GET',
  			url: 'api/table/all'
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
    
      
      self.reserveTable = function(table) {
    	  console.log('inside table services ');
    	  console.log(table);
          var defer = $q.defer();
          $http({
    			method: 'PUT',
    			url: 'api/table/reserve',
    			data: table
    		})
    		.success(function(data){
    			if(data.status == 'success'){
    				console.log('inside table success');
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