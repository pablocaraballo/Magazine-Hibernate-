

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import Entities.*;

/**
 * @author Pablo Caraballo
 * @version FileAccessorv01
 * @since 20/01/2017
 */

public class FileAccessor {

	ArrayList<Autor> llistaAutors = new ArrayList();
	ArrayList<Revista> llistaRevistes = new ArrayList();

	public FileAccessor() {
	}

	/**
	 * @param filename
	 *            Aquest String correspon amb l'arxiu on s'emmagatzemen les
	 *            dades de les instancies d'Autor
	 * @throws IOException
	 * @see Autor
	 *
	 *      <dt><b>Preconditions:</b>
	 *      <dd>
	 *      filename<>nil </br> llistaAutors == nil
	 *      <dt><b>Postconditions:</b>
	 *      <dd>
	 *      llistaAutors<>nil
	 */

	public void readAutorsFile(String filename) throws IOException {
		int id;
		String name, year, country;
		boolean active;

		BufferedReader br = new BufferedReader(new FileReader(filename));
		String linea = "";
		while ((linea = br.readLine()) != null) {
			StringTokenizer str = new StringTokenizer(linea, ",");
			id = Integer.parseInt(str.nextToken());
			name = str.nextToken();
			year = str.nextToken();
			country = str.nextToken();
			active = Boolean.parseBoolean(str.nextToken());
			// System.out.println(id + name + country + year + active);
			llistaAutors.add(new Autor(id, name, country, year, active));

		}
		br.close();

	}

	public void printAutors() {
		for (int i = 0; i < llistaAutors.size(); i++) {
			System.out.println(llistaAutors.get(i).toString());
		}
	}

	public void printRevistes() {
		// TODO
		//
		for (int i = 0; i < llistaRevistes.size(); i++) {
			System.out.println(llistaRevistes.get(i).toString());
		}
	}

	/**
	 * 
	 * @param filename
	 *            Aquest String correspon amb l'arxiu on s'emmagatzemen les
	 *            dades de les instancies de Revista
	 * @throws IOException
	 * 
	 *             <dt><b>Preconditions:</b>
	 *             <dd>
	 *             filename<>nil </br> llistaRevistes == nil
	 *             <dt><b>Postconditions:</b>
	 *             <dd>
	 *             llistaRevistes<>nil
	 */

	public void readMagazinesFile(String filename) throws IOException {
		int id;
		String titol;
		Date date;
		DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");

		BufferedReader br = new BufferedReader(new FileReader(filename));
		String linea = "";
		while ((linea = br.readLine()) != null) {
			StringTokenizer str = new StringTokenizer(linea, ",");
			id = Integer.parseInt(str.nextToken());
			titol = str.nextToken();
			try {
				date = dateformat.parse(str.nextToken());
				// TODO
				// Cal afegir un objecte Magazine a la llista
				llistaRevistes.add(new Revista(id,titol,date));
				

			} catch (ParseException e) {
				System.err.println(" errada format data al fitxer");
				e.printStackTrace();
			}

		}
		br.close();

	}

	/**
	 * @param filename
	 *            Aquest String correspon amb l'arxiu on s'emmagatzemen les
	 *            dades de les isntancies de Revista
	 * @return ArrayList d'objectes Revista, amb els seus articles i la
	 *         informació de l'autor
	 * @throws IOException
	 * 
	 *             <dt><b>Preconditions:</b>
	 *             <dd>
	 *             filename<>nil </br> llistaRevistes<>nil </br>
	 *             llistaRevistes.getRevista(i).getArticles()== nil</br>
	 *             <dt><b>Postconditions:</b>
	 *             <dd>
	 *             llistaRevistes.getRevistes()<>nil</br>
	 *             llistaRevistes.getRevista(i).getArticles()<>nil</br>
	 *             llistaRevistes.getRevista(i).getArticle(j)<>nil</br>
	 *             llistaRevistes
	 *             .getRevista(i).getArticle(j).getAutor()<>nil</br>
	 * 
	 */
	public ArrayList<Revista> readArticlesFile(String filename)
			throws IOException {
		int idArticle, idRevista, idAutor;
		String titol;
		Date data_creacio;
		boolean publicable;
		DateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD");

		// TODO afegir la resta d'atributs necessaris

		// TODO implementar mètode de lectura del fitxer
		BufferedReader br=new BufferedReader(new FileReader(filename));
		String linea=br.readLine();
		
		while(linea!=null){
			// TODO per a cada línia emprar StringTokenizer
			StringTokenizer token=new StringTokenizer(linea, ",");
			// TODO Mentre hi hagi línies
			// TODO -> Assignar els diferents tokens a variables
			idArticle=Integer.parseInt(token.nextToken());
			idRevista=Integer.parseInt(token.nextToken());
			idAutor=Integer.parseInt(token.nextToken());
			titol=token.nextToken();
			
			try {
				data_creacio=dateformat.parse(token.nextToken());
				publicable=Boolean.getBoolean(token.nextToken());
				
				for(int j=0; j<llistaRevistes.size(); j++){
					
					if(idRevista==llistaRevistes.get(j).getId_revista()){
						
						for(int i=0; i<llistaAutors.size(); i++){
							if(idAutor==llistaAutors.get(i).getId_autor()){
								
								// TODO -> Crear un objecte Article afegint com autor l'element idAutor de la llista d'autors
								// TODO -> incorporar l'article a la revista indicada per idRevista dins
								// la llista de revistes
								llistaRevistes.get(j).addArticle(new Article(idArticle,titol,data_creacio,publicable,llistaAutors.get(i)));
							}
						}
					}
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			linea=br.readLine();
		}
		return llistaRevistes;
	}

}
