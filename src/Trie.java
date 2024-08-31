
import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord;
    }

    public List<String> autocomplete(String prefix) {
        TrieNode current = root;
        List<String> results = new ArrayList<>();
        for (char ch : prefix.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return results;
            }
            current = node;
        }
        findWordsFromNode(current, results, new StringBuilder(prefix));
        return results;
    }

    private void findWordsFromNode(TrieNode node, List<String> results, StringBuilder prefix) {
        if (node.isEndOfWord) {
            results.add(prefix.toString());
        }
        for (char ch : node.children.keySet()) {
            prefix.append(ch);
            findWordsFromNode(node.children.get(ch), results, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
