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

class HuffmanTree {
    private static Nodo raiz;
    private static int i = 0, j = 0;

    public static void encode(Nodo root, String str, Map<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }

        // Encontré un nodo hoja
        if (isLeaf(root)) {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }

        encode(root.left, str + '0', huffmanCode);
        encode(root.right, str + '1', huffmanCode);
    }

    public static int decode(Nodo root, int index, StringBuilder sb) {
        if (root == null) {
            return index;
        }

        if (isLeaf(root)) {
            System.out.print(root.ch);
            return index;
        }

        index++;

        root = (sb.charAt(index) == '0') ? root.left : root.right;
        index = decode(root, index, sb);
        return index;
    }

    public static boolean isLeaf(Nodo root) {
        return root.left == null && root.right == null;
    }

    public static void buildHuffmanTree(String text) {
        // Caso base: string vacía
        if (text == null || text.length() == 0) {
            return;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Nodo> pq;
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));

        for (var entry : freq.entrySet()) {
            pq.add(new Nodo(entry.getKey(), entry.getValue()));
        }

        while (pq.size() != 1) {

            Nodo left = pq.poll();
            Nodo right = pq.poll();

            int sum = left.freq + right.freq;
            pq.add(new Nodo(null, sum, left, right));
        }

        Nodo root = pq.peek();

        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        System.out.println("Codigos de Huffman: " + huffmanCode);
        System.out.println("Texto original: " + text);

        // Imprimir string codificada
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(huffmanCode.get(c));
        }

        System.out.println("Texto codificado: " + sb);
        System.out.print("Texto decodificado: ");

        if (isLeaf(root)) {
            // Caso especial: Para entradas como a, aa, aaa, etc.
            while (root.freq-- > 0) {
                System.out.print(root.ch);
            }
        } else {
            // Atraviesa el árbol Huffman de nuevo y esta vez,
            // decodifica la string codificada
            int index = -1;
            while (index < sb.length() - 1) {
                index = decode(root, index, sb);
            }
        }

        raiz = root;
        if (raiz.ch == null)
            raiz.ch = '^';
    }

}