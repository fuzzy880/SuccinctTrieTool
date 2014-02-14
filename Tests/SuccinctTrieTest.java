import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Test;


public class SuccinctTrieTest {
	
	static SuccinctTrie sucTrie;
	
	@BeforeClass
	public static void createSuccinctTrie() {
		Trie trie = new Trie("words.txt");
		sucTrie = new SuccinctTrie(trie);
	}

	@Test
	public void testCreateSuccinctTrie() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("words.txt")); 
			String word;
			boolean notFound = true;
			while ((word = in.readLine()) != null) {
				notFound = sucTrie.findWord(word.toLowerCase());
				if(notFound == false) {
					System.out.println(word + " is not found");
					assertTrue(false);
				}
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error reading from file");
		}
	}

}
