
import java.util.InputMismatchException;

public class App {

    //Script desenvolvido dia 10/06/2021 e finalizado 11/06/2021 as 05:05 horas:minutos
    //Autoria Eric Carlotto - Aluno no curso de Sistemas de informação na universidade PUCRS
    //Agradecimentos a equipe da DELL por me propor este desafio, onde obtive um grande avanço em meu conhecimentos práticos 
    public static void main(String[] args){
        
        //Inicia o menu ou trata a exceção de erro de entrada
        try {
            Menu.startMenu();
        //Tratamento da exceção de entrada inválida e resetando o menu caso ocorra a exceção    
        } catch (InputMismatchException e) {
            System.out.println("Só serão aceitos números entre 1 e 5.");           
            System.out.println("E a latitude e longitude só serão aceitos números com vírgula ex: -30,4305431");
            System.out.println("Tente novamente!");
            Menu.startMenu();
        }
    }
}
