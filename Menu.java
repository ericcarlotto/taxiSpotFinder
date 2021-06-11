import java.sql.Time;
import java.util.Scanner;

public class Menu {
    public static void startMenu() {

        //Inicialização da latitude e longitude com valores default
        Double latitude = 0.0;
        Double longitude = 0.0;
        
        // Looping de execução do menu
        for(int i = 0 ; i < 1;){

            //Mostra o menu
            System.out.println("===========MENU====================");
            System.out.println("1 - Listar todos os pontos de táxi");
            System.out.println("2 - Informar minha localização");
            System.out.println("3 - Encontrar pontos próximos");
            System.out.println("4 - Buscar pontos por logradouro");
            System.out.println("5 - Terminar o programa");
            System.out.println("===================================");
            System.out.println("");

            //Espera dois segundos
            Timer.timer(2);

            //Entrada do usuário
            System.out.println("Digite a opção desejada: ");

            //Intancia um scanner para leitura das interações com o usuário do sistema
            Scanner sc = new Scanner(System.in);
    
            //Lê a entrada
            int leitor = sc.nextInt();

            //Seleciona a opção conforme a escolha do usuário
            switch (leitor) {
                case 1:
                    //Lista todos os pontos de taxi do arquivo Csv
                    System.out.println("Os pontos de táxi disponíveis são:");
                    
                    //Simulação de busca em banco de dados
                    System.out.println("Carregando...");

                    //Espera um segundo
                    Timer.timer(1);

                    //Mostra todos pontos de táxi do arquivo csv
                    CsvReader.showAllSpots();
                    break;

                case 2:
                    //Informa localização do usuário

                    //Lê a localização do usuário
                    System.out.println("Informe sua localização: ");

                    //Lê a latitude da localização
                    System.out.print("Digite sua latitude: ");
                    latitude = sc.nextDouble();

                    //Lê a longitude da localização
                    System.out.print("Digite sua longitude: ");
                    longitude = sc.nextDouble();

                    //Espera um segundo
                    Timer.timer(1);

                    //Resposta para o usuário
                    System.out.println("Localização armazenada.");
                    break;

                case 3:
                    //Encontra ponto próximos a localização do usuário

                    //Caso o usuário não tenha informado nenhuma latitude e longitude o programa instrui para que seja informado ao sistema 
                    if(latitude == 0.0 && longitude == 0.0){
                        System.out.println("Você deve informar a sua latitude e a sua longitude.");
                        System.out.println("Escolha a opção 2 do menu inicial. Obrigado!");
                        Timer.timer(1);
                    }else{
                        //Caso o usuário tenha informado a latitude e longitude do mesmo, executa a opção 3 normalmente
                        CsvReader.haversineAplication(latitude, longitude);
                    }                              

                    break;

                case 4:
                    //Busca pontos por logradouro
                    
                    //Mostra os pontos de táxi perto do local escolhido
                    CsvReader.showSpotInSamePublicPlace();

                    break;
                
                case 5:
                    //Finaliza o sistema
                    System.out.println("Obrigado por usar o Taxi App, volte sempre!");
					i = 1;
                    break;
                  
                default:
                    //Opção caso o usuário digite um número inválido
                    System.out.println("Digite um número válido, só serão aceitos números entre 1 e 5!");
                    break;
            }
        }
    }
}
