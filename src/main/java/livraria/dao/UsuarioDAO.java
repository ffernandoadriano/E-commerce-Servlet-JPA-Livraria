package livraria.dao;

import java.util.List;

import org.hibernate.query.Query;

import livraria.entity.Usuario;
import webf.dao.DAO;
import webf.dao.DAOException;

public class UsuarioDAO extends DAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	/**
	 * Verifica se um usuário existe, com base no seu e-mail
	 * 
	 * @param email
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean existeUsuario(String email) throws DAOException {
		Query<Long> query = query("SELECT COUNT(*) FROM Usuario u WHERE upper(u.email) = :email", Long.class);
		query.setParameter("email", email.toUpperCase());
		List<Long> results = query.list();
		Long count = results.get(0);
		return count > 0;
	}

	/**
	 * Obtém um usuário com base no e-mail
	 * 
	 * @param email
	 * @return Usuario
	 * @throws DAOException
	 */
	public Usuario getUsuarioByEmail(String email) throws DAOException {
		Query<Usuario> query = query("FROM Usuario u WHERE upper(u.email) = :email");
		query.setParameter("email", email.toUpperCase());
		return query.uniqueResult();

	}

}
