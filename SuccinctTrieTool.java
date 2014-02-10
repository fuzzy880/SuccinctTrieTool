/***
 * Tool that creates a succinct trie and writes the bitvector and data array to
 * files.
 * 
 * Tool reads in the words from a file and creates a trie. The trie is scanned
 * to create a succinct trie structure.
 * 
 * @author Chris
 * 
 */
public class SuccinctTrieTool {

	public static void main(String[] args) {
		Trie trie = new Trie("words.txt");
		SuccinctTrie sucTrie = new SuccinctTrie(trie);
		sucTrie.writeNodeDataToFile();
		sucTrie.writeTrieBitsToFile();
	}

}
