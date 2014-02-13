import static org.junit.Assert.*;

import org.junit.Test;


public class TrieNodeTest {

	@Test
	public void testAddLetterWithAddChild() {
		TrieNode node = new TrieNode('h');
		TrieNode nodeE = node.addChild('e');
		TrieNode nodeFromGet = node.getChildren().get(0);
		
		//Ensure node returned from addChild is same as the child
		assertTrue(nodeE.equals(nodeFromGet));

		//Ensure node h has 1 child
		assertTrue(node.getChildren().size() == 1);
		
		//Ensure node h has child e
		assertTrue(nodeE.getLetter() == 'e');
		
		//Ensure node e has no children
		assertTrue(nodeE.getChildren().size() == 0);
	}
	
	@Test
	public void testAddExistingLetterWithAddChild() {
		TrieNode node = new TrieNode('h');
		node.addChild('e');
		node.addChild('e');
		TrieNode nodeE = node.getChildren().get(0);

		//Ensure node h has 1 child
		assertTrue(node.getChildren().size() == 1);
		
		//Ensure node h has child e
		assertTrue(nodeE.getLetter() == 'e');
		
		//Ensure node e has no children
		assertTrue(nodeE.getChildren().size() == 0);
	}
	
	@Test
	public void testFindExistingChild() {
		TrieNode node = new TrieNode();
		node.addChild('a');

		//Ensure node a is found
		assertTrue(node.findChild('a') != null);
		
		//Ensure node a is set to 'a'
		assertTrue(node.findChild('a').getLetter() == 'a');
	}
	
	@Test
	public void testFindMissingChild() {
		TrieNode node = new TrieNode();
		node.addChild('a');

		//Ensure node b is not found
		assertTrue(node.findChild('b') == null);
	}

}
