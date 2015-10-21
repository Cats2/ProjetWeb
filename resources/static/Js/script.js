$(document).ready(function(){
     $('#art').click(article);
     $('#artl').click(article);
     $('#myart').click(mesArticles);
     $('#insc').click(inscription);
     $('#add_art').click(form_add_article);
     $('#conex').click(connexion);
     $('#deconexion').click(deconnexion);
     $('#profil').click(affprofil);
     $('#search').click(recherche);
     $('#search2').click(recherche2);
    var sessionlog = localStorage.getItem("connexion");
        if(sessionlog != null)
        {
            $('#pro_txt').html('Profil: ' + sessionlog);
             $('#deco').hide();
            $('#connexion').show('fast');
        }
        else
        {
              $('#connexion').hide();
                    $('#deco').show('fast');
        }
    accueil();
})

function accueil()
{
    $('#contenu_page').load('../Html/element.html #pageacc', function () {
            $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/articles",
            success:function (data) 
            {
            }   

        });
    })

}

function article()
{
    $('#Container').mixItUp('destroy');
    $('#contenu_page').empty();
    $('#acc').attr('class','');
    $('#insc').attr('class','');
    $('#art').attr('class','active');
    $('#artl').attr('class','active');
    console.log("function article");
    $('#titrepage').html("Liste des articles");
    $('#contenu_page').load('../Html/element.html #boutonmix', function () {
        });
    $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/articles",
            success:function (data) 
            {
                $('#contenu_page').append(
                    $('<div />')
                        .attr('id', 'Container'))
                 for (i in data)
                 {
                    //$('#Container').mixItUp('insert', i, $('<div class="mix "'+ data[i].catag +'><div class="col-sm-6 col-md-4"><div class="thumbnail"><img class="imgartlist" src="../Image/dragon.jpg"/><div class="caption"><h3>'+data[i].titre+'</h3></div></div></div></div>'), {filter: 'all'});
                   $('#Container').append(
                        $('<div />')
                            .attr('class', 'mix '+ data[i].categ)
                            .attr('data-my-order', i)
                            .attr('style','display:inline-block')
                            .append(
                            $('<div />')
                                .attr('class', 'col-sm-6 col-md-4')
                                .append(
                                    $('<div />')
                                        .attr('class', 'thumbnail')
                                        .append(
                                            $('<img />')
                                                .attr('class', 'imgartlist')
                                                .attr('src', '../Image/dragon.jpg'))
                                        .append(
                                            $('<div />')
                                                .attr('class', 'caption')
                                                .append(
                                                    $('<h3 />')
                                                        .html(data[i].titre))
                                                .append(
                                                    $('<p />')
                                                        .html(data[i].categ))
                                                .append(
                                                    $('<p />')
                                                        .append(
                                                            $('<a />')
                                                                .attr('href', '#')
                                                                .attr('class', 'btn btn-primary')
                                                                .attr('role', 'button')
                                                                .attr('id', data[i].titre)
                                                                .html('Afficher')))))))
                
            }
            $('.btn').click(selectarticle);
            $('#Container').mixItUp();
        }
        })
    .fail(function(data){
        alert("fail: " + JSON.stringify(data));        
      })

}

