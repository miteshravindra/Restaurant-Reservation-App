(function() {
  'use strict';
  angular
    .module('plunker')
    .controller('ProfileCtrl', ProfileController);

  
  ProfileController.$inject = ['profileService'];
  function ProfileController(profileService) {
	var profileVm = this;
	
	profileService.getProfileDetails().then(function(data) {
		profileVm.newPro = data;
    }, function(err) {
      console.log(err);
    });
	
	
	profileVm.updateProfile = function(){
	    profileService.updateProfile(profileVm.newPro).then(function(data) {
	    	console.log(data);
	    	if(data.status == "success"){
	    		console.log(data.message);
	    	}
	    }, function(err) {
	    	console.log(err);
	    });
	};
  }

})();