package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import BOAProjeto.BancoDeDados.entities.Pessoa;
import conection.BDconeccao;

public class DaoPessoa {

	private Connection connection = null;
	public DaoPessoa() {
		connection = BDconeccao.getConnection();
	}
	
public void AdicionarPessoa(Pessoa pessoa) throws SQLException {
		
		try {
		PreparedStatement preparastmt = 
				connection.prepareStatement("insert into pessoa(id,nome,email,senha ) values(?,?,?,?)");
		
		
		preparastmt.setString(2, pessoa.getNome());
		preparastmt.setString(3, pessoa.getEmail());
		preparastmt.setString(4, pessoa.getSenha());
		preparastmt.executeUpdate();
		
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
		
}
		public void DeletePessoa(int pessoaid) {
			try {
				PreparedStatement preparadelete = connection.prepareStatement("delete from usuario where id=?");
				preparadelete.setInt(1, pessoaid);
				preparadelete.executeUpdate();
	}
	catch (SQLException exc) {
		exc.printStackTrace();
	} 
}
		
		public void UpdatePessoa(Pessoa pessoa) {
			try {
				PreparedStatement preparaupdate = 
						connection.prepareStatement("update pessoa set nome=?, email=?, senha=?" + "where id=?");
				preparaupdate.setString(2, pessoa.getNome());
				preparaupdate.setString(3, pessoa.getEmail());
				preparaupdate.setString(4, pessoa.getSenha());
				preparaupdate.setInt(1, pessoa.getId());
				preparaupdate.executeUpdate();
				
			}catch (SQLException exc) {
				exc.printStackTrace();
			} 
		}
		
		public List<Pessoa> getallPessoa(){
			List<Pessoa> listapessoa = new ArrayList<Pessoa>();
			try {
				Statement lista = connection.createStatement();
				ResultSet rs = lista.executeQuery("select * from pessoa");
				while(rs.next()) {
					Pessoa pessoa = new Pessoa();
					pessoa.setId(rs.getInt("id"));
					pessoa.setNome(rs.getString("nome"));
					pessoa.setEmail(rs.getString("email"));
					pessoa.setSenha(rs.getString("senha"));
					listapessoa.add(pessoa);
				}
			}catch (SQLException exc) {
				exc.printStackTrace();
			} 
			return listapessoa;
		}
		
		public Pessoa getPessoaByid(int pessoaid) throws IOException, SQLException {
			Pessoa pessoa = new Pessoa();
			
			PreparedStatement busca=connection.prepareStatement("select * from pessoa where id = ?");
			busca.setInt(1, pessoaid);
			ResultSet rs = busca.executeQuery();
			
			if(rs.next()) {
				pessoa.setNome(rs.getString("nome"));
				pessoa.setId(rs.getInt("id"));
			}
			return pessoa;
				
		}
		
		public Pessoa getLogin(String nome, String senha) throws IOException {
			Pessoa pessoa = new Pessoa();
			System.out.println(nome);
			try {
				
				PreparedStatement busca =connection.prepareStatement("select * from pessoa where nome = ? and senha=?");
				busca.setString(1, nome);
				
			busca.setString(2, senha);
			ResultSet rs = busca.executeQuery();
			
			if(rs.next()) {
				
				
				pessoa.setNome(rs.getString("nome"));
				
				pessoa.setEmail(rs.getString("email"));
				pessoa.setSenha(rs.getString("senha"));
			}
			
			}catch (SQLException exc) {
				exc.printStackTrace();
			}
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getSenha());
			System.out.println(pessoa.getEmail());
			return pessoa;
		}
		
		public Integer getusuarionomeusuarioByName(String nomepessoa) throws SQLException {
			PreparedStatement dic =connection.prepareStatement("select id from pessoa where nome = ?");
			dic.setString(1, nomepessoa);
			System.out.println(nomepessoa);
			ResultSet rs=dic.executeQuery();
			if(rs.next()) { 
			return rs.getInt("id");
			}
			return 0;
			
		}
		
		
}
