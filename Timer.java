public class Timer {
    public static void timer(int time) {
        
        // Converte a entrada de milisegundos para segundos
        int timeSec = time*1000;

        // Executa o timer ou trata a exceção
        try {						
            Thread.sleep(timeSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
