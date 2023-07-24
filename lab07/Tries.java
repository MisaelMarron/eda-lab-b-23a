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
}