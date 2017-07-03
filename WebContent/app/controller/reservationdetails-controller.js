(function() {
  'use strict';
  angular
    .module('plunker')
    .controller('ReservationDetailsCtrl', ReservationListController);

  
  ReservationListController.$inject = ['reservationService'];
  function ReservationListController(reservationService) {
	
	var reserveVm = this;
	reserveVm.table = {};
    reservationService.getReservationDetails().then(function(data) {
      reserveVm.people = data;
    }, function(err) {
      console.log(err);
    });
    
    reserveVm.showDetails = function(position){
    	reserveVm.reservationDetails = reserveVm.people[position];
	}
    
    
    reserveVm.getLayout = function(position){
    	reserveVm.selectedPostion = position;
    	reserveVm.selectedCnfCode = reserveVm.people[position].cnfCode;
    	reservationService.getTables().then(function(data) {
            reserveVm.tables = data;
            console.log(data);
          }, function(err) {
            console.log(err);
         });
    	
    }
    
    reserveVm.makeReservation = function(isFormValid){
    	if(isFormValid){
    		console.log(reserveVm.newReserve);
    		reservationService.makeReservation(reserveVm.newReserve).then(function(data) {
    	    	if(data.status == "success"){
    	    		console.log(data.message);
    	    	}
    	    }, function(err) {
    	    	console.log(err);
    	    });
    	}
    	else{
    		console.log('Form values are incorrect');
    	}
	};
    
    
    
    reserveVm.selectTable = function(table){
    	reserveVm.selectedTable = table;
    }
    
    
    
    reserveVm.reserveTable = function(){
		reserveVm.table.cnfCode = reserveVm.people[reserveVm.selectedPostion].cnfCode;
		reserveVm.table.id = reserveVm.selectedTable.id;
		reserveVm.table.status = 'booked';
		reserveVm.table.since = '';
		reserveVm.table.size = reserveVm.selectedTable.size;
		
		reservationService.reserveTable(reserveVm.table).then(function(data) {
	    	if(data.status == "success"){
	    		console.log(data.message);
	    	}
	    }, function(err) {
	    	console.log(err);
	    	reserveVm.updateReserve.serverMsg = err;
	    });
	};
    
    
  }
})();