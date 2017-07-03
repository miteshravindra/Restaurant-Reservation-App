(function() {
  'use strict';

  angular
    .module('plunker')
    .service('seatingService', seatingServiceFn);

  seatingServiceFn.$inject = ['$q', '$http'];
  function seatingServiceFn($q, $http) {
    var self = this;
    
    self.getSeatingDetails = function() {
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
    
  }
  
})();