//talkit.com.br/author/joaocunha

package br.com.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.util.List;
import br.com.livraria.dao.DAO;
import br.com.livraria.dao.UsuarioDao;
import br.com.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }
    
    public String realizarLogin() {
    	System.out.println("Realizando o login do usuario"+this.usuario.getEmail());
    	
    	boolean existe = new UsuarioDao<>().existe(this.usuario);
    	
    	if(existe) {
    		return "livro?faces-redirect=true";
    	}else {
    		return null;
    	}
    
    }
	}
