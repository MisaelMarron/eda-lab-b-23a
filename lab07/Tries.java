import javax.swing.*;
import java.util.*;
import javax.swing.text.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
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
public boolean startsWith(String prefix) {
    TrieNode current = root;

    for (int i = 0; i < prefix.length(); i++) {
        char ch = prefix.charAt(i);
        TrieNode node = current.getChild(ch);

        if (node == null) {
            return false;
        }

        current = node;
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









public boolean replace(String word, String newWord) {
    if (!search(word)) {
        return false;
    }

    List<Integer> indices = Indices.get(word);
    if (indices != null && indices.size() > 0) {
        for (int i = indices.size() - 1; i >= 0; i--) {
            int index = indices.get(i);
            InOrder.set(index, newWord);
        }
        indices.clear();
    }

    TrieNode current = root;
    for (int i = 0; i < word.length(); i++) {
        char ch = word.charAt(i);
        TrieNode node = current.getChild(ch);
        current = node;
    }
    current.setEndOfWord(false);

    

    return true;
}


public void printSetence() {
    for (String word : InOrder) {
        System.out.print(word + " ");
    }
    System.out.print("\n");
}

public String printSetence2() {
    String devolver = "";
    for (String word : InOrder) {
        devolver += word + " ";
    }
    return devolver;
}

public String printString(String word2) {
    String devolver = "";
    for (String word : InOrder) {
        int index = word.indexOf(word2);
        int endIndex = index + word2.length();

        if (index >= 0) {
            String beforeHighlight = word.substring(0, index);
            String highlighted = word.substring(index, endIndex);
            String afterHighlight = word.substring(endIndex);

            devolver += beforeHighlight;
            devolver += "\u001B[33m" + highlighted + "\u001B[0m";
            devolver += afterHighlight + " ";
        } else {
            devolver += word + " ";
        }
    }
    return devolver;
}

public void clear() {
    root = new TrieNode();
    InOrder.clear();
    Indices.clear();
}

public void printString2(String word3, JTextPane TextArea1) {
    StyledDocument doc = TextArea1.getStyledDocument();
    SimpleAttributeSet attr = new SimpleAttributeSet();
    StyleConstants.setForeground(attr, Color.BLACK);
    StyleConstants.setFontFamily(attr, "Times New Roman");

    SimpleAttributeSet yellowAttr = new SimpleAttributeSet();
    StyleConstants.setForeground(yellowAttr, Color.RED);

    for (String word : InOrder) {
        int index = word.indexOf(word3);
        int endIndex = index + word3.length();

        int currentPos = 0;
        while (index >= 0) {
            try {
                doc.insertString(doc.getLength(), word.substring(currentPos, index), attr);
                doc.insertString(doc.getLength(), word.substring(index, endIndex), yellowAttr);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            currentPos = endIndex;
            index = word.indexOf(word3, endIndex);
            endIndex = index + word3.length();
        }

        try {
            doc.insertString(doc.getLength(), word.substring(currentPos) + " ", attr);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
}