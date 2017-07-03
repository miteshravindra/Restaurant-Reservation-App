(function() {
  'use strict';
  angular
    .module('plunker')
    .controller('TimingsCtrl', TimingsController);

  
  TimingsController.$inject = ['timingsService'];
  function TimingsController(timingsService) {
	var timingsVm = this;
	timingsService.getTimings().then(function(data) {
      timingsVm.timings = data;
      console.log(data);
    }, function(err) {
      console.log(err);
    });
    
    
  }
})();