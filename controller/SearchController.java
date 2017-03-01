package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SearchInputDAO;
import to.BorrowerManagementTO;
import to.SearchInputTO;
import to.SearchOutputTO;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside search controller..");
		List<SearchOutputTO> searchList = new ArrayList<SearchOutputTO>();
		SearchInputTO sito= new SearchInputTO();
		sito.setIsbn(request.getParameter("isbn"));
		sito.setTitle(request.getParameter("title"));
		sito.setAuthors(request.getParameter("authors"));
		SearchInputDAO sdao = new SearchInputDAO();
		try {
			
			searchList = sdao.search(sito);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("searchList", searchList);
		request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
	}

}
