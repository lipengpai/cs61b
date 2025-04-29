import java.util.List;
import java.lang.Math;
public class ArrayDeque61B<T> implements Deque61B<T> {
    public int size;
    public int capacity;
    public T[] array;
    public int first;
    public int last;
    public ArrayDeque61B() {
          capacity=8;
          size=0;
          array=(T[]) new Object[capacity];
          first=Math.floorMod(0,capacity);
          last=Math.floorMod(capacity-1,capacity);
    }
    @Override
    public void addFirst(T x) {
        if (size < capacity) {
            first = Math.floorMod(first - 1, capacity);
            array[first] = x;
            size++;
        }
        /*else{
            capacity*=2;
            T[] other= (T[]) new Object[capacity];
            for(int i=0;i<array.length;i++){
                other[i]=array[i];
            }
            first=Math.floorMod(first-1,capacity);
            other[first]=x;
            array=other;
            size++;
        }*/
    }
    @Override
    public void addLast(T x) {
         if(size<capacity){
             last=Math.floorMod(last+1,capacity);
             array[last]=x;
             size++;
         }
         else{

         }
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if(index<0 || index>=size){
            return null;
        }
        else{
            return array[index];
        }
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
