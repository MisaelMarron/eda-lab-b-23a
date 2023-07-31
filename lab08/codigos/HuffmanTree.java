import java.util.*;

class Nodo {
    Character ch = ' ';
    Integer freq;
    Nodo left = null, right = null;

    Nodo(Character ch, Integer freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Nodo(Character ch, Integer freq, Nodo left, Nodo right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}
