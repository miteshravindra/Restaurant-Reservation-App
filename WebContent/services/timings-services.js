(function() {
  'use strict';

  angular
    .module('plunker')
    .service('timingsService', timingsServiceFn);

  timingsServiceFn.$inject = ['$q', '$http'];
  function timingsServiceFn($q, $http) {
    var self = this;
    self.getTimings = function() {
      var defer = $q.defer();
      $http({
			method: 'GET',
			url: 'api/timings/all'
		})
		.success(function(data){
			var result = data.payload;
			result.forEach(function(entry){
				entry.startTime = new Date(entry.startTime);
				entry.endTime = new Date(entry.endTime);
			});
			data.payload = result;
			console.log(data);
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