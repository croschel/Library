//talkit.com.br/author/joaocunha

package br.com.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.util.List;
import br.com.livraria.dao.DAO;
import br.com.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {
	
	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null) {
		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		}else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		
		this.autor = new Autor();
	}
	
	public List<Autor> getAutores(){ //coloca os autores em uma lista ordenada
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void carregar(Autor autor) {
		this.autor = autor;
	}
	
	public void deletar(Autor autor) {
		System.out.println("Autor "+autor.getNome()+" está sendo deletado");
		new DAO<Autor>(Autor.class).remove(autor);
	}
}
