package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{

    private Node<T> sentinel ;
    private int size;

    public class Node<T>{
        public T item;
        Node<T> next;
        Node<T> prev;
        public Node(T item){
            this.item = item;
            next = null;
            prev = null;
        }
        public Node(Node<T> prev,T item,Node<T> next){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }


    /*链表初始化
    循环哨兵
    one point
    * */
    public LinkedListDeque(){
        size=0;
        sentinel = new Node<>(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }



    /*add first
    * */
    @Override
    public void addFirst(T item) {
        size += 1;
        sentinel.next = new Node<>(sentinel,item,sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    public void addLast(T item) {
        size += 1;
        sentinel.prev = new Node<T>(sentinel.prev,item,sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        Node<T> p = sentinel;
        int index = size;
        StringBuilder result = new StringBuilder();
        while (index!=0){
            p = p.next;
            result.append(p.item+" ");
            index -= 1;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("last");
        System.out.println(lld1.sentinel.next.item);
    }

    @Override
    public T removeFirst() {
        if (!isEmpty()){
            size -= 1;
            Node<T> p = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            return p.item;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (!isEmpty()){
            Node<T> p = sentinel.prev;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            return p.item;
        }
        return null;
    }

    @Override
    public T get(int index) {
        Node<T> p = sentinel;
        if (index >= size){
            return null;
        }
        while (index >= 0){
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    public T getRecursive(int index){
        if (index == 0){
            return sentinel.next.item;
        }else {
            return getRecursive(index-1);
        }
    }

    public Iterator iterator(){
        return null;
    }
    public boolean equals(Object o){
        return false;
    }


}
