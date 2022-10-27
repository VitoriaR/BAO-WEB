package BOAProjeto.BancoDeDados.entities;


public class Universal {

	
	public int id_universal;
	public int id_tipo;
	public int capitulos;
	public String nome;
	public String nome_alternativo;
	public String sinopse;
	public String status;
	public String link;
	
	public int getId_universal() {
		return id_universal;
	}
	public void setId_universal(int id_universal) {
		this.id_universal = id_universal;
	}
	public int getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}
	public int getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(int capitulos) {
		this.capitulos = capitulos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome_alternativo() {
		return nome_alternativo;
	}
	public void setNome_alternativo(String nome_alternativo) {
		this.nome_alternativo = nome_alternativo;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
