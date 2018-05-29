//talkit.com.br/author/joaocunha

package br.com.livraria.bean;

import javax.faces.bean.ViewScoped;

import java.util.List;
import br.com.livraria.dao.DAO;
import br.com.livraria.modelo.Autor;

@ViewScoped
public class AutorBean {
	
	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		this.autor = new Autor();
	}
	
	public List<Autor> getAutores(){ //coloca os autores em uma lista ordenada
		return new DAO<Autor>(Autor.class).listaTodos();
	}
}
