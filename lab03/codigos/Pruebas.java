public  class Pruebas {
        public  static void main(String[] args) {
            Lista<Integer> lista = new ListaUsar<Integer>();
            System.out.println("50 ? "+ lista.contains(50));
            lista.add(30);
            System.out.println(lista);
            lista.add(40);
            System.out.println(lista);
            lista.add(50);
            System.out.println(lista);

        }
}
