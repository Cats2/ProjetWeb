$(document).ready(function(){
     $('#art').click(article);
})

function article()
{
$('#acc').attr('class','');
$('#art').attr('class','active');
console.log("function article");
  $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/articles",
            success:function (data) 
            {
            	$('#contenu_page').append(
            		$('<ul />')
            			.attr('id', 'ul')
            			.attr('class', 'list-group'))
                for (i in data)
                {
            	$('#ul').append(
            		$('<li />')
            			.attr('class', 'list-group-item list-group-item-info')
            			.html(data[i].titre))
            	}
            }
        })
    .fail(function(data){
        alert("fail: " + JSON.stringify(data));        
      })
}