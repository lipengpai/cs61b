import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }
     @Test
     public void tolistTest(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         arr.addLast(1);
         arr.addLast(2);
         arr.addLast(3);
         assertThat(arr.toList()).containsExactly(1,2,3).inOrder();
     }
     @Test
     /** 检查当array不需要调整大小时能否正确在头部添加元素*/
     public void addFirstTest01(){
            Deque61B<Integer> arr=new ArrayDeque61B<>();
            arr.addFirst(3);
            arr.addFirst(2);
            arr.addFirst(1);
            assertThat(arr.toList()).containsExactly(1,2,3).inOrder();
     }
     @Test
     /** 检查需要改变大小时的添加 */
     public void addFirstTest02(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         arr.addFirst(10);
         arr.addFirst(9);
         arr.addFirst(8);
         arr.addFirst(7);
         arr.addFirst(6);
         arr.addFirst(5);
         arr.addFirst(4);
         arr.addFirst(3);
         arr.addFirst(2);
         arr.addFirst(1);
         assertThat(arr.toList()).containsExactly(1,2,3,4,5,6,7,8,9,10).inOrder();
     }
     @Test
     public void addLastTest01(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         arr.addLast(1);
         arr.addLast(2);
         arr.addLast(3);
         assertThat(arr.toList()).containsExactly(1,2,3).inOrder();
     }
     @Test
     public void addLastTest02(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         arr.addLast(1);
         arr.addLast(2);
         arr.addLast(3);
         arr.addLast(4);
         arr.addLast(5);
         arr.addLast(6);
         arr.addLast(7);
         arr.addLast(8);
         arr.addLast(9);
         arr.addLast(10);
         assertThat(arr.toList()).containsExactly(1,2,3,4,5,6,7,8,9,10).inOrder();
     }
     @Test
    public void getTest01(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         arr.addLast(1);
         arr.addLast(2);
         arr.addLast(3);
         assertThat(arr.get(1)).isNotNull();
         assertThat(arr.get(2)).isEqualTo(3);
     }
     @Test
    public void getTest02(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         assertThat(arr.get(999)).isNull();
     }
     @Test
    public void getTest03(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         arr.addFirst(3);
         arr.addFirst(2);
         arr.addFirst(1);
         assertThat(arr.get(2)).isEqualTo(3);
     }
     @Test
    public void getTest04(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         //arr.addFirst(10);
        //arr.addFirst(9);
         //arr.addFirst(8);
         arr.addFirst(7);
         arr.addFirst(6);
         arr.addFirst(5);
         arr.addFirst(4);
         arr.addFirst(3);
         arr.addFirst(2);
         arr.addFirst(1);
         arr.addFirst(0);
         assertThat(arr.get(0)).isEqualTo(0);
         assertThat(arr.get(4)).isEqualTo(4);
         assertThat(arr.get(7)).isEqualTo(7);
         //assertThat(arr.get(10)).isEqualTo(10);
     }
     @Test
    public void isEmptyTest01(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         arr.addFirst(4);
         arr.addFirst(3);
         assertThat(arr.isEmpty()).isFalse();
     }
     @Test
    public void isEmptyTest02(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         assertThat(arr.isEmpty()).isTrue();
     }
     @Test
    public void sizeTest(){
         Deque61B<Integer> arr=new ArrayDeque61B<>();
         arr.addFirst(4);
         arr.addFirst(3);
         arr.addFirst(2);
         assertThat(arr.size()).isEqualTo(3);
     }
}