function mesArticles()
{
    $('#Container').mixItUp('destroy');
    $('#contenu_page').empty();
    $('#acc').attr('class','');
    $('#insc').attr('class','');
    $('#art').attr('class','active');
    $('#artl').attr('class','active');
    console.log("function Mes articles");
    $('#titrepage').html("Liste des articles");
    $('#contenu_page').load('../Html/element.html #boutonmix', function () {
        });
    pseudo = localStorage.getItem("connexion");
    $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/mesarticles?user="+pseudo,
            success:function (data) 
            {
                $('#contenu_page').append(
                    $('<div />')
                        .attr('id', 'Container'))
                 for (i in data)
                 {
                    //$('#Container').mixItUp('insert', i, $('<div class="mix "'+ data[i].catag +'><div class="col-sm-6 col-md-4"><div class="thumbnail"><img class="imgartlist" src="../Image/dragon.jpg"/><div class="caption"><h3>'+data[i].titre+'</h3></div></div></div></div>'), {filter: 'all'});
                   $('#Container').append(
                        $('<div />')
                            .attr('class', 'mix '+ data[i].categ)
                            .attr('data-my-order', i)
                            .attr('style','display:inline-block')
                            .append(
                            $('<div />')
                                .attr('class', 'col-sm-6 col-md-4')
                                .append(
                                    $('<div />')
                                        .attr('class', 'thumbnail')
                                        .append(
                                            $('<img />')
                                                .attr('class', 'imgartlist')
                                                .attr('src', '../Image/dragon.jpg'))
                                        .append(
                                            $('<div />')
                                                .attr('class', 'caption')
                                                .append(
                                                    $('<h3 />')
                                                        .html(data[i].titre))
                                                .append(
                                                    $('<p />')
                                                        .html(data[i].categ))
                                                .append(
                                                    $('<p />')
                                                        .append(
                                                            $('<a />')
                                                                .attr('href', '#')
                                                                .attr('class', 'btn btn-primary')
                                                                .attr('role', 'button')
                                                                .attr('id', data[i].titre)
                                                                .html('Afficher')))))))
                
            }
            $('.btn').click(selectarticle);
            $('#Container').mixItUp();
        }
        })
    .fail(function(data){
        alert("fail: " + JSON.stringify(data));        
      })
}

function selectarticle()
{
    $('#Container').mixItUp('destroy');
    $('#contenu_page').empty();
    console.log("function selectarticle");
    var id = this.id;
    var pseudo = localStorage.getItem("connexion");
    var avis;
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
                                        .html(data.titre + " " + data.dateCrea)
                                        .append(
                                            $('<a />')
                                                .attr('class', 'btn btn-default')
                                                .attr('role', 'button')
                                                .attr('id', 'avis')
                                                .append(
                                                    $('<span />')
                                                        .attr('class', 'glyphicon glyphicon-thumbs-up')
                                                        .attr('id', 'htmlavis')
                                                        .html("  " +data.avis)))))
                        .append(
                            $('<img />')
                                .attr('class', 'image')
                                .attr('src', '../Image/dragon.jpg'))
                        .append(
                            $('<div />')
                                .attr('class', 'panel-body')
                                .html(data.contenu))
                    )
                    $('#avis').click(function(){
                        var pseudo = localStorage.getItem("connexion");
                        console.log('ajout avis' + id)
                        $.ajax ({
                            type: "GET",
                            url: "http://127.0.0.1:9000/api/addAvisArticle?titre="+id+"&pseudo="+pseudo,
                            success:function (data) 
                            {
                                $('#avis').attr('class', 'btn btn-primary');
                                $('#avis').attr('disabled', 'disabled');
                                //var note = $('#htmlavis').val();
                                $('#htmlavis').html(parseInt($('#htmlavis').html(), 10)+1);
                            }
                        })
                    });
            }
    })
    if (pseudo != null)
    {
     $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/avisArticle?titre="+id+"&pseudo="+pseudo,
            success:function (data) 
            {
                console.log("data " + data);
                if(data == true)
                {
                    console.log("ture");
                    $('#avis').attr('class', 'btn btn-primary');
                    $('#avis').attr('disabled', 'disabled');
                }
            }
        })
    }
}

function addAvis()
{
    var pseudo = localStorage.getItem("connexion");
     $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/addAvisArticle?titre="+id+"&pseudo="+pseudo,
            success:function (data) 
            {
                $('#avis').attr('class', 'btn btn-primary');
                $('#avis').attr('disabled', 'disabled');
                var note = $('#htmlavis').val();
                note++;
                ('#htmlavis').html(note);
            }
    })
}

