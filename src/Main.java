
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String FullPath= "C:/Users/hkuma/java files/search_engine_trie/src/words.txt";
        // Load words from a file
        loadWordsFromFile(trie, FullPath);

        // Test search functionality
        System.out.println("Search for 'apple': " + trie.search("apple"));
        System.out.println("Search for 'banana': " + trie.search("banana"));

        // Test autocomplete functionality
        List<String> autocompleteResults = trie.autocomplete("ap");
        System.out.println("Autocomplete for 'ap': " + autocompleteResults);
    }

    private static void loadWordsFromFile(Trie trie, String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String word;
            while ((word = br.readLine()) != null) {
                trie.insert(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
