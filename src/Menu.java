public class Menu {
    public void MenuPrincipal() {

        System.out.println("Aluno:Thiago Germano  ");
        System.out.println("Disiplina: Estruturas de Dados II");
        System.out.println("Professor: Walter Travassos Sarinho");
        System.out.println("Arvore AVL");

        System.out.println("---------------------------------------------------");
        System.out.println("|1- INSERIR           - fornecer RGM e Nome       |");
        System.out.println("|2- REMOVER UM NÓ     - fornecer o RGM a remover  |");
        System.out.println("|3- PESQUISAR         - fornecer o RGM a pesquisar|");
        System.out.println("|4- ESVAZIAR A ÁRVORE -                           |");
        System.out.println("|5- EXIBIR A ARVORE   - tres opções:PRÉ,IN ou PÓS |");
        System.out.println("|0- EXIT                                          |");
        System.out.println("---------------------------------------------------");
        System.out.println("                 DIGITE SUA OPÇÃO                 ");
    }

    public void ElementoNaoEncotrado() {
        System.out.println("-------------------------------------------------");
        System.out.println("|           ELEMENTO NÃO ENCONTRADO             |");
        System.out.println("-------------------------------------------------");

    }

    public void ElementoEncotrado() {
        System.out.println("-------------------------------------------------");
        System.out.println("|             ELEMENTO ENCONTRADO               |");
        System.out.println("-------------------------------------------------");

    }

    public void MenuSegundario() {
        System.out.println("--------------------");
        System.out.println("|1- PRÉ-ORDEM       |");
        System.out.println("|2- IN -ORDEM       |");
        System.out.println("|3- PÓS-ORDEM       |");
        System.out.println("|4- GRAFICAMENTE    |");
        System.out.println("--------------------");

    }
}