function form_add_article()
{
    $('#Container').mixItUp('destroy');
    console.log("function add_article");
    $('#contenu_page').empty();
    $('#contenu_page').load('../Html/element.html #add_article', function () {
            $('input[name=uploadfile]').change(imgadd);
            $('#btn_add_art').click(ajoutArticle);
        });
}

function ajoutArticle()
{
    console.log("function ajoutArticle");
    uploadFile();
    /*var image = $("#upload-img").val();
    var titre = $("#titre").val();
    console.log("titre "+ titre);
    var contenu = $("#contenu").val();
    console.log("contenu " + contenu);
    $.ajax ({
            type: "POST",
            url: "http://127.0.0.1:9000/api/addArticle?titre="+titre+"&contenu="+contenu,
            success:function (data) 
            {
                $('#contenu_page').append(
                    $('<div />')
                        .attr('class', 'alert alert-success')
                        .attr('role', 'alert')
                        .html("Article Ajouté")
                    )
            }
    })*/
}

function imgadd() {
    // A chaque sélection de fichier
    console.log("function imgadd");
        var files = $(this)[0].files;
        console.log("function à add" + files);
        if (files.length > 0) {
            // On part du principe qu'il n'y qu'un seul fichier
            // étant donné que l'on a pas renseigné l'attribut "multiple"
            var file = files[0],
                $image_preview = $('#image_preview');
            console.log("fic à add" + file);
            // Ici on injecte les informations recoltées sur le fichier pour l'utilisateur
            $image_preview.find('img').attr('src', window.URL.createObjectURL(file));
            $image_preview.find('h4').html(file.name);
            $image_preview.find('#infimg').html(file.size +' bytes');
        }
}

function uploadFile() {
    console.log("function uploadFile");
    var formData = new FormData();
    formData.append("uploadfile",$("input[name='uploadfile']")[0]);
    var test = $("#uploadfile").val();
    console.log($("input[name='uploadfile']")[0]);
    console.log(test);
  $.ajax({
    url: "http://127.0.0.1:9000/api/uploadFile?uploadfile="+test,
    type: "POST",
    enctype: 'multipart/form-data',
    processData: false,
    contentType : false,
    cache: false,
    success: function () {
      // Handle upload success
      // ...
    },
    error: function (jqXHR, textStatus, errorMessage) {
           console.log(errorMessage);
    }
  });
}

function inscription()
{
    $('#Container').mixItUp('destroy');
    $('#contenu_page').empty();
    $('#acc').attr('class','');
    $('#art').attr('class','');
    $('#insc').attr('class','active');
    console.log("function inscription");
    $('#contenu_page').load('../Html/element.html #form_inscr', function () {
        $('#btn_add_user').click(add_user);
    });
}

function add_user()
{
    console.log("function add_user");
    var pseudo = $("#pseudo").val();
    console.log("log "+ pseudo);
    var mdp = $("#mdp").val();
    console.log("mdp " + mdp);
    $.ajax ({
            type: "POST",
            url: "http://127.0.0.1:9000/api/addUser?login="+pseudo+"&pass="+mdp,
            success:function (data) 
            {
                if(data != "OK")
                {
                    $('#contenu_page').append(
                        $('<div />')
                            .attr('class', 'alert alert-danger')
                            .attr('role', 'alert')
                            .html(data)
                    )
                }
                else
                {
                    $('#contenu_page').append(
                        $('<div />')
                            .attr('class', 'alert alert-success')
                            .attr('role', 'alert')
                            .html("Inscription réussi, vous pouvez vous connecter !")
                    )
                }
            }
    })
}

