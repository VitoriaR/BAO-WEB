package BOAProjeto.BancoDeDados.entities;

public class Escritor {

	public String nome;
	public String sexo;
	public String producao;
	public int id_esc;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getProducao() {
		return producao;
	}
	public void setProducao(String producao) {
		this.producao = producao;
	}
	public int getId_esc() {
		return id_esc;
	}
	public void setId_esc(int id_esc) {
		this.id_esc = id_esc;
	}
	
	
}
