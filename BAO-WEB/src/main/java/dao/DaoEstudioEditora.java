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

import BOAProjeto.BancoDeDados.entities.EstudioEditora;
import conection.BDconeccao;



public class DaoEstudioEditora {

	
	private Connection connection = null;
	public DaoEstudioEditora() {
		connection = BDconeccao.getConnection();
	}
	
	public void AdicionarEstudioEditora(EstudioEditora estudioEditora) throws SQLException {
		
		try {
		PreparedStatement preparastmt = 
				connection.prepareStatement("insert into estudio_editora(nome,tipo_id,producao ) values(?,?,?,?)");
		
		
		preparastmt.setString(1, estudioEditora.getNome());
		preparastmt.setString(3,estudioEditora.getProducao());
		preparastmt.setString(2, estudioEditora.getTipo_id());
		preparastmt.executeUpdate();
		
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
	}
	
	public void DeleteEstudioEditora(int estudioEditoraid) {
		try {
		PreparedStatement preparadelete = connection.prepareStatement("delete from estudio_editora where id=?");
		preparadelete.setInt(1, estudioEditoraid);
		preparadelete.executeUpdate();
		}
		catch (SQLException exc) {
			exc.printStackTrace();
		} 
	}
	
	public void UpdateEstudioEditora(EstudioEditora estudioEditora) {
		try {
			PreparedStatement preparaupdate = 
					connection.prepareStatement("update estudio_editora set nome=?, tipo_id=?, producao=?" 
			+ "where id=?");
			preparaupdate.setString(1, estudioEditora.getNome());
			preparaupdate.setString(3, estudioEditora.getProducao());
			preparaupdate.setString(2, estudioEditora.getTipo_id());
			preparaupdate.executeUpdate();
			
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
	}
	
	public List<EstudioEditora> getallEstudioEditora(){
		List<EstudioEditora> listaestudioEditora = new ArrayList<EstudioEditora>();
		try {
			Statement lista = connection.createStatement();
			ResultSet rs = lista.executeQuery("select * from usuario");
			while(rs.next()) {
				EstudioEditora estudioEditora = new EstudioEditora();
				estudioEditora.setTipo_id(rs.getString("tipo_id"));
				estudioEditora.setNome(rs.getString("nome"));
				estudioEditora.setProducao(rs.getString("producao"));
				
				listaestudioEditora.add(estudioEditora);
			}
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
		return listaestudioEditora;
	}
	
	public EstudioEditora getEstudioEditoraByid(int estudioEditoraid) throws IOException, SQLException {
		EstudioEditora estudioEditora = new EstudioEditora();
		
		PreparedStatement busca=connection.prepareStatement("select * from estudio_editora where id = ?");
		busca.setInt(1, estudioEditoraid);
		ResultSet rs = busca.executeQuery();
		
		if(rs.next()) {
			estudioEditora.setNome(rs.getString("nome"));
			estudioEditora.setTipo_id(rs.getString("tipo_id"));
		}
		return estudioEditora;
		
	}
	
	public Integer getEstudioEditoraBnomeEstudioEditoraBByName(String nomeEstudioEditoraB) throws SQLException {
		PreparedStatement dic =connection.prepareStatement("select id from estudio_editora where nome = ?");
		dic.setString(1, nomeEstudioEditoraB);
		System.out.println(nomeEstudioEditoraB);
		ResultSet rs=dic.executeQuery();
		if(rs.next()) { 
		return rs.getInt("id");
		}
		return 0;
		
	}
}
