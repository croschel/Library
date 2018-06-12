
package br.com.livraria.bean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

		FacesContext context = FacesContext.getCurrentInstance();

    	boolean existe = new UsuarioDao<>().existe(this.usuario);
    	
    	if(existe) {
    		context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario); // utilizando FacesContext para pegar um atributo do servlet que de fato é o usuario logado
    		return "livro?faces-redirect=true";
    	}
    		context.getExternalContext().getFlash().setKeepMessages(true);
    		context.addMessage(null, new FacesMessage("Usuario não encontrado"));
    	
    		return "login?faces-redirect=true";
    	}
    public String deslogar() {
    	
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado"); 

    	
    	return "login?faces-redirect=true";
    }
    public String cadastroForm() { //método para redirecionar o fluxo da página web
		System.out.println("Chamando o formulario de cadastro");
		return "usuario?faces-redirect=true";
	}
  
    }
	
