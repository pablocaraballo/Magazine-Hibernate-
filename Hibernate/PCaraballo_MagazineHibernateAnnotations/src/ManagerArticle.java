import java.io.IOException;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entities.Article;
//import Entities.Employee;

public class ManagerArticle {

	/*private static SessionFactory factory;

	public static void main(String[] args) throws IOException {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		FileAccessor fa;
		fa = new FileAccessor();
		fa.readArticlesFile("articles.txt");
	}*/

	/* Method to CREATE an Autor in the database */
	public void addArticle(Article article) {
		Session session = MainMagazine.getFactory().openSession();
		Transaction tx = null;
		Integer ArticleID = null;
		try {
			tx = session.beginTransaction();

			ArticleID = (Integer) session.save(article);
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
	public void listArtcile() {
		Session session = MainMagazine.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List articles = session.createQuery("FROM Article").list();

			for (Iterator iterator = articles.iterator(); iterator.hasNext();) {
				Article autor = (Article) iterator.next();
				System.out.println(articles.toString());
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
	public void updateArticle(Integer ID, String titulo) {
		Session session = MainMagazine.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Article article = (Article) session.get(Article.class, ID);
			article.setTitol(titulo);;
			session.update(article);
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
	public void deleteArticle(Integer ArticleID) {
		Session session = MainMagazine.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Article article = (Article) session.get(Article.class, ArticleID);
			session.delete(article);
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
