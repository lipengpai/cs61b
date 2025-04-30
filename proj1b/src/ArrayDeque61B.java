import java.util.ArrayList;
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
          array=(T[]) new Object[8];
          first=0;
          last=0;
    }
    @Override
    public void addFirst(T x) {
        if (size < capacity) {
            first = Math.floorMod(first - 1, capacity);
            array[first] = x;
            size++;
        }
       else{
           capacity*=2;
           T[] newarray=(T[]) new Object[capacity];
           for(int i=0;i<size;i++){
               newarray[i]=array[i];
           }
            first = Math.floorMod(first - 1, capacity);
            newarray[first] = x;
            array=newarray;
            size++;
        }
    }
    @Override
    public void addLast(T x) {
         if(size<capacity){
             array[last]=x;
             last=Math.floorMod(last+1,capacity);
             size++;
         }
         else{
             capacity*=2;
             T[] newarray=(T[]) new Object[capacity];
             for(int i=0;i<size;i++){
                 newarray[i]=array[i];
             }
             newarray[size] = x;
             first = 0;
             last = size + 1;
             array = newarray;
             size++;
         }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i=0;i<size;i++){
            T value= get(i);
            returnList.addLast(value);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(first==last){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
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
            return array[Math.floorMod(index+first,capacity)];
        }
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
