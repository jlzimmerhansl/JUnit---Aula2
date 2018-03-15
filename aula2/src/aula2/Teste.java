package aula2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;



public class Teste {


	public static void main(String[] args) {
		Connection conn = null;
		
		
		try {
			
			ConexaoBD bd = new ConexaoBD();
			conn = bd.conectar();		
			
			Pais pais = new Pais();
			
			Pais paisretorno = new Pais();
			
			ArrayList<Pais> vetor = new ArrayList<Pais>();
			
			paisretorno = pais.maiorPopulacao(conn);
			System.out.println(paisretorno);
	       
			paisretorno = pais.menorPopulacao(conn);
			System.out.println(paisretorno);
			
			vetor = pais.vetor(conn);
			
			for(Pais v:vetor) {
				System.out.println(v);
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
			if(conn != null) {
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					System.out.println(e1.getStackTrace());
				}
			}
		}
		finally {
			
			if(conn != null){
				try {
					conn.close();
				}
				catch(SQLException e1){
					System.out.println(e1.getStackTrace());
				}	
			}
		}
	}

}
