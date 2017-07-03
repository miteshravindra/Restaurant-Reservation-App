(function() {
  'use strict';

  angular
    .module('plunker')
    .service('loginService', loginServiceFn);

  loginServiceFn.$inject = ['$q', '$http','$window'];
  function loginServiceFn($q, $http, $window) {
    var self = this;
    
    self.getCredentials = function(login) {
      var defer = $q.defer();
      $http({
			method: 'GET',
			url: 'api/owner/login'
		})
		.success(function(data){
			if(data.status == 'success'){
				if(login.email==data.payload.email && login.password==data.payload.password){
					console.log("success");
					$window.location.href = 'home.html';
				}
				else{
					data.payload.message="Your email or password is wrong";
				}
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