package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    public Comparator<T> dComparator;//创建一个比较器类型的实例变量,通过.compare(v1,v2)来调用
    public MaxArrayDeque61B(Comparator<T> c){
         super();
         this.dComparator=c;
    }
    public T max(){
        if(this.size()==0){
            return null;
        }
        else{
            return max(this.dComparator);
        }
    }
    public T max(Comparator<T> c){
        if(this.size()==0){
            return null;
        }
        else{
            Iterator<T> iterator = this.iterator();
            T maxElement = iterator.next(); // 假设第一个元素是最大的

            // 遍历剩余元素
            while (iterator.hasNext()) {
                T current = iterator.next();
                // 如果当前元素比最大元素大，则更新最大元素
                if (c.compare(current, maxElement) > 0) {
                    maxElement = current;
                }
            }

            return maxElement;
        }
    }
}
