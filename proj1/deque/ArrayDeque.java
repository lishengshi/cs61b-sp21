package deque;


import java.util.Iterator;

/*不变量
* -9 1 2 0 0 0 0 0 0 9
* 4
* addLast = szie
* the last is the szie-1 position
* addFirst
* 看做是循环数组，标记其nextFirst 和 nextLast
* nextFirst 和 nextLast 需要初始化两个相邻位置
* 跨越边界：nextFirst = 0 则 下一个时候 length-1
* nextLast = length-1 下下一个时候为 0
* 扩容：需要copy的是nextFirst后的和nextLast前的元素；顺序呢？
* 缩容：
*
* position
* */
public class ArrayDeque<T> implements Deque<T>{

    private static double FACTOR = 0.25;
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    /*返回index的下一个索引*/
    private int addOne(int index){
        return index == items.length - 1 ? 0 : index + 1;
    }


    /*Return:
    * 该index的前一个索引
    * */
    private int minusOne(int index){
        return index == 0 ? items.length - 1 : index - 1;
    }


    /*case1 不满，return
    *case2  nextFirst+1开始到length-1 0到nextLast-1
    *case3 0 - length-1
    * 0 1 2 3 4 5 6 7
    * nextFirst = 4
    * nextLast
    * */
    private void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        int index = 0;
        for (int i=0; i < size(); i++){
            nextFirst = addOne(nextFirst);
            index = index + 1;
            newArray[index] = items[nextFirst];
        }
        items = newArray;
        nextFirst = 0;
        nextLast = size() + 1;
    }


    /*首先判断是否满：
    * 是否需要跨界
    * */
    @Override
    public void addFirst(T item) {
        /*扩容条件:nextLast == addOne(nextFirst) 相邻*/
        if (size() == items.length){
            resize((int) (items.length / FACTOR));
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public static void main(String[] args) {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addFirst("i");
        ad.addFirst("h");
        ad.addFirst("g");
        ad.addFirst("f");
        ad.addFirst("e");
        ad.addFirst("d");
        ad.addFirst("c");
        ad.addFirst("b");
        ad.addFirst("a");
        ad.addLast("hello world");
        ad.removeFirst();
        ad.removeFirst();
        for (int i=0;i<ad.size()+1;i++){
            System.out.println(ad.get(i));
        }

    }


    @Override
    public void addLast(T item) {
        if (size() == items.length){
            resize((int) (items.length / FACTOR));
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {

    }

    /*在小于size<length*FACTORY时候，进行缩容
    * */
    @Override
    public T removeFirst() {
        int first = addOne(nextFirst);
        T value = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        return value;
    }

    @Override
    public T removeLast() {
        int last = minusOne(nextLast);
        T value = items[last];
        items[last] = null;
        size -= 1;
        return value;
    }

    @Override
    public T get(int index) {
        return items[index];
    }
    public boolean equals(Object o){
        return true;
    }

    public Iterator iterator(){
        return null;
    }
/*    private class ArrayDequeIterator<T> implements Iterator<T>{
        private int position ;
        private int first;
        public ArrayDequeIterator(){
            this.position = 0;
            this.first = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size();
        }

        @Override
        public T next() {
            this.first = addOne(nextFirst);
            first = addOne(first);
            return (T) items[first];
        }
    }*/
}
