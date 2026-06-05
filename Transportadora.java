import java.util.Scanner;

interface Transporte {
    
    double calcularFrete(double peso, double altura, double largura, double distancia);
}

abstract class Veiculo implements Transporte {

    private int    anoFabricacao;
    private String marca;
    private String modelo;
    private String propulsao;


    public int getAnoFabricacao()               { return anoFabricacao; }
    public void setAnoFabricacao(int ano)        { this.anoFabricacao = ano; }

    public String getMarca()                     { return marca; }
    public void setMarca(String marca)           { this.marca = marca; }

    public String getModelo()                    { return modelo; }
    public void setModelo(String modelo)         { this.modelo = modelo; }

    public String getPropulsao()                 { return propulsao; }
    public void setPropulsao(String propulsao)   { this.propulsao = propulsao; }

    @Override
    public String toString() {
        return String.format("%s %s (%d) - Propulsão: %s", marca, modelo, anoFabricacao, propulsao);
    }
}


class VeiculoTerrestre extends Veiculo {

    private int    qtdeRodas;
    private int    qtdePortas;
    private String placa;
    private String chassi;

    public int getQtdeRodas()                  { return qtdeRodas; }
    public void setQtdeRodas(int qtdeRodas)    { this.qtdeRodas = qtdeRodas; }

    public int getQtdePortas()                 { return qtdePortas; }
    public void setQtdePortas(int qtdePortas)  { this.qtdePortas = qtdePortas; }

    public String getPlaca()                   { return placa; }
    public void setPlaca(String placa)         { this.placa = placa; }

    public String getChassi()                  { return chassi; }
    public void setChassi(String chassi)       { this.chassi = chassi; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        throw new UnsupportedOperationException("Use uma subclasse concreta de VeiculoTerrestre.");
    }
}

// ------------------------------------------------------------

class VeiculoAereo extends Veiculo {

    private String rab;           
    private int    qtdeMotores;

    public String getRab()                     { return rab; }
    public void setRab(String rab)             { this.rab = rab; }

    public int getQtdeMotores()                { return qtdeMotores; }
    public void setQtdeMotores(int qtde)       { this.qtdeMotores = qtde; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        throw new UnsupportedOperationException("Use uma subclasse concreta de VeiculoAereo.");
    }
}

// ------------------------------------------------------------

class VeiculoFluvial extends Veiculo {

    private String registroMarinha;
    private double boca;
    private double caladoAereo;

    public String getRegistroMarinha()                       { return registroMarinha; }
    public void setRegistroMarinha(String registroMarinha)   { this.registroMarinha = registroMarinha; }

    public double getBoca()                                  { return boca; }
    public void setBoca(double boca)                         { this.boca = boca; }

    public double getCaladoAereo()                           { return caladoAereo; }
    public void setCaladoAereo(double caladoAereo)           { this.caladoAereo = caladoAereo; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        throw new UnsupportedOperationException("Use uma subclasse concreta de VeiculoFluvial.");
    }
}


class Caminhao extends VeiculoTerrestre {

    private static final double PRECO_DIESEL = 6.99;

    private int    qtdeEixos;
    private double capacidade;
    private String carroceria;

    public int getQtdeEixos()                  { return qtdeEixos; }
    public void setQtdeEixos(int qtdeEixos)    { this.qtdeEixos = qtdeEixos; }

    public double getCapacidade()              { return capacidade; }
    public void setCapacidade(double cap)      { this.capacidade = cap; }

    public String getCarroceria()              { return carroceria; }
    public void setCarroceria(String car)      { this.carroceria = car; }

    /**
     * Fórmula: (peso + (altura * largura)) * (distância * preço_diesel)
     */
    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        return (peso + (altura * largura)) * (distancia * PRECO_DIESEL);
    }

    @Override
    public String toString() {
        return "Caminhão [" + super.toString() + "] - Carroceria: " + carroceria
                + " | Eixos: " + qtdeEixos + " | Capacidade: " + capacidade + "t";
    }
}


class Aviao extends VeiculoAereo {

    private static final double PRECO_QUEROSENE = 9.99;

    private double capacidadeCarga; 
    private double mtow;

    public double getCapacidadeCarga()               { return capacidadeCarga; }
    public void setCapacidadeCarga(double cap)        { this.capacidadeCarga = cap; }

    public double getMtow()                          { return mtow; }
    public void setMtow(double mtow)                 { this.mtow = mtow; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        return ((peso * peso) * (altura * largura)) * (distancia * PRECO_QUEROSENE);
    }

    @Override
    public String toString() {
        return "Avião [" + super.toString() + "] - MTOW: " + mtow + "kg"
                + " | Carga máx: " + capacidadeCarga + "kg";
    }
}


class PortaContainer extends VeiculoFluvial {

    private int    capacidadeTEU; 
    private String categoria;

