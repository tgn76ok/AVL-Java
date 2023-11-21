import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        Arvore arvore = new Arvore(new Elemento(entrada.nextInt(), "noma"));
        arvore.calcularBalanceamento();
        arvore = arvore.verificaBalanceamento();
        System.out.println(arvore.printArvore(0));

        while (true) {
            arvore = arvore.inserir(new Elemento(entrada.nextInt(), "nome"));
            arvore.calcularBalanceamento();
            arvore = arvore.verificaBalanceamento();

            Arvore tree = arvore.busca_elemento(0);
            System.out.println("--------------");
            System.out.println(tree.printArvore(0));
            System.out.println("--------------");
            System.out.println(arvore.printArvore(0));
        }

    }
}
