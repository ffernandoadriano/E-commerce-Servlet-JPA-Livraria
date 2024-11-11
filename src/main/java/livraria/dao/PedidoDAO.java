package livraria.dao;

import java.util.List;

import org.hibernate.query.Query;

import livraria.entity.Pedido;
import livraria.entity.Usuario;
import webf.dao.DAO;
import webf.dao.DAOException;

public class PedidoDAO extends DAO<Pedido> {

	protected PedidoDAO() {
		super(Pedido.class);
	}

	/**
	 * Obtém o maior sequencial de número de pedido
	 * 
	 * @return int
	 * @throws DAOException
	 *
	 */
	public int getMaxNumPedido() throws DAOException {
		List<String> result = list("SELECT MAX(p.id) FROM Pedido p", String.class);

		String max = result.get(0);

		if (max == null) {
			// Não existem pedidos cadastrados
			return 0;
		}

		return Integer.parseInt(max);
	}

	/**
	 * Obtém os pedidos atrelados a um usuário
	 * 
	 * @param usuario
	 * @return List<Pedido>
	 * @throws DAOException
	 */

	public List<Pedido> getPedidosByUsuario(Usuario usuario) throws DAOException {
		Query<Pedido> query = query("FROM Pedido p WHERE p.usuario.id = :usuarioId ORDER BY p.data DESC");
		query.setParameter("usuarioId", usuario.getId());
		return query.getResultList();
	}

	/**
	 * Obtém os pedidos que estão com determinado status
	 * 
	 * @param status
	 * @return List<Pedido>
	 * @throws DAOException
	 */
	public List<Pedido> getPedidosByStatus(int status) throws DAOException {
		Query<Pedido> query = query("FROM Pedido p WHERE p.status = :status ORDER BY p.data");
		query.setParameter("status", status);
		return query.getResultList();
	}
}
