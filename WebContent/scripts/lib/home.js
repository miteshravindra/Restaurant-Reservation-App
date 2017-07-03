/**
 * Created by jain on 7/22/15.
 */
$(document).ready(function(){
    $('.myTable').dataTable();
    $("[name='my-checkbox']").bootstrapSwitch();
    $('[data-toggle="popover"]').popover({trigger:"hover"})
});
