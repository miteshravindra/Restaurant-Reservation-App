(function() {
  'use strict';
  angular
    .module('plunker')
    .controller('ReservationCtrl', ReservationController);

  
  ReservationController.$inject = ['reservationService'];
  function ReservationController(reservationService) {
	var reserveVm = this;
	reserveVm.makeReservation = function(){
		console.log(reserveVm.newReserve);
		reservationService.makeReservation(reserveVm.newReserve).then(function(data) {
	    	if(data.status == "success"){
	    		console.log(data.message);
	    	}
	    }, function(err) {
	    	console.log(err);
	    });
	};
	
	reserveVm.cancelReservation = function(){
		reservationService.deleteReservation(reserveVm.cnfCode).then(function(data) {
	    	if(data.status == "success"){
	    		console.log(data.message);
	    	}
	    }, function(err) {
	    	console.log(err);
	    });
		
	}
	
	reserveVm.updateReservation = function(){
		console.log(reserveVm.updateReserve);
		reservationService.updateReservation(reserveVm.updateReserve).then(function(data) {
	    	if(data.status == "success"){
	    		console.log(data.message);
	    		reserveVm.updateReserve.serverMsg = data.message;
	    	}
	    }, function(err) {
	    	console.log(err);
	    	reserveVm.updateReserve.serverMsg = err;
	    });
	};
	
  }

})();