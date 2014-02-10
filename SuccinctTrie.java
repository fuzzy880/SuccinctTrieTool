import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;

/***
 * 
 * @author Chris
 * 
 */
public class SuccinctTrie {

	BitSet arrayBits;
	ArrayList<Character> arrayChar;
	ArrayList<String> arrayString;

	public SuccinctTrie(Trie trie) {
		this.arrayBits = new BitSet();
		this.arrayChar = new ArrayList<Character>();
		this.arrayString = new ArrayList<String>();

		createSuccinctTrie(trie);
	}

	/***
	 * Transforms a trie to succinct trie by generating the bitarray and
	 * character data array
	 * 
	 * Nodes are represented by a series of 1's for every children they have
	 * followed by a 0. Every 0 represents a node.
	 * 
	 * The node number is the position of node's character in character array
	 * 
	 * @param trie
	 */
	private void createSuccinctTrie(Trie trie) {
		int counter = 0;
		int nodeNum = 0;
		ArrayList<TrieNode> queue = new ArrayList<TrieNode>();

		arrayBits.set(counter++, true);
		arrayBits.set(counter++, false);
		queue.add(trie.getRoot());

		while (queue.size() > 0) {
			TrieNode temp = queue.get(0);
			queue.remove(0);

			for (TrieNode child : temp.getChildren()) {
				arrayBits.set(counter++, true);
				queue.add(child);
			}

			arrayBits.set(counter++, false);
			arrayChar.add(nodeNum, temp.getLetter());

			if (temp.getIsWord() != null) {
				arrayString.add(nodeNum, temp.getIsWord());
			} else {
				arrayString.add(nodeNum, null);
			}

			nodeNum++;
		}
	}

	public boolean findWord(String word) {
		return findWord(word, 0, 0);
	}
	
	/***
	 * Determines the word exists in the succinct trie
	 * 
	 * @param word
	 * @param start
	 * @param nodeNum
	 * @return
	 */
	private boolean findWord(String word, int start, int nodeNum) {
		if (start < word.length()) {
			int firstChild = firstChild(nodeNum);
			int numChildren = getChildren(nodeNum);
			for (int i = 0; i < numChildren; i++) {
				if (arrayChar.get(firstChild + i) == word.charAt(start)) {
					return findWord(word, start + 1, firstChild + i);
				}
			}
		} else {
			if (arrayString.get(nodeNum) != null) {
				if (word.equals(arrayString.get(nodeNum))) {
					return true;
				}
			}
		}
		return false;
	}

	/***
	 * Writes character data array to file
	 */
	public void writeNodeDataToFile() {
		try {
			PrintWriter writer = new PrintWriter("char.txt", "UTF-8");
			writer.print(" ");
			for (int i = 0; i < arrayChar.size(); i++) {
				if (arrayChar.get(i) != '\u0000') {
					writer.print(arrayChar.get(i));
				}
			}
			writer.close();
		} catch (IOException e) {

		}
	}

	/***
	 * Writes bit array to file
	 */
	public void writeTrieBitsToFile() {
		try {
			PrintWriter writer = new PrintWriter("trie.txt", "UTF-8");
			for (int i = 0; i < arrayBits.size(); i++) {
				if (arrayBits.get(i)) {
					writer.print("1");
				} else {
					writer.print("0");
				}
			}
			writer.close();
		} catch (IOException e) {

		}
		System.out.println(arrayBits.size());
	}

	/***
	 * Determines the first child of a given node
	 * 
	 * @param nodeNum
	 * @return
	 */
	private int firstChild(int nodeNum) {
		return select(nodeNum + 1) - nodeNum;
	}

	/***
	 * Determines the number of children of a given node
	 * 
	 * @param nodeNum
	 * @return
	 */
	private int getChildren(int nodeNum) {
		int firstChild = select(nodeNum + 1) - nodeNum;
		int nextChild = select(nodeNum + 2) - nodeNum - 1;
		if (firstChild == -1 || nextChild == -1) {
			System.err.println("not found");
		}
		return nextChild - firstChild;
	}

	/***
	 * Finds position of the ith 0 bit
	 * 
	 * @param ithZeroBit
	 * @return
	 */
	private int select(int ithZeroBit) {
		int counter = ithZeroBit;
		for (int i = 0; i < arrayBits.size(); i++) {
			if (arrayBits.get(i) == false) {
				counter--;
				if (counter == 0) {
					return i;
				}
			}
		}
		return -1;
	}

	/***
	 * Finds the number of 1's at or before i
	 * 
	 * @param onesAtOrBeforeI
	 * @return
	 */
	private int rank(int onesAtOrBeforeI) {
		int counter = 0;
		for (int i = 0; i <= onesAtOrBeforeI; i++) {
			if (arrayBits.get(i) == true) {
				counter++;
			}
		}
		return counter;
	}
}