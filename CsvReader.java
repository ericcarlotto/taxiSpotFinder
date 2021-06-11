import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvReader {
                
    //Mostra os pontos de táxi do csv lido
    public static void showAllSpots() {

        //Acessa o Csv
        Path path = Paths.get("pontos_taxi.csv");


        try {
            //Mostra todos os pontos de táxi
            Files.lines(path)
            			.skip(1)
            			.map(lin -> lin.split(";"))
            			.map(col -> new TaxiSpot(Integer.parseInt(col[1]),col[2],col[3],col[4],col[5],
            										Double.valueOf(col[6].replaceAll(",", ".")), Double.valueOf(col[7].replaceAll(",", "."))))
            			.forEach(t -> System.out.println("------- Ponto: -------"
            												+ "\nNome: " + t.nome
            												+ "\nTelefone: " + t.telefone
            												+ "\nLogradouro: " + t.logradouro
            												+ "\nNumero: " + t.numero
            												+ "\nCodigo: " + t.codigo
            												+ "\nLatitude: " + t.latitude
            												+ "\nLongitude: " + t.longitude
            												+ "\n"));
        //Tratamento da exceção de entrada/saida                                                    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Mostra os pontos de táxi do csv lido com filtro
    public static void showSpotInSamePublicPlace() {

        //Acessa o Csv
        Path path = Paths.get("pontos_taxi.csv");

        //Intancia um scanner para leitura das interações com o usuário do sistema
        Scanner sc = new Scanner(System.in);

        //Mostra os pontos de táxi pertos do logradouro escolhido do csv lido 
        try {

            //Lê o logradouro do usuário e converte tudo para maiúsculo
            System.out.println("Digite todo ou parte do nome do logradouro:");
            String logrUser = sc.nextLine().toUpperCase();
            
            //Mostra os pontos no mesmo local público informado pelo usuário
            Files.lines(path)
				.skip(1)
				.map(lin -> lin.split(";"))
				.map(col -> new TaxiSpot(Integer.parseInt(col[1]),col[2],col[3],col[4],col[5],
											Double.valueOf(col[6].replaceAll(",", ".")), Double.valueOf(col[7].replaceAll(",", "."))))
				.filter(t -> t.logradouro.contains(logrUser))
				.forEach(t -> System.out.println("------- Ponto: -------"
													+ "\nNome: " + t.nome
													+ "\nTelefone: " + t.telefone
													+ "\nLogradouro: " + t.logradouro
													+ "\nNumero: " + t.numero
													+ "\nCodigo: " + t.codigo
													+ "\nLatitude: " + t.latitude
													+ "\nLongitude: " + t.longitude
													+ "\n"));
        //Tratamento da exceção de entrada/saida    
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
    
    //Mostra os pontos de táxi do csv lido
    public static void haversineAplication(Double lat1, Double long1) {

        //Acessa o Csv
        Path path = Paths.get("pontos_taxi.csv");

        //Vetor para armazenar latitude e longitudes do cálculo
        ArrayList<Double> coordinatesLat = new ArrayList<>();
        
        ArrayList<Double> coordinatesLong = new ArrayList<>();

        ArrayList<Double> haversineValues = new ArrayList<>();

        //Variáveis para pegar as coordenadas dos pontos
        Double cord1 = 0.0;

        Double cord2 = 0.0;
        
        Double cord3 = 0.0;

        //Variáveis para pegar os 3 pontos mais próximos
        Double val1 = 100000.0;

        Double val2 = 100000.0;

        Double val3 = 100000.0;

        //Variáveis das coordenadas do usuário
        Double latUsu = lat1;

        Double longUsu = long1;

        //Variáveis das coordenadas dos pontos próximos ao usuário
        Double latSpot1 = 0.0;

        Double longSpot1 = 0.0;

        Double latSpot2 = 0.0;

        Double longSpot2 = 0.0;

        Double latSpot3 = 0.0;

        Double longSpot3 = 0.0;

        try {

            //Adiciona todas as latitudes
            Files.lines(path)
            			.skip(1)
            			.map(lin -> lin.split(";"))
            			.map(col -> new TaxiSpot(Integer.parseInt(col[1]),col[2],col[3],col[4],col[5],
            										Double.valueOf(col[6].replaceAll(",", ".")), Double.valueOf(col[7].replaceAll(",", "."))))
            			.forEach(t -> 
                            coordinatesLat.add(t.longitude)
                        );

            //Adiciona todas as longitudes
            Files.lines(path)
            			.skip(1)
            			.map(lin -> lin.split(";"))
            			.map(col -> new TaxiSpot(Integer.parseInt(col[1]),col[2],col[3],col[4],col[5],
            										Double.valueOf(col[6].replaceAll(",", ".")), Double.valueOf(col[7].replaceAll(",", "."))))
            			.forEach(t -> 
                            coordinatesLong.add(t.latitude)
                        );

            //Realiza a fórmula de haversine para todos pontos com intuito de achar os mais próximos
            for(int i = 0; i < coordinatesLat.size(); i++){
                haversineValues.add(Haversine.haversine(latUsu, longUsu, coordinatesLat.get(i), coordinatesLong.get(i)));
            }
            
            //Pega a menor distância da aplicação da fórmula de haversine
            for(Double value : haversineValues) {             
                if(value < val1){
                    val1 = value;
                }
            }

            //Pega a segunda menor distância da aplicação da fórmula de haversine
            for(int i = 0; i < haversineValues.size(); i++) {
                if(haversineValues.get(i) < val2){
                    if(val1 != haversineValues.get(i)){
                        val2 = haversineValues.get(i);
                    }
                }
            }

            //Pega a terceira menor distância da aplicação da fórmula de haversine
            for(int i = 0; i < haversineValues.size(); i++) {
                if(haversineValues.get(i) < val3){
                    if(val1 != haversineValues.get(i)){
                        if(val2 != haversineValues.get(i)){
                            val3 = haversineValues.get(i);
                        }
                    }
                }
            }

            //Realiza a fórmula de haversine novamente para aquisição da latitude e longitude do ponto mais próximo
            for(int i = 0; i < coordinatesLat.size(); i++){
                if(Haversine.haversine(latUsu, longUsu, coordinatesLat.get(i), coordinatesLong.get(i)) == val1){
                    latSpot1 = coordinatesLat.get(i);
                    longSpot1 = coordinatesLong.get(i);
                }                
            }

            //Realiza a fórmula de haversine novamente para aquisição da latitude e longitude do segundo ponto mais próximo
            for(int i = 0; i < coordinatesLat.size(); i++){
                if(Haversine.haversine(latUsu, longUsu, coordinatesLat.get(i), coordinatesLong.get(i)) == val2){
                    latSpot2 = coordinatesLat.get(i);
                    longSpot2 = coordinatesLong.get(i);
                }                
            }

            //Realiza a fórmula de haversine novamente para aquisição da latitude e longitude do terceiro ponto mais próximo
            for(int i = 0; i < coordinatesLat.size(); i++){
                if(Haversine.haversine(latUsu, longUsu, coordinatesLat.get(i), coordinatesLong.get(i)) == val3){
                    latSpot3 = coordinatesLat.get(i);
                    longSpot3 = coordinatesLong.get(i);
                }                
            }

            //Leitura do arquivo csv e separação em strings para manipulação com intuito de achar os pontos mais próximos
            //Realizando novamente a leitura pois tive a necessidade de trabalhar com a latitude e longitude em formato STRING
            String path1 = "pontos_taxi.csv";
            String line = "";

            //Conversão das latitudes para String
            String latSpotString1 = String.valueOf(latSpot1);
            String latSpotString2 = String.valueOf(latSpot2);
            String latSpotString3 = String.valueOf(latSpot3);

            //Conversão das longitudes para String
            String longSpotString1 = String.valueOf(longSpot1);
            String longSpotString2 = String.valueOf(longSpot2);
            String longSpotString3 = String.valueOf(longSpot3);

            try {
                //Instanciando um bufferedReader para leitura do arquivo CSV
                BufferedReader br = new BufferedReader(new FileReader(path1));

                //Leitura das linhas do arquivo
                while((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    
                    //Encontrando o ponto mais próximo 
                    if(values[6].replaceAll(",", ".").equals(latSpotString1) && values[7].replaceAll(",", ".").equals(longSpotString1)){
                        System.out.println("O primeiro ponto mais próximo de sua localização é: ");
                        System.out.println("====================================================");
                        System.out.println("");
                        System.out.println(values[2]);
                        System.out.println(values[3]);
                        System.out.println(values[4]);
                        System.out.println(values[5]);
                        System.out.println(values[6]);
                        System.out.println(values[7]);
                        System.out.println("====================================================");
                        System.out.println("");
                    }

                    //Encontrando o segundo ponto mais próximo
                    if(values[6].replaceAll(",", ".").equals(latSpotString2) && values[7].replaceAll(",", ".").equals(longSpotString2)){
                        System.out.println("O segundo ponto mais próximo de sua localização é: ");
                        System.out.println("====================================================");
                        System.out.println("");
                        System.out.println(values[2]);
                        System.out.println(values[3]);
                        System.out.println(values[4]);
                        System.out.println(values[5]);
                        System.out.println(values[6]);
                        System.out.println(values[7]);
                        System.out.println("====================================================");
                        System.out.println("");
                    }

                    //Por fim, encontrando o terceiro ponto mais próximo
                    if(values[6].replaceAll(",", ".").equals(latSpotString3) && values[7].replaceAll(",", ".").equals(longSpotString3)){
                        System.out.println("O terceiro ponto mais próximo de sua localização é: ");
                        System.out.println("====================================================");
                        System.out.println("");
                        System.out.println(values[2]);
                        System.out.println(values[3]);
                        System.out.println(values[4]);
                        System.out.println(values[5]);
                        System.out.println(values[6]);
                        System.out.println(values[7]);
                        System.out.println("====================================================");
                        System.out.println("");
                    }
                    
                }
            //Tratamaneto da exceção de arquivo não encontrado   
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        //Tratamento da exceção de entrada/saida
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