    public int getCapacidadeTEU()                  { return capacidadeTEU; }
    public void setCapacidadeTEU(int teu)           { this.capacidadeTEU = teu; }

    public String getCategoria()                   { return categoria; }
    public void setCategoria(String categoria)      { this.categoria = categoria; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        return peso + (altura * largura) * distancia;
    }

    @Override
    public String toString() {
        return "Porta-Container [" + super.toString() + "] - Categoria: " + categoria
                + " | Capacidade: " + capacidadeTEU + " TEUs";
    }
}


public class Transportadora {

    public double calcularFrete(Transporte veiculo,
                                double peso, double altura,
                                double largura, double distancia) {
        return veiculo.calcularFrete(peso, altura, largura, distancia);
    }

    private static Caminhao criarCaminhao() {
        Caminhao c = new Caminhao();
        c.setMarca("Mercedes-Benz");
        c.setModelo("Actros 2651");
        c.setAnoFabricacao(2022);
        c.setPropulsao("Diesel");
        c.setQtdeRodas(10);
        c.setQtdePortas(2);
        c.setPlaca("BRA2E25");
        c.setChassi("9BM384070NB123456");
        c.setQtdeEixos(5);
        c.setCapacidade(33.0);
        c.setCarroceria("Baú Frigorífico");
        return c;
    }

    private static Aviao criarAviao() {
        Aviao a = new Aviao();
        a.setMarca("Boeing");
        a.setModelo("747-8F");
        a.setAnoFabricacao(2020);
        a.setPropulsao("Turbofan");
        a.setRab("PP-VGI");
        a.setQtdeMotores(4);
        a.setCapacidadeCarga(134200.0);
        a.setMtow(447696.0);
        return a;
    }

    private static PortaContainer criarPortaContainer() {
        PortaContainer p = new PortaContainer();
        p.setMarca("COSCO Shipping");
        p.setModelo("COSCO Universe");
        p.setAnoFabricacao(2021);
        p.setPropulsao("Diesel de Baixa Rotação");
        p.setRegistroMarinha("IMO-9795427");
        p.setBoca(61.5);
        p.setCaladoAereo(16.5);
        p.setCapacidadeTEU(21237);
        p.setCategoria("Ultra Large Container Vessels (ULCV)");
        return p;
    }

    // ---------- Método principal ----------

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Transportadora transportadora = new Transportadora();

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║      SISTEMA DE COTAÇÃO DE FRETE         ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();

        // Coleta de dados da encomenda
        System.out.print("  Peso da encomenda (kg)   : ");
        double peso = sc.nextDouble();

        System.out.print("  Altura da encomenda (m)  : ");
        double altura = sc.nextDouble();

        System.out.print("  Largura da encomenda (m) : ");
        double largura = sc.nextDouble();

        System.out.print("  Distância do frete (km)  : ");
        double distancia = sc.nextDouble();

        sc.close();

        Caminhao        caminhao        = criarCaminhao();
        Aviao           aviao           = criarAviao();
        PortaContainer  portaContainer  = criarPortaContainer();

        double freteCaminhao       = transportadora.calcularFrete(caminhao,       peso, altura, largura, distancia);
        double freteAviao          = transportadora.calcularFrete(aviao,          peso, altura, largura, distancia);
        double fretePortaContainer = transportadora.calcularFrete(portaContainer, peso, altura, largura, distancia);

        System.out.println();
        System.out.println("══════════════════════════════════════════════");
        System.out.println("               COTAÇÃO DE FRETE               ");
        System.out.println("══════════════════════════════════════════════");
        System.out.printf("  Encomenda : %.2f kg | %.2fm x %.2fm | %.0f km%n",
                peso, altura, largura, distancia);
        System.out.println("──────────────────────────────────────────────");
        System.out.printf("  🚛 Caminhão        : R$ %,.2f%n", freteCaminhao);
        System.out.printf("     %s%n", caminhao);
        System.out.println();
        System.out.printf("  ✈  Avião           : R$ %,.2f%n", freteAviao);
        System.out.printf("     %s%n", aviao);
        System.out.println();
        System.out.printf("  🚢 Porta-Container : R$ %,.2f%n", fretePortaContainer);
        System.out.printf("     %s%n", portaContainer);
        System.out.println("══════════════════════════════════════════════");

        double menorFrete = Math.min(freteCaminhao, Math.min(freteAviao, fretePortaContainer));
        String maisBarato;
        if (menorFrete == freteCaminhao)            maisBarato = "Caminhão";
        else if (menorFrete == freteAviao)          maisBarato = "Avião";
        else                                        maisBarato = "Porta-Container";

        System.out.printf("  ✅ Opção mais econômica: %s (R$ %,.2f)%n", maisBarato, menorFrete);
        System.out.println("══════════════════════════════════════════════");
    }
}
