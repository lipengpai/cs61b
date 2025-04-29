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
     /** 检查当array不需要调整大小时能否正确在头部添加元素*/
     public void addFirstTest01(){
            Deque61B<Integer> arr=new ArrayDeque61B<>();
            arr.addFirst(3);
            arr.addFirst(2);
            arr.addFirst(1);
            assertThat(arr.toList()).containsExactly(1,2,3).inOrder();
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
}
