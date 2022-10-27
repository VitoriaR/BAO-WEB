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

import BOAProjeto.BancoDeDados.entities.Escritor;
import conection.BDconeccao;


public class DaoEscritor {

	private Connection connection = null;
	public DaoEscritor() {
		connection = BDconeccao.getConnection();
	}
	
	public void AdicionarEscritor(Escritor escritor) throws SQLException {
		
		try {
		PreparedStatement preparastmt = 
				connection.prepareStatement("insert into escritor(nome,sexo,producao ) values(?,?,?)");
		
		
		preparastmt.setString(2, escritor.getNome());
		preparastmt.setString(3,escritor.getSexo());
		preparastmt.setString(4, escritor.getProducao());
		preparastmt.executeUpdate();
		
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
	}
	
	public void DeleteEscritor(int escritorid) {
		try {
		PreparedStatement preparadelete = connection.prepareStatement("delete from escritor where id=?");
		preparadelete.setInt(1, escritorid);
		preparadelete.executeUpdate();
		}
		catch (SQLException exc) {
			exc.printStackTrace();
		} 
	}
	
	public void UpdateEscritor(Escritor escritor) {
		try {
			PreparedStatement preparaupdate = 
					connection.prepareStatement("update escritor set nome=?,  sexo=?, producao=?" + "where id=?");
			preparaupdate.setString(2, escritor.getNome());
			preparaupdate.setString(3, escritor.getSexo());
			preparaupdate.setString(4, escritor.getProducao());
			preparaupdate.setInt(1, escritor.getId_esc());
			preparaupdate.executeUpdate();
			
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
	}
	
	public List<Escritor> getallEscritor(){
		List<Escritor> listaescritor = new ArrayList<Escritor>();
		try {
			Statement lista = connection.createStatement();
			ResultSet rs = lista.executeQuery("select * from escritor");
			while(rs.next()) {
				Escritor escritor = new Escritor();
				escritor.setId_esc(rs.getInt("id"));
				escritor.setNome(rs.getString("nome"));
				escritor.setSexo(rs.getString("sexo"));
				escritor.setProducao(rs.getString("producao"));
				listaescritor.add(escritor);
			}
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
		return listaescritor;
	}
	
	public Escritor getEscritorByid(int escritorid) throws IOException, SQLException {
		Escritor escritor = new Escritor();
		
		PreparedStatement busca=connection.prepareStatement("select * from escritor where id = ?");
		busca.setInt(1, escritorid);
		ResultSet rs = busca.executeQuery();
		
		if(rs.next()) {
			escritor.setNome(rs.getString("nome"));
			escritor.setId_esc(rs.getInt("id"));
			escritor.setProducao(rs.getString("producao"));
		}
		return escritor;
			
	}
	
	public String getescritornomeescritorByTipo(int idescritor) throws SQLException {
		PreparedStatement dic =connection.prepareStatement("select nome from escritor where id = ?");
		String nome = null;
		dic.setInt(1, idescritor);
		System.out.println(idescritor);
		ResultSet rs=dic.executeQuery();
		if(rs.next()) { 
		 nome=rs.getString("nome");
		}
		return nome;
		
	}
	
	
}
