package br.com.fiap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.bean.LivrosBeanRemote;
import br.com.fiap.entity.Livros;

@WebServlet("/livros")
public class ServletLivros extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {
			InitialContext ctx = new InitialContext();
			LivrosBeanRemote service = (LivrosBeanRemote) ctx.lookup("ejb:/03_LivrosEJB/LivrosBean!br.com.fiap.bean.LivrosBeanRemote");
			List<Livros> lista = service.getAll();
			request.setAttribute("lista", lista);
			request.getRequestDispatcher("lista.jsp").forward(request, response);
		} catch (NamingException e) {
			out.print(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
