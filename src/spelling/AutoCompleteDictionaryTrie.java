package spelling;


import java.util.*;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 * @author You
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private TrieNode root;
    private int size;


    public AutoCompleteDictionaryTrie() {
        root = new TrieNode();
    }


    /**
     * Insert a word into the trie.
     * For the basic part of the assignment (part 2), you should ignore the word's case.
     * That is, you should convert the string to all lower case as you insert it.
     */
    public boolean addWord(String word) {
        if (word.isEmpty() || word == null) {
            return false;
        }
        if (this.isWord(word.toLowerCase())) {
            return false;
        }
        String str = word.toLowerCase();
        TrieNode first = root;
        TrieNode next = first;
        int counter = 1;
        for (int i = 0; i < str.toCharArray().length; i++) {
            char c = str.charAt(i);
            if (counter == 1) {
                first = next;
                next = root.insert(c);
                counter++;
            } else {
                first = next;
                next = next.insert(c);
            }
            if (next == null) {
                next = first.getChild(c);
            }
            if (i == str.length() - 1) {
                size++;
                next.setEndsWord(true);
            }
        }
        return true;
    }

    /**
     * Return the number of words in the dictionary.  This is NOT necessarily the same
     * as the number of TrieNodes in the trie.
     */
    public int size() {
        //TODO: Implement this method
        return size;
    }


    /**
     * Returns whether the string is a word in the trie
     */
    @Override
    public boolean isWord(String s) {
        // TODO: Implement this method
        if (s.isEmpty() || s == null || size == 0) {
            return false;
        }
        String str = s.toLowerCase();
        TrieNode node = root;
        for (int i = 0; i < str.toCharArray().length; i++) {
            char c = str.charAt(i);
            if (node.getValidNextCharacters().contains(c)) {
                node = node.getChild(c);
            }
        }
        if (node.endsWord() && node.getText().equals(str)) {
            return true;
        } else
            return false;
    }

    /**
     * * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     *
     * @param prefix         The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */
    @Override
    public List<String> predictCompletions(String prefix, int numCompletions) {
        // TODO: Implement this method
        // This method should implement the following algorithm:
        // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
        //    empty list
        // 2. Once the stem is found, perform a breadth first search to generate completions
        //    using the following algorithm:
        //    Create a queue (LinkedList) and add the node that completes the stem to the back
        //       of the list.
        //    Create a list of completions to return (initially empty)
        //    While the queue is not empty and you don't have enough completions:
        //       remove the first Node from the queue
        //       If it is a word, add it to the completions list
        //       Add all of its child nodes to the back of the queue
        // Return the list of completions
        LinkedList<TrieNode> stringQueue = new LinkedList<>();
        List<String> list = new LinkedList<>();
        try {
            ////////////////////////////////////////////
            String str = prefix.toLowerCase();
            TrieNode node = root;
            for (int i = 0; i < str.toCharArray().length; i++) {
                char c = str.charAt(i);
                if (node.getValidNextCharacters().contains(c)) {
                    node = node.getChild(c);
                    if (node.getText().equals(str)) {
                        stringQueue.addFirst(node);
                        break;
                    }
                }
            }
            if (stringQueue.size() == 0) {
                return list;
            }
            //////////////////////////////////////////
            while (true) {
                LinkedList<TrieNode> temp = new LinkedList<>();

                for (TrieNode trieNode : stringQueue) {
                    if (list.size() < numCompletions || stringQueue.size() == 0) {
                        if (trieNode.endsWord()) {
                            if (!list.contains(trieNode.getText())) {
                                list.add(trieNode.getText());
                            }
                            for (char key : trieNode.getValidNextCharacters()) {
                                temp.addLast(trieNode.getChild(key));
                            }
                        } else {
                            for (char key : trieNode.getValidNextCharacters()) {
                                temp.addLast(trieNode.getChild(key));
                            }
                        }
                    } else
                        return list;
                }
                for (TrieNode trieNode : temp) {
                    stringQueue.addLast(trieNode);
                }
                stringQueue.removeFirst();
                temp = new LinkedList<>();
            }
        }catch (Exception e){
            return list;
        }
    }

    // For debugging
    public void printTree() {
        printNode(root);
    }

    /**
     * Do a pre-order traversal from this node down
     */
    public void printNode(TrieNode curr) {
        if (curr == null)
            return;

        System.out.println(curr.getText());

        TrieNode next = null;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            printNode(next);
        }
    }
}