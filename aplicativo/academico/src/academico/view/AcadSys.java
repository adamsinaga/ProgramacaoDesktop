package academico.view;

import academico.model.AcademicoDAO;
import academico.model.vo.Estudante;

public class AcadSys {
	public static void main(String[] args) {
		System.out.println("Instanciando a classe de acesso a dados");
		AcademicoDAO acad = new AcademicoDAO();
//		System.out.println("Criando a instância referente ao estudante");
//		Estudante est = new Estudante();
//		System.out.println("populando o objeto aluno com informações do estudante");
//		est.setNome("João Marcos da Silva");
//		est.setCurso("Sistemas para Internet");
//		est.setDepartamento("Depto de Computação");
//		System.out.println("Persistindo os dados no banco");
//		acad.save(est);
//		System.out.println("Apagando um estudante registrado");
//		acad.deleteById(10);
		for (int i = 0; i < acad.getEstudantes().size(); i++) {
			System.out.println("Código do estudante: "+acad.getEstudantes().get(i).getId());
			System.out.println("Nome: "+acad.getEstudantes().get(i).getNome());
			System.out.println("Curso: "+acad.getEstudantes().get(i).getCurso());
			System.out.println("Departamento: "+acad.getEstudantes().get(i).getDepartamento());
		}
	}
}
