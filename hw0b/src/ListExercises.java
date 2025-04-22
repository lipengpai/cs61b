import java.util.*;

public class ListExercises {

    /**
     * Returns the total sum in a list of integers
     */
    public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;
        for (int elem : L) {
            sum += elem;
        }
        return sum;
    }

    /**
     * Returns a list containing the even numbers of the given list
     */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> newlst = new ArrayList<>();//创建动态数组
        if (L.isEmpty()) {
            return newlst;
        } else {
            for (int elem : L) {
                if (elem % 2 == 0) {
                    newlst.add(elem);
                }
            }
            return newlst;
        }
    }

    /**
     * Returns a list containing the common item of the two given lists
     */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> res = new ArrayList<>();
        for (int elem1 : L1) {
            for (int elem2 : L2) {
                if (elem1 == elem2) {
                    res.add(elem1);
                }
            }
        }
        return res;
    }


    /**
     * Returns the number of occurrences of the given character in a list of strings.
     */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int cnt = 0;
        for (int i = 0; i < words.size(); i++) {
            // 使用 words.get(i) 来访问 List 元素
            String word = words.get(i);

            // 遍历单词的每个字符，检查是否与 c 匹配
            for (int j = 0; j < word.length(); j++) {
                if (word.charAt(j) == c) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
