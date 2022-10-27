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

import BOAProjeto.BancoDeDados.entities.Categoria;
import conection.BDconeccao;

public class DaoCategoria {
	
	private Connection connection = null;
	public DaoCategoria() {
		connection = BDconeccao.getConnection();
	}
	
public void AdicionarPessoa(Categoria categoria) throws SQLException {
		
		try {
		PreparedStatement preparastmt = 
				connection.prepareStatement("insert into categoria(nome ) values(?)");
		
		
		preparastmt.setString(2, categoria.getNome());
		preparastmt.executeUpdate();
		
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
		
}

public void DeleteCategoria(int categoriaid) {
	try {
	PreparedStatement preparadelete = connection.prepareStatement("delete from usuario where id=?");
	preparadelete.setInt(1, categoriaid);
	preparadelete.executeUpdate();
	}
	catch (SQLException exc) {
		exc.printStackTrace();
	} 
}

public void UpdateCategoria(Categoria categoria) {
	try {
		PreparedStatement preparaupdate = 
				connection.prepareStatement("update categoria set nome=?" + "where id=?");
		preparaupdate.setString(2, categoria.getNome());
		preparaupdate.setInt(1, categoria.getId());
		preparaupdate.executeUpdate();
		
	}catch (SQLException exc) {
		exc.printStackTrace();
	} 
}

public List<Categoria> getallCategoria(){
	List<Categoria> listacategoria = new ArrayList<Categoria>();
	try {
		Statement lista = connection.createStatement();
		ResultSet rs = lista.executeQuery("select * from categoria");
		while(rs.next()) {
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("id"));
			categoria.setNome(rs.getString("nome"));
			listacategoria.add(categoria);
		}
	}catch (SQLException exc) {
		exc.printStackTrace();
	} 
	return listacategoria;
}

public Categoria getCategoriaByid(int usuarioid) throws IOException, SQLException {
	Categoria categoria = new Categoria();
	
	PreparedStatement busca=connection.prepareStatement("select * from categoria where id = ?");
	busca.setInt(1, usuarioid);
	ResultSet rs = busca.executeQuery();
	
	if(rs.next()) {
		categoria.setNome(rs.getString("nome"));
		categoria.setId(rs.getInt("id"));
	}
	return categoria;	
}

public String getusuarionomeusuarioByTipo(int idcategoria) throws SQLException {
	PreparedStatement dic =connection.prepareStatement("select tipo from categoria where id = ?");
	String nome = null;
	dic.setInt(1, idcategoria);
	System.out.println(idcategoria);
	ResultSet rs=dic.executeQuery();
	if(rs.next()) { 
	 nome=rs.getString("nome");
	}
	return nome;
	
}


}
