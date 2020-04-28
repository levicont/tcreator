  
 	$(document).ready(function(){

 		$("#inputFileXLS").on('change',function (event) {
        if(event.target.files[0] == undefined){
            return
        }
        var fieldVal = event.target.files[0].name;
        if (fieldVal != undefined || fieldVal != "") {
            var result = fieldVal;
            $("#labelInputFileXLS").text(result);
        }
        });

 	});
