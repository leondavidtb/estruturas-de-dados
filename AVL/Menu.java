/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import java.util.Scanner;

/**
 *
 * @author leon
 */
public class Menu {

    Scanner in = new Scanner(System.in);
    Avl avl = new Avl();

    public void telaInicial() {
        System.out.println("\n ----------MENU----------");
        System.out.println("| 1- Inserir elemento    |");
        System.out.println("| 2- Remover elemento    |");
        System.out.println("| 3- Mostrar AVL         |");
        System.out.println("| 4- Sair                |");
        System.out.println(" ------------------------");
        System.out.println("Digite uma opção: ");
    }

    public void inserirElemento() {
        int valor, i = 1;
        System.out.println("\nDigite o numero '999' para sair da inserção.");
        while (true) {
            System.out.print("\nDigite o " + i + "º valor: ");
            valor = in.nextInt();
            if (valor != 999) {
                avl.inserir(valor);
                System.out.println("-----------------------");
                avl.imprimir(avl.getRaiz());
                System.out.println("-----------------------");
                i++;
            } else {
                break;
            }
        }
    }

    public void removerElemento() {
        System.out.println("\nDigite o elemento a ser removido: ");
        //int valor;
        while (true) {
            //valor = in.nextInt();
            avl.remover(in.nextInt());
            break;
        }
    }

    public void mostrarElemento() {
        System.out.println("\n\nÁrvore AVL: ");
        System.out.println("Raiz: " + avl.getRaiz().getChave());
        avl.imprimir(avl.getRaiz());
    }

    public void gerarMenu() {
        String op = null;

        do {
            this.telaInicial();

            op = in.next();

            switch (op) {
                case "1":
                    this.inserirElemento();
                    break;
                case "2":
                    this.removerElemento();
                    break;
                case "3":
                    this.mostrarElemento();
                    break;
                case "4":
                    System.out.println("Aplicação finalizada!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (op != "4");
    }
}
