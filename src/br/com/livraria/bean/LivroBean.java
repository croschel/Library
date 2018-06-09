package br.com.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.livraria.dao.DAO;
import br.com.livraria.modelo.Autor;
import br.com.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;

	
	//getters and setters
	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Livro getLivro() {
		return livro;
	}
	//getters and setters
	
	public List<Livro> getLivros(){ //coloca os livros em um lista ordenada
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public List<Autor> getAutores(){ //coloca os autores em uma lista ordenada
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	public List<Autor> getAutoresDoLivro(){ //pega todos os autores linkados à algum livro
		return this.livro.getAutores();
	}
	
	public void gravarAutor() { //método que grava um autor em determinado livro
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Autor "+autor.getNome()+" Incluido na lista e aguardando ser linkado ao livro");
	}

	public void gravar() {//método que grava o livro junto a todas as informações recebidas
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			
			FacesContext.getCurrentInstance().addMessage("autor",new FacesMessage("Livro deve ter pelo menos um Autor")); // faces context foi utilizado para retornar msg de exceção
		}
		
		if(this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro); // chamada de objeto DAO para inserção no banco JPA

		}else {
			new DAO<Livro>(Livro.class).atualiza(livro);
		}
		
		this.livro = new Livro();	
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		//método especial para usando facesContext para retornar exceção caso difite um ISBN não iniciado com 1
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("o ISBN deveria começar com 1"));
		}
	}
	
	public String formAutor() { //método para redirecionar o fluxo da página web
		System.out.println("Chamando o formulario do Autor");
		return "autor?faces-redirect=true";
	}
	
	public void remover(Livro livro) {//remove o livro da lista utilizando um método do DAO
		System.out.println("Removendo o livro "+livro.getTitulo());
		new DAO<Livro>(Livro.class).remove(livro);
	}

	public void carregar(Livro livro) {//carregar o livro no formulário de preenchimento
		System.out.println("Carregando Livro "+livro.getTitulo());
		this.livro = livro; //o livro instânciado nessa classe é igual ao recebido pelo link alterar
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	public void carregarPorId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(livroId);
	}
}