function connexion()
{
    console.log("function connexion");
    $('#contenu_page').empty();
    var pseudo = $("#login").val();
    console.log("log "+ pseudo);
    var mdp = $("#pass").val();
    console.log("mdp " + mdp);
    $.ajax ({
            type: "POST",
            url: "http://127.0.0.1:9000/api/connexion?login="+pseudo+"&pass="+mdp,
            success:function (data) 
            {
                var index = data.indexOf("connecté");
                console.log(index);
                if (index >= 0)
                {
                    var pseudo = data.split(',');
                    $('#pro_txt').html('Profil: ' + pseudo[0]);
                    $('#contenu_page').append(
                        $('<div />')
                            .attr('class', 'alert alert-success')
                            .attr('role', 'alert')
                            .html(data)
                    )
                    $('#deco').hide();
                    $('#connexion').show('fast');
                    localStorage.setItem("connexion",pseudo[0]);
                }
                else
                {
                    $('#contenu_page').append(
                        $('<div />')
                            .attr('class', 'alert alert-danger')
                            .attr('role', 'alert')
                            .html(data)
                    )
                }
            }
    })
}

function deconnexion()
{
    $('#connexion').hide();
    $('#deco').show('fast');
    localStorage.removeItem("connexion");
}

function recherche()
{
    $('#Container').mixItUp('destroy');
    console.log("function recherche");
    $('#contenu_page').empty();
    var search = $("#recherche").val();
    console.log("search "+ search);
    $('#contenu_page').load('../Html/element.html #boutonmix', function () {
        });
    $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/searchArticle?search="+search,
            success:function (data) 
            {
                $('#contenu_page').append(
                    $('<div />')
                        .attr('id', 'Container'))
                 for (i in data)
                 {
                 $('#Container').append(
                        $('<div />')
                            .attr('class', 'mix '+ data[i].categ)
                            .attr('data-my-order', i)
                            .attr('style','display:inline-block')
                            .append(
                            $('<div />')
                                .attr('class', 'col-sm-6 col-md-4')
                                .append(
                                    $('<div />')
                                        .attr('class', 'thumbnail')
                                        .append(
                                            $('<img />')
                                                .attr('class', 'imgartlist')
                                                .attr('src', '../Image/dragon.jpg'))
                                        .append(
                                            $('<div />')
                                                .attr('class', 'caption')
                                                .append(
                                                    $('<h3 />')
                                                        .html(data[i].titre))
                                                .append(
                                                    $('<p />')
                                                        .html(data[i].categ))
                                                .append(
                                                    $('<p />')
                                                        .append(
                                                            $('<a />')
                                                                .attr('href', '#')
                                                                .attr('class', 'btn btn-primary')
                                                                .attr('role', 'button')
                                                                .attr('id', data[i].titre)
                                                                .html('Afficher')))))))
            }
            $('.btn').click(selectarticle);
            $('#Container').mixItUp();
        }
    })
}

