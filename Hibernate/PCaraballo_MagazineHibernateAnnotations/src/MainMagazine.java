import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entities.Autor;
import Entities.Revista;

public class MainMagazine {
	
	private static SessionFactory factory;

	public static void main(String[] args) throws IOException, ParseException {

		factory=new Configuration().configure().buildSessionFactory();
		
		//Instanciamos los Managers
		ManagerAutor MA=new ManagerAutor();
		ManagerRevista MR=new ManagerRevista();
		ManagerArticle MAR=new ManagerArticle();
		
		//Instanciamos el FileAccessor y cargamos los datos de los archivos
		FileAccessor fa=new FileAccessor();
		fa.readAutorsFile("autors.txt");
		fa.readMagazinesFile("revistes.txt");
		fa.readArticlesFile("articles.txt");
		
		//Creamos una revista por cada una de las revistas que hemos cargado desde el archivo
		for(Revista R : fa.llistaRevistes){
			MR.addRevista(R);
		}
		
		Autor pablo=new Autor(28,"Pablo", "Española","1990",true);
		//Añadir Autor
		MA.addAutor(pablo);
		
		//Modificar Autor
		MA.updateEmployee(8, true);
		
		//Añadir Articulo
		SimpleDateFormat dbf=new SimpleDateFormat("dd/MM/yyyy");
		Date fecha=dbf.parse("30/01/2017");
		MAR.addArticle(new Entities.Article(30,"No es gol por un metro",fecha,true,pablo));
	}
	
	
	public static SessionFactory getFactory()
	{
		return factory;
	}
}
