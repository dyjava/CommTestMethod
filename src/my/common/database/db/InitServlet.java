package my.common.database.db;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
//			String configFile = request.getParameter("config");
//			System.out.println("======="+configFile);
//			configFile += request.getContextPath();
//			System.out.println("======="+configFile);
//			Const con = new Const();
//			con.load(configFile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init() throws ServletException {
		String configFile = this.getInitParameter("config");
//		System.out.println("======="+configFile);
		configFile = this.getServletContext().getRealPath(configFile);
//		System.out.println("======="+configFile);
//		������ݿ������ļ�.
		Const con = new Const();
		try {
			con.load(configFile);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
