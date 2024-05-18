import java.util.Objects;
import java.util.Scanner;
import models.ApiResponse;
import queries.ExchangeRate;

public class Main {
    public static void main(String[] args) {

        String menu = """
                
                ********************************************
                Sea Bienvenido al Conversor de Moneda =]
                1) Dólar =>> Peso Argentino
                2) Peso Argentino =>> Dólar
                3) Dólar =>> Real Brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Salir
                Elija la opción válida
                *********************************************
                """;

        Scanner teclado = new Scanner(System.in);
        var opcion = 0;
        var valor = 0.0;
        var base_code = "";
        var target_code = "";

        while(true){

            System.out.println(menu);
            opcion = teclado.nextInt();
            System.out.println(opcion);
            if(opcion==7) {
                System.out.println("Servicio Finalizado");
                break;
            }else{
                switch(opcion){
                    case 1:
                        base_code = "USD";
                        target_code = "ARS";
                        break;
                    case 2:
                        base_code = "ARS";
                        target_code = "USD";
                        break;
                    case 3:
                        base_code = "USD";
                        target_code = "BRL";
                        break;
                    case 4:
                        base_code = "BRL";
                        target_code = "USD";
                        break;
                    case 5:
                        base_code = "USD";
                        target_code = "COP";
                        break;
                    case 6:
                        base_code = "COP";
                        target_code = "USD";
                        break;
                    default:
                        System.out.println("Opción inválida");
                        continue;
                }

                System.out.println("Ingrese el valor de: " + base_code + " que deseas convertir a " + target_code);
                valor = teclado.nextDouble();

                //Realizando el Query
                try{
                    ApiResponse result = ExchangeRate.query(base_code, target_code);

                    double conversion_rate = result.conversion_rate();
                    double convertedValue = conversion_rate * valor;
                    System.out.println("El valor de " + valor + "[" + base_code + "] corresponde al valor final de =>>> " + convertedValue + "[" + target_code + "]");

                }
                catch (Exception e){
                    System.out.println("Ocurrió un error en la conversión "+ e.getMessage());
                }

            }

        }

    }
}
