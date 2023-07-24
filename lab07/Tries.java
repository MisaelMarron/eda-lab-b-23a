import java.util.*;
class TrieNode {

    private static final int ALPHABET_SIZE = 30;

    private TrieNode[] children;
    private boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[ALPHABET_SIZE];
        isEndOfWord = false;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public TrieNode getChild(char ch) {
        int index = ch -'a';
        if (index <0 || index >= ALPHABET_SIZE) {
            return null;
        }
        return children[index];
    }

    public void setChild(char ch, TrieNode node) {
        int index = ch - 'a';
        if (index < 0 || index >= ALPHABET_SIZE) {
            return;
        }
        children[index] = node;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }
}

public class Tries {
    private TrieNode root;
    private List<String> InOrder;
    private Map<String, List<Integer>> Indices;

    public Tries() {
        root = new TrieNode();
        InOrder = new ArrayList<>();
        Indices = new HashMap<>();
    }

public void insert(String word) {
    TrieNode current = root;

    for (int i = 0; i < word.length(); i++) {
        char ch = word.charAt(i);
        TrieNode node = current.getChild(ch);

        if (node == null) {
            node = new TrieNode();
            current.setChild(ch, node);
        }
        current = node;
    }

        current.setEndOfWord(true);
    if (!Indices.containsKey(word)) {
            InOrder.add(word);
        if (!Indices.containsKey(word)) 
            Indices.put(word, new ArrayList<>());
        
        Indices.get(word).add(InOrder.size() - 1);
     }
}
public boolean search(String word) {
    TrieNode current = root;

    for (int i = 0; i < word.length(); i++) {
        char ch = word.charAt(i);
        TrieNode node = current.getChild(ch);

        if (node == null) {
            return false;
        }

        current = node;
    }

    return current.isEndOfWord();
}   
public boolean delete(String word) {
    if (!search(word)) {
        return false;
    }

    TrieNode current = root;
    TrieNode[] ancestors = new TrieNode[word.length()];

    for (int i = 0; i < word.length(); i++) {
        char ch = word.charAt(i);
        TrieNode node = current.getChild(ch);

        ancestors[i] = current;
        current = node;
    }

    current.setEndOfWord(false);

    for (int i = word.length() - 1; i >= 0; i--) {
        TrieNode node = ancestors[i];

        if (current.isEndOfWord() || hasChildren(current)) {
            break;
        }

        char ch = word.charAt(i);
        node.setChild(ch, null);
        current = node;
    }

    List<Integer> indices = Indices.get(word);
    if (indices != null && indices.size() > 0) {
        int indexToRemove = indices.get(indices.size() - 1);
        InOrder.remove(indexToRemove);
        indices.remove(indices.size() - 1);
    }

    return true;
}

private boolean hasChildren(TrieNode node) {
    for (TrieNode child : node.getChildren()) {
        if (child != null) {
            return true;
        }
    }
    return false;
}
}