(function() {
  'use strict';

  angular
    .module('plunker')
    .controller('SeatingCtrl', SeatingController);

  SeatingController.$inject = ['seatingService'];

  function SeatingController(seatingService) {
    var seatingVm = this;
    seatingService.getSeatingDetails().then(function(data) {
    	seatingVm.table = data;
    }, function(err) {
      console.log(err);
    });
    
    seatingVm.showDetails = function(position){
    	seatingVm.cnfCode = seatingVm.table[position].cnfCode;
    	console.log(seatingVm.cnfCode);
	}
    
    
    
  }

})();