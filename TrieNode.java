import java.util.ArrayList;

/***
 * Trie's node
 * 
 * Implements an object-oriented node structure The root node only contains a
 * list of references to its children nodes. Other nodes also contain the letter
 * of a word's prefix and the word if the node is a leaf.
 * 
 * @author Chris
 * 
 */
public class TrieNode {

	private char letter;
	private String isWord;
	private ArrayList<TrieNode> children;

	/***
	 * Root node constructor
	 */
	public TrieNode() {
		children = new ArrayList<TrieNode>();
	}

	public TrieNode(char letter) {
		this.children = new ArrayList<TrieNode>();
		this.letter = letter;
		this.isWord = null;
	}

	/***
	 * Add the given letter as the node's child
	 * 
	 * If there already exists a node with the letter, it is return. Otherwise, a
	 * new node is created and returned.
	 * 
	 * @param letter
	 * @return
	 */
	public TrieNode addChild(char letter) {
		TrieNode temp = findChild(letter);
		if (temp == null) {
			temp = new TrieNode(letter);
			this.children.add(temp);
		}

		return temp;
	}

	/***
	 * Find the child node that matches the letter
	 * 
	 * Will return the node or null
	 * 
	 * @param letter
	 * @return
	 */
	public TrieNode findChild(char letter) {
		for (TrieNode node : this.children) {
			if (letter == node.letter) {
				return node;
			}
		}

		return null;
	}

	/***
	 * Determines if the word exists as a children of the current node
	 * 
	 * @param word
	 *            - word to find
	 * @param start
	 *            - the current letter
	 * @param length
	 *            - length of word
	 * @return
	 */
	public boolean findWord(String word, int start, int length) {
		if (start < length) {
			for (int i = 0; i < children.size(); i++) {
				if (word.charAt(start) == children.get(i).letter) {
					return children.get(i).findWord(word, start + 1, length);
				}
			}
		} else if (this.isWord != null) {

			return true;
		}

		return false;
	}

	public ArrayList<TrieNode> getChildren() {
		return this.children;
	}

	public char getLetter() {
		return this.letter;
	}

	public String getIsWord() {
		return this.isWord;
	}

	public void setIsWord(String word) {
		this.isWord = word;
	}
}