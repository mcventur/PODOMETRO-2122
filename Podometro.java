/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45; // porcentaje sobre la altura (45%)
    private final double ZANCADA_MUJER = 0.41; // porcentaje sobre la altura (41%)
    private final int SABADO = 6;
    private final int DOMINGO = 7;

    private String marca;

    private double altura; // en cm
    private char sexo;
    private double longitudZancada; // en cm

    private double totalDistanciaSemana; // en Km
    private double totalDistanciaFinSemana; // en Km
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;

    private int tiempo; //  tiempo total caminado durante toda la semana en minutos
    private int caminatasNoche; // nº caminatas a partir de las 21 h.

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {

        marca = queMarca;
        altura = 0;
        sexo = MUJER;

        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;

        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;

        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {

        return marca;

    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {

        altura = queAltura;
        sexo = queSexo;

        if (sexo == HOMBRE) {
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
        else {
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }
    }

     /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFin – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
                            int horaFin) {

        double distancia = pasos * longitudZancada / Math.pow(10, 5); // distancia recorrida en Km
        totalDistanciaSemana += distancia;

        int minutosInicio = (horaInicio / 100) * 60 + (horaInicio % 100);
        int minutosFin = (horaFin / 100) * 60 + (horaFin % 100);
        tiempo += (minutosFin - minutosInicio);
        // System.out.println("\nCaminata " + (minutosFin - minutosInicio) + "'");

        if (horaInicio / 100 >= 21) {
            caminatasNoche++;
        }
        switch (dia) {
            case SABADO:
            totalPasosSabado += pasos;
            totalDistanciaFinSemana += distancia;
            break;
            case DOMINGO:
            totalPasosDomingo += pasos;
            totalDistanciaFinSemana += distancia;
            break;
            default:
            totalPasosLaborables += pasos;
            break;
        }

    }
    
     /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {

        System.out.println("\nConfiguración del podómetro");
        System.out.println("*********************************");

        System.out.println("Altura: " + altura / 100 + " mtos");
        String strSexo;
        if (sexo == MUJER) {
            strSexo = "MUJER";
        }
        else {
            strSexo = "HOMBRE";
        }
        System.out.println("Sexo: " + strSexo);
        System.out.println("Longitud zancada: " + longitudZancada / 100 + " mtos");

    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {

        System.out.println("\nEstadísticas");
        System.out.println("*********************************");

        System.out.println("Distancia recorrida toda la semana: "
            + totalDistanciaSemana + " Km");
        System.out.println("Distancia recorrida fin de semana: "
            + totalDistanciaFinSemana + " Km \n");
          
        System.out.println("Nº pasos días laborables: " + totalPasosLaborables);
        System.out.println("Nº pasos SÁBADO: " + totalPasosSabado);
        System.out.println("Nº pasos DOMINGO: " + totalPasosDomingo);

        System.out.println("\nNº caminatas realizadas a partir de las 21h.: "
            + caminatasNoche);

        int horas = tiempo / 60;
        int minutos = tiempo % 60;
        System.out.println("\nTiempo total caminado en la semana: " + horas
            + "h. y " + minutos + "m.");

    }

   

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {

        int mayor;
        String diaMayor;

        if (totalPasosLaborables > totalPasosSabado) {
            mayor = totalPasosLaborables;
            diaMayor = "LABORABLES";
        }
        else if (totalPasosLaborables < totalPasosSabado) {
            mayor = totalPasosSabado;
            diaMayor = " SABADO ";
        }
        else {
            mayor = totalPasosSabado;
            diaMayor = " LABORABLES  SABADO ";
        }
        
        if (totalPasosDomingo > mayor) {
            diaMayor = " DOMINGO";
        }
        else if (totalPasosDomingo == mayor) {
            diaMayor += " DOMINGO ";
        }

        return diaMayor;

    }
    
    
    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {

        altura = 0;
        sexo = MUJER;

        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;

        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;

        tiempo = 0;
        caminatasNoche = 0;

    }

}
