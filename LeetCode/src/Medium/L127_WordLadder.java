package Medium;

import java.util.List;

public class L127_WordLadder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    	if(wordList.contains(endWord)) {
    		return 0;
    	}
    	
        return wordList.size();
    }
}
