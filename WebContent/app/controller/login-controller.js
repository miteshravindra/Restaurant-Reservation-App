(function() {
  'use strict';
  angular
    .module('plunker')
    .controller('LoginCtrl', LoginController);

  LoginController.$inject = ['loginService'];
  
  function LoginController(loginService) {
    var loginVm = this;
    loginVm.authenticate = function (isFormValid){
    	if(isFormValid){
    		loginService.getCredentials(loginVm.login).then(function(data) {
    			loginVm.message = data.message;
            }, function(err) {
              console.log(err);
            });
    	}
    }
  }
})();