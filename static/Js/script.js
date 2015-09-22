$(document).ready(function(){
     $('#art').click(article);
})

function article()
{
  $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/customer?id=2",
            success:function (data) 
            {
                //alert("done: " + JSON.stringify(data));
            	//$('#article').html(data[1].id);
            }
        })
    .fail(function(data){
        alert("fail: " + JSON.stringify(data));        
      })
}