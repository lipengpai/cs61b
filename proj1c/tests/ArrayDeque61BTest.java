import deque.ArrayDeque61B;
import deque.Deque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    public void Test01(){
        Deque61B<Integer> arr=new ArrayDeque61B<>();
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);
        assertThat(arr).containsExactly(1,2,3).inOrder();
    }
    @Test
    public void Test02(){
        Deque61B<Integer> arr=new ArrayDeque61B<>();
        Deque61B<Integer> arr2=new ArrayDeque61B<>();
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);
        arr2.addLast(1);
        arr2.addLast(2);
        arr2.addLast(3);
        assertThat(arr).isEqualTo(arr2);
    }
    @Test
    public void Test03(){
        Deque61B<Integer> arr=new ArrayDeque61B<>();
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);
        assertThat(arr.toString()).isEqualTo("[1, 2, 3]");
    }
}
