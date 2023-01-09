import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

at most (n-1) operations
swap a[i] and a[i+1] (performed at most once)

5 4 1 3 2
5 1 4 3 2 (2,3)
5 1 4 2 3 (4,5)
5 1 2 4 3 (3,4)
1 5 2 4 3 (1,2)

5 4 1 3 2
5 1 4 3 2 (2,3)
1 5 4 3 2 (1,2)
1 5 4 2 3 (4,5)
1 5 2 4 3 (3,4)

1 5 4 3 2
1 5 3 4 2
1 3 5 4 2 (cannot swap it anymore)

find a[j] < a[i], where i < j <= n
consider (j - i) as well

we can always put 1, the concern is on the next numbers

 */

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			int[] a = fs.readArray(n);
			int moves = n - 1, stop = 0;
			for (int i = 0; i < n; i++) {
				int index = -1, min = a[i];
				for (int j = i + 1; j < n; j++) {
					if (a[j] < min && j - i <= moves) {
						min = a[j];
						index = j;
					}
				}
				if (index != -1) {
					moves -= (index - i);
					int copyIndex = index;
					stop = i;
//					System.out.println(stop + " " + index);
					while (index > stop) {
						swap(a, index, index - 1);
						index--;
					}
					i = copyIndex - 1;
				}
			}
			for (int x : a) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
		out.close();
	}
	
	static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	static final Random rnd = new Random();
	
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newIndex = rnd.nextInt(n);
			int temp = a[newIndex]; //change this
			a[newIndex] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
