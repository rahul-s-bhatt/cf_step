import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // If input.txt exists in the same folder, read it (local testing).
        // On Codeforces the file won't exist, so it will read from System.in.
        InputStream inStream = System.in;
        try {
            File f = new File("input.txt"); // or "../input.txt" if you prefer local layout
            if (f.exists()) {
                inStream = new FileInputStream(f);
            }
        } catch (SecurityException | IOException e) {
            // Running inside a sandbox (or file not accessible) — fall back to stdin
            inStream = System.in;
        }
        FastScanner fs = new FastScanner(inStream);

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);

        // ---------- problem code starts here ----------
        // Sample for 189A - Cut Ribbon (replace with your solution)
        int n = fs.nextInt();
        int a = fs.nextInt();
        int b = fs.nextInt();
        int c = fs.nextInt();
        int[] cuts = new int[]{a, b, c};
        // int[] dp = new int[n + 1];
        // Arrays.fill(dp, Integer.MIN_VALUE / 2);
        // dp[0] = 0;
        // for (int i = 1; i <= n; i++) {
        //     for (int cut : cuts)
        //         if (i - cut >= 0) dp[i] = Math.max(dp[i], dp[i - cut] + 1);
        // }
        // out.println(dp[n]);
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        int res = num_cuts(cuts, n, memo);
        out.println(res);
        // ---------- problem code ends here ----------

        out.flush();
    }

    private static int num_cuts(int[] cuts, int n, int[] memo){
        // base cond
        if(n == 0) return 0; // perfectly cut
        if(n < 0) return Integer.MIN_VALUE/2; // impossible
        if(memo[n] != -1) return memo[n];
        int best = Integer.MIN_VALUE/2; // because of addition overflow below
        
        for(int cut: cuts){
            best = Math.max(best, 1 + num_cuts(cuts, n - cut, memo));
        }
        return memo[n] = best;
    }

    // Fast input
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        public FastScanner(InputStream is) { this.in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        public String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return null;
            }
            do {
                sb.append((char) c);
                c = read();
            } while (c > ' ');
            return sb.toString();
        }
        public int nextInt() throws IOException {
            String s = next();
            if (s == null) throw new NoSuchElementException("EOF");
            return Integer.parseInt(s);
        }
        public long nextLong() throws IOException { return Long.parseLong(next()); }
        public double nextDouble() throws IOException { return Double.parseDouble(next()); }
        public String nextLine() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c != '\n') sb.append((char)c);
            if (sb.length() == 0 && c == -1) return null;
            return sb.toString();
        }
    }
}
