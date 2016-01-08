/**
 * 
 */
function mainFunction()
{
	$('#creditLink').click(function(){
		$('#debitid').css("display","none");
		$('#creditid').css("display","block");
		$('#transidid').css("display","none");
	});
	
	$('#debitLink').click(function(){
		$('#debitid').css("display","block");
		$('#creditid').css("display","none");
		$('#transidid').css("display","none");
	});
	$('#transLink').click(function(){
		$('#debitid').css("display","none");
		$('#creditid').css("display","none");
		$('#transid').css("display","block");
	});
}

$(document).ready(function(){

mainFunction();			
		});