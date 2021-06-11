public class TaxiSpot {

    // Variáveis
    public int codigo;
    public String nome;
    public String telefone;
    public String logradouro;
    public String numero;
    public double longitude;
    public double latitude;

    //Construtor
    public TaxiSpot(int codigo, String nome, String telefone, String logradouro, String numero, double longitude,
            double latitude) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.numero = numero;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    
}