function recherche2()
{
    $('#Container').mixItUp('destroy');
    console.log("function recherche");
    $('#contenu_page').empty();
    var search = $("#recherche2").val();
    console.log("search "+ search);
    $('#contenu_page').load('../Html/element.html #boutonmix', function () {
        });
    $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/searchArticle?search="+search,
            success:function (data) 
            {
                $('#contenu_page').append(
                    $('<div />')
                        .attr('id', 'Container'))
                 for (i in data)
                 {
                 $('#Container').append(
                        $('<div />')
                            .attr('class', 'mix '+ data[i].categ)
                            .attr('data-my-order', i)
                            .attr('style','display:inline-block')
                            .append(
                            $('<div />')
                                .attr('class', 'col-sm-6 col-md-4')
                                .append(
                                    $('<div />')
                                        .attr('class', 'thumbnail')
                                        .append(
                                            $('<img />')
                                                .attr('class', 'imgartlist')
                                                .attr('src', '../Image/dragon.jpg'))
                                        .append(
                                            $('<div />')
                                                .attr('class', 'caption')
                                                .append(
                                                    $('<h3 />')
                                                        .html(data[i].titre))
                                                .append(
                                                    $('<p />')
                                                        .html(data[i].categ))
                                                .append(
                                                    $('<p />')
                                                        .append(
                                                            $('<a />')
                                                                .attr('href', '#')
                                                                .attr('class', 'btn btn-primary')
                                                                .attr('role', 'button')
                                                                .attr('id', data[i].titre)
                                                                .html('Afficher')))))))
            }
            $('.btn').click(selectarticle);
            $('#Container').mixItUp();
        }
    })
}
function affprofil()
{
    $('#acc').attr('class','');
    $('#art').attr('class','');
    $('#insc').attr('class','');
    $('#profil').attr('class','active');
    console.log("Affichage Profil");
    $('#contenu_page').empty();
    var pseudo= localStorage.getItem("connexion");
    $.ajax ({
            type: "GET",
            url: "http://127.0.0.1:9000/api/user?pseudo="+pseudo,
            success:function (data) 
            {
                console.log("profilsuccces");
                console.log(data);

                $('#contenu_page').append(
                    $('<div />')
                        .attr('class','panel panel-default')
                        .append(
                            $('<div />')
                                .attr('class', 'panel-heading')
                                .html("Information Profil"))
                        .append(
                             $('<div />')
                                .attr('class', 'panel-body')
                                .attr('id', 'panelbody')
                                .append(
                                    $('<p />')
                                        .html(data.datecrea))
                                .append(
                                    $('<a />')
                                        .attr('href', '#')
                                        .attr('id', 'modifpseudo')
                                        .attr('class', 'btn btn-default')
                                        .attr('role', 'button')
                                        .html('Modifier le pseudonyme'))
                                .append(
                                    $('<a />')
                                        .attr('href', '#')
                                        .attr('id', 'modifmdp')
                                        .attr('class', 'btn btn-default')
                                        .attr('role', 'button')
                                        .html('Modifier le mot de passe'))
                                .append(
                                    $('<a />')
                                        .attr('href', '#')
                                        .attr('id', 'supprcompte')
                                        .attr('class', 'btn btn-default')
                                        .attr('role', 'button')
                                        .html('Supprimer Compter')))
                        .append(
                            $('<ul />')
                                .attr('class', 'list-group')
                                .append(
                                    $('<li />')
                                        .attr('id', 'lipseudo')
                                        .attr('class', 'list-group-item')
                                        .html("Pseudo: " + data.pseudo))
                                .append(
                                    $('<li />')
                                        .attr('class', 'list-group-item')
                                        .html("Mot de passe: " + data.mdp))
                                .append(
                                    $('<li />')
                                        .attr('class', 'list-group-item')
                                        .html('Nombre d\'article posté ')
                                        .append(
                                            $('<span />')
                                                .attr('class', 'badge')
                                                .html(data.nbArt)))
                                .append(
                                    $('<li />')
                                        .attr('class', 'list-group-item')
                                        .html('Nombre de commentaire posté ')
                                        .append(
                                            $('<span />')
                                                .attr('class', 'badge')
                                                .html('0')))))
                $('#modifpseudo').click(ModifierPseudo);
                $('#modifmdp').click(ModifierMdp);
                $('#supprcompte').click(SupprimerCompte);
        }
        })
}

function ModifierPseudo()
{
    $('#panelbody').empty();
    $('#panelbody').append(
        $('<form />')
            .attr('class', 'navbar-form navbar-left')
            .attr('role', 'search')
            .append(
                $('<div />')
                    .attr('class', 'form-group')
                    .append(
                        $('<input />')
                            .attr('id', 'new_pseudo')
                            .attr('type', 'text')
                            .attr('class', 'form-control')
                            .attr('placeholder',  'Nouveau Pseudo'))
                    .append(
                        $('<button />')
                            .attr('type', 'button')
                            .attr('id', 'btn_modif_pseudo')
                            .attr('class', 'btn btn-default')
                            .html('Valider'))
                    .append(
                        $('<button />')
                            .attr('type', 'button')
                            .attr('id', 'btn_modif_pseudo-annuler')
                            .attr('class', 'btn btn-default')
                            .html('Annuler'))))
    $('#btn_modif_pseudo').click(UpdatePseudo);
    $('#btn_modif_pseudo-annuler').click(affprofil);
}


