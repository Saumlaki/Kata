/*
*Kata - MyLinkedList
* Saumlaki
* 2020.12.07
 */
import java.util.StringJoiner;

public class MyLinkedList<T> {

    //Переменные объекта MyLinkedList
    private Node head;//Голова коллекции, с нее начинаеться перебор элементов
    private Node LastNode;//Последний добавленный узел
    private int size;//Хранит текущее значение величины списка
    
    //Геттеры объекта MyLinkedList
    public int getSize() {
        return size;
    }
    
    //Основные методы по работе с листом
    //Добавление нового элемента
    public void add(T values) {

        //Создаем новый узел
        Node newNode;

        if (size == 0) {
            //Если это первый добавляемый объект то работаем с ним по особому
            newNode = new Node(values, null);//Создаем новый узел, в качестве предыдущего узла у него будет null
            head = newNode;//Устанавливаем голову
        } else {
            //Если это второй и последующие узлы то обрабатываем их следующим образом
            newNode = new Node(values, LastNode);//Создаем новый узел, в качестве предыдущего узла у него будет LastNode
            LastNode.setNext(newNode);//Для предыдущего узла(LastNode) в качестве последующего(next) устанавливаем новый узел
        }
        LastNode = newNode;//В качестве последнего узла устанавливаем новый добавленный узел
        size++;//Увеличиваем размер списка
    }

    //Получение элемента по индексу
    public T get(int index) {
        //Если вышли за границе массива то кидаем исключения
        if (index > size - 1) throw new ArrayIndexOutOfBoundsException();
        if (index < 0) throw new ArrayIndexOutOfBoundsException();

        //Получаем элемент по индексу
        Node returnNode = head;
        int tempIndex = 0;
        while (tempIndex++ < index) {
            returnNode = returnNode.getNext();
        }
        return returnNode.getValues();
    }

    //Удаление элемента по индексу
    public void remove(int index) {
        //Если вышли за границе массива то кидаем исключения
        if (index > size - 1) throw new ArrayIndexOutOfBoundsException();
        if (index < 0) throw new ArrayIndexOutOfBoundsException();

        //Получаем элемент по индексу и рвем на нем цепочку
        Node removeNode = head;
        int tempIndex = 0;
        while (tempIndex++ < index) {
            removeNode = removeNode.getNext();
        }

        //Получили узел который удаляем.
        if (removeNode.getPrevious() == null) {
            //Проверяем являеться ли узел головое
            head = removeNode.getNext();
        } else if (removeNode.getNext() == null) {
            //Проверяем являеться ли узел последним узлом
            removeNode.getPrevious().setNext(null);//Ссылаем передыдущиу узел на null
        } else {
            //Узел где то в середине
            Node nextNode = removeNode.getNext();
            Node previousNode = removeNode.getPrevious();

            nextNode.setPrevious(previousNode);
            previousNode.setNext(nextNode);
        }
        size--;
    }
    
    //Внутренние классы
    
    //Класс Node являеться элементом цепочки MyLinkedList
    class Node {

        //Переменные объекта Node
        Node next;//Следующее значение за данным узлом
        Node previous;//Предыдущее значение стоящее перед этим узлом
        T values;//Значение которое хранит узел

        //Конструктор объекта Node
        public Node(T values, Node previous) {
            this.values = values;
            this.previous = previous;
        }

        //Сеттеры объекта Node
        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        //Геттеры объекта Node
        public Node getNext() {
            return next;
        }

        public Node getPrevious() {
            return previous;
        }

        public T getValues() {
            return values;
        }
    }

    @Override
    public String toString() {

        StringJoiner stringJoiner = new StringJoiner("<->", "{", "}");
        Node tempNode = head;
        while (tempNode != null) {
            stringJoiner.add(tempNode.getValues().toString());
            tempNode = tempNode.getNext();
        }

        return stringJoiner.toString();
    }

    public static void main(String[] args) {

        //Создаем объект MyLinkedList(связанный список)
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        //Наш список должен делать простейшие опреации
        //1. Добавление элементов
        //2.Получение элемента по индексу
        //3.Удаление элемента по индексу
        
        //Проверка
        //1. Добавление элементов
        for (int i = 0; i < 10; i++) {
            myLinkedList.add(i);
        }
        System.out.println(myLinkedList);

        //2.Получение элемента по индексу
        System.out.println(myLinkedList.get(0));
        System.out.println(myLinkedList.get(5));
        System.out.println(myLinkedList.get(myLinkedList.getSize() - 1));

        //3.Удаление элемента по индексу
        myLinkedList.remove(0);
        myLinkedList.remove(5);
        myLinkedList.remove(myLinkedList.getSize() - 1);
        System.out.println(myLinkedList);
    }
}


