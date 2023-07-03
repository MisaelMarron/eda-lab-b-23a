package lab06;

import java.util.*;
public class arbol23<T extends Comparable<T>> {
    private Node<T> raiz;

    public arbol23() {
        raiz = null;
    }

    public void insert(T valor) {
        if (raiz == null) {
            raiz = new Node<>(valor);
        } else {
            Node<T> nodo = buscarNodoParaInsertar(raiz, valor);
            if (nodo == null) {
                return;
            }
            if (nodo.getPadre() == null) {
                dividirNodoRaiz(nodo, valor);
            } else {
                dividirNodoInterior(nodo, valor);
            }
        }
    }

    private Node<T> buscarNodoParaInsertar(Node<T> nodo, T valor) {
        if (nodo.getHijos().isEmpty()) {
            return nodo;
        }

        for (int i = 0; i < nodo.getValores().size(); i++) {
            if (valor.compareTo(nodo.getValores().get(i)) < 0) {
                return buscarNodoParaInsertar(nodo.getHijos().get(i), valor);
            }
        }

        return buscarNodoParaInsertar(nodo.getHijos().get(nodo.getValores().size()), valor);
    }

    private void dividirNodoRaiz(Node<T> nodo, T valor) {
        if (nodo.getValores().size() == 2) {
            nodo.getValores().add(valor);
            Collections.sort(nodo.getValores());
            Node<T> nuevoNodo = new Node<>(nodo.getValores().get(1));

            nuevoNodo.getHijos().add(nodo);
            nuevoNodo.getHijos().add(new Node<>(nodo.getValores().get(2)));

            nodo.getValores().remove(1);
            nodo.getValores().remove(1);

            nodo.getHijos().get(0).setPadre(nuevoNodo);
            nodo.getHijos().get(1).setPadre(nuevoNodo);

            raiz = nuevoNodo;
        } else {
            nodo.getValores().add(valor);
            Collections.sort(nodo.getValores());
        }
    }

    private void dividirNodoInterior(Node<T> nodo, T valor) {
        Node<T> padre = nodo.getPadre();

        if (nodo.getValores().size() == 2) {
            padre.getValores().add(nodo.getValores().get(1));
            Collections.sort(padre.getValores());

            int indiceHijo = padre.getHijos().indexOf(nodo);
            padre.getHijos().add(indiceHijo + 1, new Node<>(nodo.getValores().get(2)));

            nodo.getValores().remove(1);
            nodo.getValores().remove(1);

            padre.getHijos().get(indiceHijo).setPadre(padre);
            padre.getHijos().get(indiceHijo + 1).setPadre(padre);

            dividirNodoInterior(padre, valor);
        } else {
            nodo.getValores().add(valor);
            Collections.sort(nodo.getValores());
        }
    }

    public void delete(T valor) {
        if (raiz == null) {
            return;
        }

        Node<T> nodo = buscarNodo(raiz, valor);
        if (nodo == null) {
            return;
        }

        if (nodo.getPadre() == null && nodo.getHijos().isEmpty()) {
            raiz = null;
        } else {
            eliminarValor(nodo, valor);
        }
    }

    private Node<T> buscarNodo(Node<T> nodo, T valor) {
        if (nodo == null) {
            return null;
        }

        for (int i = 0; i < nodo.getValores().size(); i++) {
            if (valor.equals(nodo.getValores().get(i))) {
                return nodo;
            } else if (valor.compareTo(nodo.getValores().get(i)) < 0) {
                return buscarNodo(nodo.getHijos().get(i), valor);
            }
        }

        return buscarNodo(nodo.getHijos().get(nodo.getValores().size()), valor);
    }

    private void eliminarValor(Node<T> nodo, T valor) {
        nodo.getValores().remove(valor);

        if (nodo.getValores().isEmpty()) {
            Node<T> padre = nodo.getPadre();

            if (padre != null) {
                int indiceHijo = padre.getHijos().indexOf(nodo);
                if (indiceHijo > 0 && padre.getHijos().get(indiceHijo - 1).getValores().size() > 1) {
                    Node<T> hermanoIzquierdo = padre.getHijos().get(indiceHijo - 1);
                    T valorPadre = padre.getValores().remove(indiceHijo - 1);
                    nodo.getValores().add(0, valorPadre);

                    int indiceValorHermano = hermanoIzquierdo.getValores().size() - 1;
                    T valorHermano = hermanoIzquierdo.getValores().remove(indiceValorHermano);
                    padre.getValores().add(indiceHijo - 1, valorHermano);
                    Collections.sort(padre.getValores());

                    if (!hermanoIzquierdo.getHijos().isEmpty()) {
                        Node<T> ultimoHijo = hermanoIzquierdo.getHijos().remove(indiceValorHermano + 1);
                        nodo.getHijos().add(0, ultimoHijo);
                        ultimoHijo.setPadre(nodo);
                    }
                } else if (indiceHijo < padre.getHijos().size() - 1 && padre.getHijos().get(indiceHijo + 1).getValores().size() > 1) {
                    Node<T> hermanoDerecho = padre.getHijos().get(indiceHijo + 1);
                    T valorPadre = padre.getValores().remove(indiceHijo);
                    nodo.getValores().add(valorPadre);

                    T valorHermano = hermanoDerecho.getValores().remove(0);
                    padre.getValores().add(indiceHijo, valorHermano);
                    Collections.sort(padre.getValores());

                    if (!hermanoDerecho.getHijos().isEmpty()) {
                        Node<T> primerHijo = hermanoDerecho.getHijos().remove(0);
                        nodo.getHijos().add(primerHijo);
                        primerHijo.setPadre(nodo);
                    }
                } else {
                    if (indiceHijo > 0) {
                        Node<T> hermanoIzquierdo = padre.getHijos().remove(indiceHijo - 1);
                        T valorPadre = padre.getValores().remove(indiceHijo - 1);
                        hermanoIzquierdo.getValores().add(valorPadre);
                        hermanoIzquierdo.getValores().addAll(nodo.getValores());
                        hermanoIzquierdo.getHijos().addAll(nodo.getHijos());

                        for (Node<T> hijo : nodo.getHijos()) {
                            hijo.setPadre(hermanoIzquierdo);
                        }

                        if (padre.getValores().isEmpty()) {
                            raiz = hermanoIzquierdo;
                        }

                        eliminarValor(padre, valor);
                    } else {
                        Node<T> hermanoDerecho = padre.getHijos().remove(indiceHijo + 1);
                        T valorPadre = padre.getValores().remove(indiceHijo);
                        nodo.getValores().add(valorPadre);
                        nodo.getValores().addAll(hermanoDerecho.getValores());
                        nodo.getHijos().addAll(hermanoDerecho.getHijos());

                        for (Node<T> hijo : hermanoDerecho.getHijos()) {
                            hijo.setPadre(nodo);
                        }

                        if (padre.getValores().isEmpty()) {
                            raiz = nodo;
                        }

                        eliminarValor(padre, valor);
                    }
                }
            }
        }
    }

    public void display() {
        displayHelper(raiz, "");
    }

    private void displayHelper(Node<T> nodo, String indent) {
        if (nodo == null) {
            return;
        }

        for (int i = nodo.getValores().size() - 1; i >= 0; i--) {
            displayHelper(nodo.getHijos().get(i + 1), indent + "    ");
            System.out.println(indent + nodo.getValores().get(i));
        }

        displayHelper(nodo.getHijos().get(0), indent + "    ");
    }
}