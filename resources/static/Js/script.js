$(document).ready(function(){
     $('#art').click(article);
     $('#insc').click(inscription);
})

function article()
{
    $('#contenu_page').empty();
    $('#acc').attr('class','');
    $('#insc').attr('class','');
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
                        .attr('id', data[i].titre)
            			.attr('class', 'list-group-item list-group-item-info')
            			.html(data[i].titre))
            	}
                $('.list-group-item').click(selectarticle);
            }
        })
    .fail(function(data){
        alert("fail: " + JSON.stringify(data));        
      })

}

function selectarticle()
{
    $('#contenu_page').empty();
    console.log("function selectarticle");
    var id = this.id;
     $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/article?titre="+id,
            success:function (data) 
            {
                $('#contenu_page').append(
                    $('<div />')
                        .attr('id', 'div_art')
                        .attr('class', 'panel panel-default')
                        .append(
                            $('<div />')
                                .attr('class', 'panel-heading')
                                .append(
                                    $('<h3 />')
                                        .attr('class', 'panel-title')
                                        .html(data.titre)))
                        .append(
                            $('<div />')
                                .attr('class', 'panel-body')
                                .html(data.contenu))
                    )
            }
    })

}

function inscription()
{
    $('#contenu_page').empty();
    $('#acc').attr('class','');
    $('#art').attr('class','');
    $('#insc').attr('class','active');
    console.log("function inscription");
    $('#contenu_page').load('../Html/element.html #form_inscr')
}
