package com.ganht.algorithm.leetcode;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such
 * minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 *
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 *
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 * @author haitian.gan
 */
public class MinimumWindowSubsequence {

    /**
     * 这道题给了我们两个字符串S和T，让我们找出S的一个长度最短子串W，使得T是W的子序列，如果长度相同，取起始位置靠前的。清楚子串和子序列的区别，
     * 那么题意就不难理解，题目中给的例子也很好的解释了题意。我们经过研究可以发现，返回的子串的起始字母和T的起始字母一定相同，这样才能保证最短。
     * 那么你肯定会想先试试暴力搜索吧，以S中每个T的起始字母为起点，均开始搜索字符串T，然后维护一个子串长度的最小值。如果是这种思路，那么还是趁
     * 早打消念头吧，博主已经替你试过了，OJ 不依。原因也不难想，假如S中有大量的连续b，并且如果T也很长的话，这种算法实在是不高效啊。根据博主多
     * 年经验，这种玩字符串且还是 Hard 的题，十有八九都是要用动态规划 Dynamic Programming 来做的，那么就直接往 DP 上去想吧。DP 的第一步
     * 就是设计 dp 数组，像这种两个字符串的题，一般都是一个二维数组，想想该怎么定义。确定一个子串的两个关键要素是起始位置和长度，那么我们的 dp
     * 值到底应该是定起始位置还是长度呢？That is a question! 仔细想一想，其实起始位置是长度的基础，因为我们一旦知道了起始位置，那么当前位置
     * 减去起始位置，就是长度了，所以我们 dp 值定为起始位置。那么 dp[i][j] 表示范围S中前i个字符包含范围T中前j个字符的子串的起始位置，注意这
     * 里的包含是子序列包含关系。然后就是确定长度了，有时候会使用字符串的原长度，有时候会多加1，看个人习惯吧，这里博主长度多加了个1。
     *
     * OK，下面就是重中之重啦，求状态转移方程。一般来说，dp[i][j] 的值是依赖于之前已经求出的dp值的，在递归形式的解法中，dp数组也可以看作是记
     * 忆数组，从而省去了大量的重复计算，这也是 dp 解法凌驾于暴力搜索之上的主要原因。牛B的方法总是最难想出来的，dp 的状态转移方程就是其中之一。
     * 在脑子一片浆糊的情况下，博主的建议是从最简单的例子开始分析，比如 S = "b", T = "b", 那么我们就有 dp[1][1] = 0，因为S中的起始位置为0，
     * 长度为1的子串可以包含T。如果当 S = "d", T = "b"，那么我们有 dp[1][1] = -1，因为我们的dp数组初始化均为 -1，表示未匹配或者无法匹配。
     * 下面来看一个稍稍复杂些的例子，S = "dbd", T = "bd"，我们的dp数组是：
     *
     *    ∅  b  d
     * ∅  ?  ?  ?
     * d  ? -1 -1
     * b  ?  1 -1
     * d  ?  1  1
     * 这里的问号是边界，我们还不知道如何初给边界赋值，我们看到，为 -1 的地方是对应的字母不相等的地方。我们首先要明确的是 dp[i][j] 中的j不能
     * 大于i，因为T的长度不能大于S的长度，所以j大于i的 dp[i][j] 一定都是-1的。再来看为1的几个位置，首先是 dp[2][1] = 1，这里表示db包含b的
     * 子串起始位置为1，make sense！然后是 dp[3][1] = 1，这里表示 dbd 包含b的子串起始位置为1，没错！然后是 dp[3][2] = 1，这里表示 dbd
     * 包含 bd 的起始位置为1，all right! 那么我们可以观察出，当 S[i] == T[j] 的时候，实际上起始位置和 dp[i - 1][j - 1] 是一样的，比如
     * dbd 包含 bd 的起始位置和 db 包含b的起始位置一样，所以可以继承过来。那么当 S[i] != T[j] 的时候，怎么搞？其实是和 dp[i - 1][j] 是
     * 一样的，比如 dbd 包含b的起始位置和 db 包含b的起始位置是一样的。
     *
     * 嗯，这就是状态转移方程的核心了，下面再来看边界怎么赋值，由于j比如小于等于i，所以第一行的第二个位置往后一定都是-1，我们只需要给第一列赋值
     * 即可。通过前面的分析，我们知道了当 S[i] == T[j] 时，我们取的是左上角的 dp 值，表示当前字母在S中的位置，由于我们dp数组提前加过1，所以
     * 第一列的数只要赋值为当前行数即可。最终的 dp 数组如下：
     *
     *    ∅  b  d
     * ∅  0 -1 -1
     * d  1 -1 -1
     * b  2  1 -1
     * d  3  1  1
     * 为了使代码更加简洁，我们在遍历完每一行，检测如果 dp[i][n] 不为-1，说明T已经被完全包含了，且当前的位置跟起始位置都知道了，我们计算出长
     * 度来更新一个全局最小值 minLen，同时更新最小值对应的起始位置 start，最后取出这个全局最短子串，如果没有找到返回空串即可，参见代码如下：
     *
     * class Solution {
     * public:
     *     string minWindow(string S, string T) {
     *         int m = S.size(), n = T.size(), start = -1, minLen = INT_MAX;
     *         vector<vector<int>> dp(m + 1, vector<int>(n + 1, -1));
     *         for (int i = 0; i <= m; ++i) dp[i][0] = i;
     *         for (int i = 1; i <= m; ++i) {
     *             for (int j = 1; j <= min(i, n); ++j) {
     *                 dp[i][j] = (S[i - 1] == T[j - 1]) ? dp[i - 1][j - 1] : dp[i - 1][j];
     *             }
     *             if (dp[i][n] != -1) {
     *                 int len = i - dp[i][n];
     *                 if (minLen > len) {
     *                     minLen = len;
     *                     start = dp[i][n];
     *                 }
     *             }
     *         }
     *         return (start != -1) ? S.substr(start, minLen) : "";
     *     }
     * };
     *
     * hard难度的字符串问题，优先考虑的是dp
     * @param S
     * @param T
     * @return
     */
    public String minWindow(String S, String T) {
        return null;
    }

}
