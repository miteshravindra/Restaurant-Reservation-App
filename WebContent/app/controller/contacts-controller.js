(function() {
  'use strict';
  angular
    .module('plunker')
    .controller('ContactsCtrl', ContactsController);

  
  ContactsController.$inject = ['contactsService'];
  function ContactsController(contactsService) {
	
	var contactsVm = this;
	contactsVm.contacts = {};
	contactsService.getContacts().then(function(data) {
		console.log(data);
		data.forEach(function(entry){
			entry.customer.time = new Date(entry.customer.time);
		});
		contactsVm.contacts = data;
		console.log(data);
    }, function(err) {
      console.log(err);
    });
	
	contactsVm.showDetails = function(position){
		  contactsVm.customerDetails = contactsVm.contacts[position];
	}
    
  }
  
  
  
  
})();