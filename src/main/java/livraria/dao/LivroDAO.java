package livraria.dao;

import java.util.List;

import org.hibernate.query.Query;

import livraria.entity.Livro;
import webf.dao.DAO;
import webf.dao.DAOException;

public class LivroDAO extends DAO<Livro> {

	public LivroDAO() {
		super(Livro.class);
	}

	/**
	 * Obtém os livros com determinado título (parte dele)
	 * 
	 * @param titulo
	 * @return List<Livro>
	 * @throws DAOException
	 */
	public List<Livro> getLivrosByTitulo(String titulo) throws DAOException {
		Query<Livro> query = query("FROM Livro l WHERE upper(l.titulo) like :titulo ORDER BY l.titulo");
		query.setParameter("titulo", "%" + titulo.toUpperCase() + "%");
		return query.getResultList();
	}

	/**
	 * Obtém os livros com determinado autor (parte dele)
	 * 
	 * @param autor
	 * @return List<Livro>
	 * @throws DAOException
	 */
	public List<Livro> getLivrosByAutor(String autor) throws DAOException {
		Query<Livro> query = query("FROM Livro l WHERE upper(l.autor) like :autor ORDER BY l.autor ");
		query.setParameter("autor", "%" + autor.toUpperCase() + "%");
		return query.getResultList();
	}

	/**
	 * Obtém o livro com determinado ID
	 * 
	 * @param id
	 * @return Livro
	 * @throws DAOException
	 */
	public Livro getLivroById(Integer id) throws DAOException {
		Query<Livro> query = query("FROM Livro l WHERE l.id = :id");
		query.setParameter("id", id);
		return query.uniqueResult();
	}

	/**
	 * Obtém todos os livros cadastrados, ordenados por título
	 * 
	 * @return List<Livro>
	 * @throws DAOException
	 */
	public List<Livro> getLivros() throws DAOException {
		return list("FROM Livro l ORDER BY l.titulo");
	}

	/**
	 * Verifica se determinado livro faz parte de algum pedido
	 * 
	 * @param livroId
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean isLivroEmPedido(Integer livroId) throws DAOException {
		Query<Long> query = query("SELECT COUNT(*) FROM Pedido p INNER JOIN p.livros AS l WHERE l.id = :livroId",
				Long.class);
		query.setParameter("livroId", livroId);
		Long count = query.uniqueResult();
		return count > 0;
	}
}
