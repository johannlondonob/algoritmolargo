package com.company;

// import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Encuesta encuesta = new Encuesta();
        Scanner scanner = new Scanner(System.in);
        // ArrayList<Encuestado> listaEncuestados = new ArrayList<>();

        boolean go = true;
        int edad = 0;
        double peso = 0;
        double estatura = 0;

        while (go) {
            try {
                System.out.print("Ingresa la edad de la persona: ");
                edad = scanner.nextInt();

                if (edad < 0) {
                    encuesta.addEdadesNegativas();
                }

                System.out.print("Ingresa el peso de la persona: ");
                peso = scanner.nextDouble();

                if (peso < 0) {
                    encuesta.addErrorPesosNegativos();
                }

                System.out.print("Ingresa la estatura de la persona en centímetros: ");
                estatura = scanner.nextDouble() / 100;
            } catch (Exception e) {
                System.out.println("Valor incorrecto");
            }

            try {
                Encuestado encuestado = new Encuestado(edad, estatura, peso);

                switch (encuestado.getCategoria()) {
                    case "NIÑO":
                        encuesta.addNinios();
                        break;
                    case "JOVEN":
                        encuesta.addJovenes();
                        break;
                    case "ADULTO":
                        encuesta.addAdultos();
                        break;
                    case "VIEJO":
                        encuesta.addViejos();
                        break;
                }

                switch (encuestado.getImc()) {
                    case "PESO INFERIOR AL NORMAL":
                        encuesta.addPesosBajos();
                        break;
                    case "NORMAL":
                        encuesta.addPesosNormal();
                        break;
                    case "PESO SUPERIOR AL NORMAL":
                        encuesta.addPesosSuperior();
                        break;
                    case "OBESIDAD":
                        encuesta.addPesosObesos();
                        break;
                }

                encuesta.setSumaIMCPoblacion(peso, estatura);
                encuesta.addEncuestado();
                // listaEncuestados.add(encuestado);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }

            try {
                go = encuesta.Continuar(scanner);
            } catch (Exception e) {
                System.out.println("Su respuesta no era la esperada. Se detiene la tarea.");
                go = false;
            }
        }

        /*for (Encuestado encuestado : listaEncuestados) {
            System.out.println(encuestado.getCategoria() + ", edad: " + encuestado.getEdad() + ", descripción IMC: " + encuestado.getImc());
        }*/

        encuesta.setPorcientoNinios();
        encuesta.setPorcientoJovenes();
        encuesta.setPorcientoAdultos();
        encuesta.setPorcientoViejos();

        encuesta.setPorcientoPesosBajos();
        encuesta.setPorcientoPesosNormal();
        encuesta.setPorcientoPesosSuperior();
        encuesta.setPorcientoPesosObesos();

        encuesta.setPromedioIMCPoblacion();

        encuesta.Imprimir();
    }

    static class Encuesta {
        public Encuesta() {
        }

        private int encuestados = 0;
        private int errorEdadesNegativas = 0;
        private int errorPesosNegativos = 0;
        private int ninios = 0;
        private int jovenes = 0;
        private int adultos = 0;
        private int viejos = 0;
        private float porcientoNinios = 0;
        private float porcientoJovenes = 0;
        private float porcientoAdultos = 0;
        private float porcientoViejos = 0;
        private int pesosBajos = 0;
        private int pesosNormal = 0;
        private int pesosSuperior = 0;
        private int pesosObesos = 0;
        private float porcientoPesosBajos = 0;
        private float porcientoPesosNormal = 0;
        private float porcientoPesosSuperior = 0;
        private float porcientoPesosObesos = 0;
        private float promedioIMCPoblacion = 0;
        private float sumaIMCPoblacion = 0;

        public int getEncuestados() {
            return encuestados;
        }

        public void addEncuestado() {
            this.encuestados++;
        }

        public int getEdadesNegativas() {
            return errorEdadesNegativas;
        }

        public void addEdadesNegativas() {
            this.errorEdadesNegativas++;
        }

        public int getErrorPesosNegativos() {
            return errorPesosNegativos;
        }

        public void addErrorPesosNegativos() {
            this.errorPesosNegativos++;
        }

        public int getNinios() { return ninios; }

        public void addNinios() {
            this.ninios++;
        }

        public int getJovenes() { return jovenes; }

        public void addJovenes() {
            this.jovenes++;
        }

        public int getAdultos() { return adultos; }

        public void addAdultos() {
            this.adultos++;
        }

        public int getViejos() { return viejos; }

        public void addViejos() {
            this.viejos++;
        }

        public float getPorcientoNinios() {
            return porcientoNinios;
        }

        public void setPorcientoNinios() {
            this.porcientoNinios = ((float) this.ninios / this.encuestados) * 100;
        }

        public float getPorcientoJovenes() {
            return porcientoJovenes;
        }

        public void setPorcientoJovenes() {
            this.porcientoJovenes = ((float) this.jovenes / (float) this.encuestados) * 100;
        }

        public float getPorcientoAdultos() {
            return porcientoAdultos;
        }

        public void setPorcientoAdultos() {
            this.porcientoAdultos = ((float) this.adultos / (float) this.encuestados) * 100;
        }

        public float getPorcientoViejos() {
            return porcientoViejos;
        }

        public void setPorcientoViejos() {
            this.porcientoViejos = ((float) this.viejos / (float) this.encuestados) * 100;
        }

        public int getPesosBajos() { return pesosBajos; }

        public void addPesosBajos() {
            this.pesosBajos++;
        }

        public int getPesosNormal() { return pesosNormal; }

        public void addPesosNormal() {
            this.pesosNormal++;
        }

        public int getPesosSuperior() { return pesosSuperior; }

        public void addPesosSuperior() {
            this.pesosSuperior++;
        }

        public int getPesosObesos() { return pesosObesos; }

        public void addPesosObesos() {
            this.pesosObesos++;
        }

        public float getPorcientoPesosBajos() {
            return porcientoPesosBajos;
        }

        public void setPorcientoPesosBajos() {
            this.porcientoPesosBajos = ((float) this.pesosBajos / (float) this.encuestados) * 100;
        }

        public float getPorcientoPesosNormal() {
            return porcientoPesosNormal;
        }

        public void setPorcientoPesosNormal() {
            this.porcientoPesosNormal = ((float) this.pesosNormal / (float) this.encuestados) * 100;
        }

        public float getPorcientoPesosSuperior() {
            return porcientoPesosSuperior;
        }

        public void setPorcientoPesosSuperior() {
            this.porcientoPesosSuperior = ((float) this.pesosSuperior / (float) this.encuestados) * 100;
        }

        public float getPorcientoPesosObesos() {
            return porcientoPesosObesos;
        }

        public void setPorcientoPesosObesos() {
            this.porcientoPesosObesos = ((float) this.pesosObesos / (float) this.encuestados) * 100;
        }

        public void setSumaIMCPoblacion(double peso, double estatura) {
            this.sumaIMCPoblacion += (float) peso / (float) (estatura * 2);
        }

        public float getPromedioIMCPoblacion() {
            return promedioIMCPoblacion;
        }

        public void setPromedioIMCPoblacion() {
            this.promedioIMCPoblacion = this.sumaIMCPoblacion / this.encuestados;
        }

        public boolean Continuar(Scanner scanner) {
            System.out.print("¿Desea continuar registrando? \n 1, para continuar. \n Su elección: ");
            int eleccion = scanner.nextInt();
            return eleccion == 1;
        }

        public void Imprimir() {
            System.out.println("\nCantidad de encuestados: " + this.getEncuestados());
            System.out.println("Cantidad de edades negativas: " + this.getEdadesNegativas());
            System.out.println("Cantidad de pesos negativos: " + this.getErrorPesosNegativos() + "\n");

            System.out.println("Cantidad de niños registrados: " + this.getNinios());
            System.out.println("Cantidad de jóvenes registrados: " + this.getJovenes());
            System.out.println("Cantidad de adultos registrados: " + this.getAdultos());
            System.out.println("Cantidad de viejos registrados: " + this.getViejos());
            System.out.println("Porcentaje de niños registrados: " + this.getPorcientoNinios() + "%");
            System.out.println("Porcentaje de jóvenes registrados: " + this.getPorcientoJovenes() + "%");
            System.out.println("Porcentaje de adultos registrados: " + this.getPorcientoAdultos() + "%");
            System.out.println("Porcentaje de viejos registrados: " + this.getPorcientoViejos() + "% \n");

            System.out.println("Cantidad de personas bajos de peso: " + this.getPesosBajos());
            System.out.println("Cantidad de personas con pesos normales: " + this.getPesosNormal());
            System.out.println("Cantidad de personas con sobrepeso: " + this.getPesosSuperior());
            System.out.println("Cantidad de personas con obesidad: " + this.getPesosObesos());
            System.out.println("Porcentaje de personas bajos de peso: " + this.getPorcientoPesosBajos() + "%");
            System.out.println("Porcentaje de personas con pesos normales: " + this.getPorcientoPesosNormal() + "%");
            System.out.println("Porcentaje de personas con sobrepeso: " + this.getPorcientoPesosSuperior() + "%");
            System.out.println("Porcentaje de personas con obesidad: " + this.getPorcientoPesosObesos() + "% \n");

            System.out.println("Promedio IMC: " + this.getPromedioIMCPoblacion() + "\n");
        }
    }

    static class Encuestado {
        private int edad;
        private double estatura;
        private double peso;
        private String imc;
        private String categoria;

        public Encuestado(int edad, double estatura, double peso) {
            this.edad = edad;
            this.estatura = estatura;
            this.peso = peso;
            this.setCategoria();
            this.setImc();
        }

        public String getImc() {
            return imc;
        }

        public void setImc() {
            float imc = (float) this.peso / (float) (this.estatura * 2);

            if (imc < 18.5) {
                this.imc = "PESO INFERIOR AL NORMAL";
            } else if (imc >= 18.5 && imc <= 24.9) {
                this.imc = "NORMAL";
            } else if (imc >= 25.0 && imc <= 29.9) {
                this.imc = "PESO SUPERIOR AL NORMAL";
            } else {
                this.imc = "OBESIDAD";
            }
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria() {
            if (this.edad <= 13) {
                this.categoria = "NIÑO";
            } else if (this.edad <= 30) {
                this.categoria = "JOVEN";
            } else if (this.edad <= 60) {
                this.categoria = "ADULTO";
            } else {
                this.categoria = "VIEJO";
            }
        }
    }
}
