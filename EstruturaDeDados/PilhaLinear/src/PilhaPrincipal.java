public class PilhaPrincipal {
	public static void main(String[] args) {
		System.out.println("==== PILHA LINEAR ====");
		Pilha P = new Pilha(); // Pilha de Inteiros
		Pilha alunos = new Pilha(); // Pilha de Strings
		int x1, x2;

		P.empilha(1);
		P.empilha(3);
		P.empilha(5);
		P.empilha(7);

		System.out.println("Apos insercoes: ");
		P.mostrar();

		x1 = (int) P.desempilha(); // Typecasting
		x2 = (int) P.desempilha();

		System.out.println("Apos remocoes de (" + x1 + ", " + x2 + ") sobraram os elementos:");
		P.mostrar();
		System.out.println("\n=====================================\n");

		alunos.empilha("Hugo");
		alunos.empilha("Pedro");
		alunos.empilha("Daniella");
		alunos.empilha("Ian");
		if (alunos.empilha("Isabel"))
			System.out.println("A aluna Isabel entrou na pilha de alunos.");
		else
			System.out.println("A aluna Isabel não entrou na pilha de alunos.");
		if (alunos.empilha("Leonardo"))
			System.out.println("O aluno Leonardo entrou na pilha de alunos.");
		else
			System.out.println("O aluno Leonardo não entrou na pilha de alunos.");

		alunos.mostrar();

		System.out.println("O último aluno a entrar na pilha foi " + alunos.desempilha());
		System.out.println("Na pilha restaram os seguintes alunos: ");
		alunos.mostrar();

	}
}