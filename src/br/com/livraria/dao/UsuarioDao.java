package br.com.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import br.com.livraria.modelo.Usuario;

public class UsuarioDao<T> {


	public boolean existe(Usuario usuario) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = em.createQuery
				("select u from Usuario u where u.email = :pEmail"
						+ " and u.senha = :pSenha",Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try {
			Usuario resultado = query.getSingleResult();
		}catch(NoResultException ex) {
			return false;
		}
		
		em.close();

		return true;
	}


}