function UpdatePseudo()
{
    console.log("Update Pseudo");
    var newpseudo = $("#new_pseudo").val();
    $('#contenu_page').empty();
    console.log("new pseudo " + newpseudo);
    var pseudo= localStorage.getItem("connexion");
    $.ajax ({
            type: "POST",
            url: "http://127.0.0.1:9000/api/updatePseudo?old_login="+pseudo+"&new_login="+newpseudo,
            success:function (data) 
            {
                if (data.indexOf("Erreur") >= 0)
                {
                    $('#contenu_page').append(
                        $('<div />')
                            .attr('class', 'alert alert-danger')
                            .attr('role', 'alert')
                            .html(data))
                }
                else
                {
                    localStorage.setItem("connexion", newpseudo);
                    affprofil();
                    $('#lipseudo').append(
                        $('<div />')
                            .attr('class', 'alert alert-success')
                            .attr('role', 'alert')
                            .html(data))
                }
            }
        })
}

function ModifierMdp()
{
    $('#panelbody').empty();
    $('#panelbody').append(
        $('<form />')
            .attr('class', 'navbar-form navbar-left')
            .attr('role', 'search')
            .append(
                $('<div />')
                    .attr('class', 'form-group')
                    .append(
                        $('<input />')
                            .attr('id', 'new_mdp')
                            .attr('type', 'text')
                            .attr('class', 'form-control')
                            .attr('placeholder',  'Nouveau Mot de Passe'))
                    .append(
                        $('<button />')
                            .attr('type', 'button')
                            .attr('id', 'btn_modif_mdp')
                            .attr('class', 'btn btn-default')
                            .html('Valider'))
                    .append(
                        $('<button />')
                            .attr('type', 'button')
                            .attr('id', 'btn_modif_mdp_annuler')
                            .attr('class', 'btn btn-default')
                            .html('Annuler'))))
    $('#btn_modif_mdp').click(UpdateMdp);
    $('#btn_modif_mdp_annuler').click(affprofil);
}

function UpdateMdp()
{
    console.log("Update Mdp");
    var newmdp = $("#new_mdp").val();
    $('#contenu_page').empty();
    console.log("new mdp " + newmdp);
    var pseudo= localStorage.getItem("connexion");
    $.ajax ({
            type: "POST",
            url: "http://127.0.0.1:9000/api/updateMdp?login="+pseudo+"&new_mdp="+newmdp,
            success:function (data) 
            {
                if (data.indexOf("Erreur") >= 0)
                {
                    $('#contenu_page').append(
                        $('<div />')
                            .attr('class', 'alert alert-danger')
                            .attr('role', 'alert')
                            .html(data))
                }
                else
                {
                    affprofil();
                    $('#lipseudo').append(
                        $('<div />')
                            .attr('class', 'alert alert-success')
                            .attr('role', 'alert')
                            .html(data))
                }
            }
        })
}


function SupprimerCompte()
{
    console.log("Supprimer Compte");
    $('#contenu_page').empty();
    var pseudo= localStorage.getItem("connexion");
    $.ajax ({
            type: "POST",
            url: "http://127.0.0.1:9000/api/deleteUser?login="+pseudo,
            success:function (data) 
            {
                if (data.indexOf("Erreur") >= 0)
                {
                    $('#contenu_page').append(
                        $('<div />')
                            .attr('class', 'alert alert-danger')
                            .attr('role', 'alert')
                            .html(data))
                }
                else
                {
                    deconnexion();
                    localStorage.removeItem("connexion");
                    $('#contenu_page').append(
                        $('<div />')
                            .attr('class', 'alert alert-success')
                            .attr('role', 'alert')
                            .html(data))
                }
            }
        })
}
