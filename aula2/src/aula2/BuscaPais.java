package aula2;

import java.sql.SQLException;
import java.sql.Connection;

public class BuscaPais {
	
	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			
			ConexaoBD bd1 = new ConexaoBD();
			conn = bd1.conectar();
			
			bd1.carregar(conn);
			System.out.println(bd1.getNome());
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

