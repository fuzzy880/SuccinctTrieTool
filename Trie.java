import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/***
 * Trie data structure that implements an object-oriented graph
 * 
 * @author Chris
 * 
 */
public class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	/***
	 * Initialize the trie with a newline separated list of words
	 * 
	 * @param filename
	 *            - word list file
	 */
	public Trie(String filename) {
		root = new TrieNode();
		int wordCount = 0;

		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String word;
			while ((word = in.readLine()) != null) {
				wordCount++;
				this.addWord(word.toLowerCase());
			}
			in.close();
		} catch (Exception e) {

		}
		System.out.println(wordCount + " words scanned into trie");
	}
	
	/***
	 * Initialize the trie with an array of words
	 * 
	 * @param filename
	 *            - word list file
	 */
	public Trie(ArrayList<String> words) {
		root = new TrieNode();
		int wordCount = 0;
		for (String word : words) {
			this.addWord(word.toLowerCase());
			wordCount++;
		}

		System.out.println(wordCount + " words scanned into trie");
	}

	/***
	 * Add word to the trie
	 * 
	 * @param word
	 */
	public void addWord(String word) {
		TrieNode temp = root;
		for (int i = 0; i < word.length(); i++) {
			temp = temp.addChild(word.charAt(i));
		}
		temp.setIsWord(word);
	}

	/***
	 * Check if a word exists in the trie
	 * 
	 * @param word
	 * @return
	 */
	public boolean findWord(String word) {
		return root.findWord(word, 0, word.length());
	}

	public TrieNode getRoot() {
		return this.root;
	}
}