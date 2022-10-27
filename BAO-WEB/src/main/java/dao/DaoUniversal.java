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

import BOAProjeto.BancoDeDados.entities.Universal;
import conection.BDconeccao;


	public class DaoUniversal {
	
	private Connection connection = null;
	public DaoUniversal() {
		connection = BDconeccao.getConnection();
	}
	
	public void AdicionarUniversal(Universal universal) throws SQLException {
		
		try {
		PreparedStatement preparastmt = 
				connection.prepareStatement("insert into universal(nome,titulo_alternativo, capitulos, status, sinopse, id_tipo,link ) values(?,?,?,?,?,?,?)");
		
		
		preparastmt.setString(1, universal.getNome());
		preparastmt.setString(2, universal.getNome_alternativo());
		preparastmt.setInt(3, universal.getCapitulos());
		preparastmt.setString(4, universal.getStatus());
		preparastmt.setString(5, universal.getSinopse());
		preparastmt.setInt(6, universal.getId_tipo());
		preparastmt.setInt(7, universal.getId_universal());
		preparastmt.setString(8, universal.getLink());
		preparastmt.executeUpdate();
		
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
	}
	
	public void DeleteUniversal(int universalid) {
		try {
		PreparedStatement preparadelete = connection.prepareStatement("delete from universal where id=?");
		preparadelete.setInt(1, universalid);
		preparadelete.executeUpdate();
		}
		catch (SQLException exc) {
			exc.printStackTrace();
		} 
	}
	
	public void UpdateUniversal(Universal universal) {
		try {
			PreparedStatement preparaupdate = 
					connection.prepareStatement("update universal set nome=?, titulo_alternativo=?, capitulos=?, status=?, sinopse=?, id_tipo=?, link=?" 
			+ "where id=?");
			preparaupdate.setString(1, universal.getNome());
			preparaupdate.setString(2, universal.getNome_alternativo());
			preparaupdate.setInt(3, universal.getCapitulos());
			preparaupdate.setString(4, universal.getStatus());
			preparaupdate.setString(5, universal.getSinopse());
			preparaupdate.setInt(6, universal.getId_tipo());
			preparaupdate.setInt(7, universal.getId_universal());
			preparaupdate.setString(8, universal.getLink());
			preparaupdate.executeUpdate();
			
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
	}
	
	public List<Universal> getallUniversal(){
		List<Universal> listauniversal = new ArrayList<Universal>();
		try {
			Statement lista = connection.createStatement();
			ResultSet rs = lista.executeQuery("select * from universal");
			while(rs.next()) {
				Universal universal = new Universal();
				universal.setId_universal(rs.getInt("id"));
				universal.setNome(rs.getString("nome"));
				universal.setNome_alternativo(rs.getString("nome_alternativo"));
				universal.setCapitulos(rs.getInt("capitulos"));
				universal.setStatus(rs.getString("status"));
				universal.setSinopse(rs.getString("sinopse"));
				universal.setId_tipo(rs.getInt("id_tipo"));
				universal.setId_universal(rs.getInt("id"));
				universal.setLink(rs.getString("link"));
				listauniversal.add(universal);
			}
		}catch (SQLException exc) {
			exc.printStackTrace();
		} 
		return listauniversal;
	}
	
	public Universal getUsuarioByid(int universalid) throws IOException, SQLException {
		Universal universal = new Universal();
		
		PreparedStatement busca=connection.prepareStatement("select * from universal where id = ?");
		busca.setInt(1, universalid);
		ResultSet rs = busca.executeQuery();
		
		if(rs.next()) {
			universal.setNome(rs.getString("nome"));
			universal.setId_universal(rs.getInt("id"));
			universal.setId_tipo(rs.getInt("id_tipo"));
		}
		return universal;	
	}
	
	public Integer getuniversalonomeuniversaloByName(String nomeuniversal) throws SQLException {
		PreparedStatement dic =connection.prepareStatement("select id from universal where nome = ?");
		dic.setString(1, nomeuniversal);
		System.out.println(nomeuniversal);
		ResultSet rs=dic.executeQuery();
		if(rs.next()) { 
		return rs.getInt("id");
		}
		return 0;
		
	}
	
	public String getuniversalnomeuniversalByTipo(int iduniversal) throws SQLException {
		PreparedStatement dic =connection.prepareStatement("select tipo from universal where id = ?");
		String tipo = null;
		dic.setInt(1, iduniversal);
		System.out.println(iduniversal);
		ResultSet rs=dic.executeQuery();
		if(rs.next()) { 
		 tipo=rs.getString("tipo");
		}
		return tipo;
		
	}
}
