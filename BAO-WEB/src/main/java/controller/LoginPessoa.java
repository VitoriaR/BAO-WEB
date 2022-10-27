package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BOAProjeto.BancoDeDados.entities.Pessoa;
import dao.DaoPessoa;

@WebServlet(name="LoginPessoa", urlPatterns= {"login/html;charset=UTF-8"})
	public class LoginPessoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoPessoa dao= null;
	
  public  LoginPessoa() {
	dao= new DaoPessoa();
}

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		  throws ServletException, IOException {

	response.getWriter().append("Served at: ").append(request.getContextPath());
}
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter print = response.getWriter();
	Pessoa pessoa = new Pessoa();
	
	String nome = request.getParameter("nome");
	System.out.println(nome);
	String senha = request.getParameter("senha");
	pessoa=dao.getLogin(nome, senha);
	
	if(pessoa.getNome()!=null && pessoa.getSenha()!=null && !usuario.getNome().isEmpty() && !usuario.getSenha().isEmpty()) {
		HttpSession session =request.getSession();
		session.setAttribute("usuario", nome);
	}else {
		response.sendRedirect("Error.html");
	}
}

}