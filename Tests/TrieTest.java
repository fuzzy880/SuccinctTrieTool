import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;


public class TrieTest {
	static Trie trie;
	
	@BeforeClass
	public static void setUpTrie() {
		ArrayList<String> words = new ArrayList<String>();
		words.add("testing");
		words.add("abalone");
		words.add("avalanche");
		words.add("keyboard");
		trie  = new Trie(words);
	}
	@Test
	public void testAddWord() {
		Trie trie = new Trie();
		trie.addWord("test");
		
		//Ensure the newly added word is in the trie
		assertTrue(trie.findWord("test"));
	}
	
	@Test
	public void testFindWord() {
		//Ensure initialized words are in the trie
		assertTrue(trie.findWord("testing"));
		assertTrue(trie.findWord("abalone"));
		assertTrue(trie.findWord("avalanche"));
		assertTrue(trie.findWord("keyboard"));
	}
	
	@Test
	public void testMissingWords() {
		//Ensure unknown words are not found in trie
		assertTrue(!trie.findWord("abalones"));
		assertTrue(!trie.findWord("avalanch"));
		assertTrue(!trie.findWord("k"));
	}

}
