import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import Entities.Revista;

public class ManagerRevista {

	/*private static SessionFactory factory;
	
	public static void main(String[] args) throws IOException {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		/*ManagerRevista MA = new ManagerRevista();
		FileAccessor fa;
		fa = new FileAccessor();
		fa.readMagazinesFile("revistes.txt");
		System.out.println("Revistes llegits des del fitxer");
		for (int i = 0; i < fa.llistaRevistes.size(); i++) {

			System.out.println(fa.llistaRevistes.get(i).toString());
			MA.addRevista(fa.llistaRevistes.get(i));

		}
		System.out.println("Revistes llegits de la base de dades");
		MA.listRevistes();
		MA.deleteAutor(1);
		MA.updateRevista(2, "Hola");
		System.out
				.println("Revistes llegits de la base de dades després de des actualitzacions");
		MA.listRevistes();

	}*/

	/* Method to CREATE an Autor in the database */
	public void addRevista(Revista rev) {
		Session session = MainMagazine.getFactory().openSession();
		Transaction tx = null;
		Integer revistaID = null;
		try {
			tx = session.beginTransaction();

			revistaID = (Integer) session.save(rev);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	/* Method to READ all Autors */
	public void listRevistes() {
		Session session = MainMagazine.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List revistes = session.createQuery("FROM Revista").list();

			for (Iterator iterator = revistes.iterator(); iterator.hasNext();) {
				Revista revista = (Revista) iterator.next();
				System.out.println(revista.toString());
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE activity for an autor */
	public void updateRevista(Integer revistaID, String titol) {
		Session session = MainMagazine.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Revista rev = (Revista) session.get(Revista.class, revistaID);
			rev.setTitol(titol);;
			session.update(rev);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteAutor(Integer revistaID) {
		Session session = MainMagazine.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Revista rev = (Revista) session.get(Revista.class, revistaID);
			session.delete(rev);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	}


