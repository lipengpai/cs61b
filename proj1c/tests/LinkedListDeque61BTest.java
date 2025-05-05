import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {
    @Test
    public void Test01(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);
        assertThat(lld).containsExactly(1,2,3).inOrder();
    }
    @Test
    public void Test02(){
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        assertThat(lld).isEqualTo(lld1);
    }

}
