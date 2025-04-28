import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** 这个测试会检测size()是否正确 */
    public void sizeTest01(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(5);  //size==1
        lld.addLast(6); //size==2
        lld.addFirst(4); // size==3
        lld.addLast(7);  //size==4
        assertThat(lld.size()).isEqualTo(4);
    }
    @Test
    /** 这里检查特殊情况,看看是否size被正确初始化了*/
    public void sizeTest02(){
        Deque61B<String> lld = new LinkedListDeque61B<>();
        assertThat(lld.size()).isEqualTo(0);
    }

    @Test
    /** 检查isEmpty()函数的测试*/
    public void isEmptyTest01(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        assertThat(lld.isEmpty()).isTrue();//本来就为空
    }
    @Test
    public void isEmptyTest02(){
        Deque61B<Integer> lld= new LinkedListDeque61B<>();
        lld.addFirst(3);
        assertThat(lld.isEmpty()).isFalse();
    }
    @Test
    /** 检查get()函数的测试 */
    public void getTest01(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        assertThat(lld.get(0)).isNull();
        assertThat(lld.get(-1)).isNull();
        assertThat(lld.get(223)).isNull();
    }
    @Test
    public void getTest02(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addFirst(3);
        lld.addLast(4);
        lld.addLast(5);
        lld.addLast(6);
        assertThat(lld.get(2)).isNotNull();
        assertThat(lld.get(4)).isNull();
        assertThat(lld.get(9989)).isNull();
        assertThat(lld.get(-1)).isNull();
        assertThat(lld.get(2)).isEqualTo(5);
        assertThat(lld.get(0)).isNotEqualTo(4);
        assertThat(lld.get(3)).isEqualTo(6);
    }
    @Test
    public void getRecursionTest(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addFirst(3);
        lld.addLast(4);
        lld.addLast(5);
        lld.addLast(6);
        assertThat(lld.getRecursive(2)).isNotNull();
        assertThat(lld.getRecursive(4)).isNull();
        assertThat(lld.getRecursive(9989)).isNull();
        assertThat(lld.getRecursive(-1)).isNull();
        assertThat(lld.getRecursive(2)).isEqualTo(5);
        assertThat(lld.getRecursive(0)).isNotEqualTo(4);
        assertThat(lld.getRecursive(3)).isEqualTo(6);
    }
    @Test
    public void removeFirstTest01(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        assertThat(lld.removeFirst()).isNull();
    }
    @Test
    public void removeFirstTest02(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addFirst(4);
        lld.addFirst(3);
        lld.addFirst(2);
        lld.addFirst(1);
        lld.removeFirst();
        assertThat(lld.toList()).containsExactly(2,3,4).inOrder();
    }
    @Test
    public void removeLastTest01(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        assertThat(lld.removeLast()).isNull();
    }
    @Test
    public void removeLastTest02(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addFirst(4);
        lld.addFirst(3);
        lld.addFirst(2);
        lld.addFirst(1);
        lld.removeLast();
        assertThat(lld.toList()).containsExactly(1,2,3).inOrder();
    }
}