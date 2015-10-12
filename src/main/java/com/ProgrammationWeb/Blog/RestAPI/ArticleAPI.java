package com.ProgrammationWeb.Blog.RestAPI;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ProgrammationWeb.Blog.BDD.JDBC;

@Named
@Path("/")
public class ArticleAPI {
	
	static {
		/*for (int i = 0; i <= 5 ; i++) {			
			Article art = new Article();
			art.setId(i);
			art.setTitre("Titre" + i);
			art.setContenu("...");
			articles.add(art);			
		}*/
		JDBC.Connection();
	}
	
	@GET
	@Path("article")
	@Produces(MediaType.APPLICATION_JSON)
	public Article getArticle(@QueryParam("titre") String titre) {
		System.out.println("param" + titre );
		List<Article> articles = new ArrayList<Article>();
		articles.addAll(JDBC.getArticle());
		Article article = null;
		for (Article a : articles) {
			System.out.println("test art one "  + a.getId() + " "+ a.getTitre());
			if (a.getTitre().equals(titre))
				article = a;
		}
		return article;
	}
	
	@GET
	@Path("articles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Article> getListArticle() {
		List<Article> articles = new ArrayList<Article>();
		articles.addAll(JDBC.getArticle());
		return articles;
	}
	
	@POST
	@Path("addArticle")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean addArticle(@QueryParam("titre") String titre, @QueryParam("contenu") String contenu) {
		System.out.println("Titre à add " + titre);
		System.out.println("Contenu de l'art à add" + contenu);
		Boolean r = JDBC.addArticle(titre, contenu);
		return r;
	}
	
	
	@POST
	@Path("uploadFile")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public  ResponseEntity<?>  uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {
		System.out.println("Upload image");
	  try {
	    // Get the filename and build the local file path (be sure that the 
	    // application have write permissions on such directory)
	    String filename = uploadfile.getOriginalFilename();
	    System.out.println("filename : " + filename);
	    String directory = "/resources/static/Image";
	    String filepath = Paths.get(directory, filename).toString();
	    
	    // Save the file locally
	    BufferedOutputStream stream =
	        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    stream.write(uploadfile.getBytes());
	    stream.close();
	  }
	  catch (Exception e) {
	    System.out.println("Erreur " + e.getMessage());
	    System.out.println(e.getMessage());
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }
	  
	  return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GET
	@Path("searchArticle")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Article> getSearchListArticle(@QueryParam("search") String search) {
		List<Article> articles = new ArrayList<Article>();
		articles.addAll(JDBC.getSearchArticle(search));
		return articles;
	}
}
