package Medium;

public class L79_Word_Search {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean exist(char[][] board, String word) {
		boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == word.charAt(0) && DFS(board, word, i, j, 0, visited)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean DFS(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
		if (index == word.length())
			return true;
		if (i >= board.length || i < 0 || j < 0 || j >= board[i].length || board[i][j] != word.charAt(index)
				|| visited[i][j]) {
			return false;
		}

		visited[i][j] = true;
		if (DFS(board, word, i - 1, j, index + 1, visited) || DFS(board, word, i + 1, j, index + 1, visited)
				|| DFS(board, word, i, j - 1, index + 1, visited) || DFS(board, word, i, j + 1, index + 1, visited)) {
			return true;
		}
		visited[i][j] = false;
		return false;
	}

}
