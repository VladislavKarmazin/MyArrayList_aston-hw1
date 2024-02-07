package org.example;

import java.util.Arrays;
import java.util.Objects;

/**
 * Класс представляет собой простую реализацию коллекции.
 * Не реализует стандартные интерфейсы Java Collections Framework.
 *
 * @param <E> тип элементов, хранящихся в коллекции.
 */
public class MyArrayList<E extends Comparable<E>> implements MyArrayList_Interface<E> {

    /**
     * Начальная емкость по умолчанию.
     */
    private final static int DEFAULT_CAPACITY = 10; // аппер кейс положен по конвенции
    public static final String MSG = "Отрицательная емкость";

    /**
     * Текущая емкость.
     */
    private int size;

    /**
     * Буфер массива, в котором хранятся элементы MyArrayList.
     */
    private Object[] array;

    /**
     * Конструктор по умолчанию. Создает пустую коллекцию с начальной емкостью 10.
     */
    public MyArrayList() {
        this.size = 0;
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор с задаваемой начальной емкостью.
     *
     * @param capacity начальная емкость MyArrayList.
     */
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(MSG);// строки всегда в статические константы
        }
        this.size = 0;
        this.array = new Object[capacity];
    }

    /**
     * Увеличиваем размер массива вдвое, когда текущий полностью заполнен. Промежуточный метод.
     */
    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    /**
     * Добавляет указанный элемент в конец этого списка.
     *
     * @param element элемент, который будет добавлен в этот список
     * @return true если эта коллекция изменилась в результате вызова.
     */
    @Override
    public boolean add(E element) {
        ensureCapacity();
        array[size++] = element;
        return true;
    }

    /**
     * Добавляет указанный элемент в коллекцию по указанному индексу,
     * смещая все последующие элементы. Если индекс равен текущему размеру коллекции,
     * элемент добавляется в конец коллекции.
     *
     * @param index   Индекс, по которому нужно добавить элемент.
     * @param element Элемент, который следует добавить в коллекцию.
     * @return true, если элемент успешно добавлен.
     * @throws IndexOutOfBoundsException если индекс находится за пределами допустимого диапазона.
     */
    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity();

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        size++;
        return true;
    }


    /**
     * Возвращает элемент коллекции по указанному индексу.
     *
     * @param index Индекс элемента для получения.
     * @return Элемент, находящийся по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс находится за пределами допустимого диапазона.
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    /**
     * Удаляет указанный элемент по заданному индексу, смещая элементы к началу коллекции
     *
     * @param index индекс удаляемого элемента
     * @return true, если элемент успешно удален
     */
    @Override
    public boolean remove(int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return true;
    }

    /**
     * Удаляет первое вхождение указанного элемента из коллекции.
     *
     * @param element Элемент, который нужно удалить.
     * @return true, если элемент был успешно удален, иначе false.
     */
    @Override
    public boolean removeFirst(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Очищает коллекцию, устанавливая ее размер в 0.
     *
     * @return true, если размер установлен на значение 0.
     */
    @Override
    public boolean removeAll() {
        size = 0;
        return true;
    }

    /**
     * Возвращает текущий объем коллекции.
     *
     * @return size.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Устанавливает элемент по заданному индексу.
     * @param index индекс задаваемого элемента.
     * @param element сам элемент.
     */
    @Override
    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    /**
     * Алгоритм быстрой сортировки
     */
    @Override
    public void quickSort() {
        quickSortRecursive(0, size - 1);
    }

    private void quickSortRecursive(int low, int high) {
        if (low < high) {
            int partitionIndex = partition(low, high);

            quickSortRecursive(low, partitionIndex - 1);
            quickSortRecursive(partitionIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        E pivot = get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (get(j).compareTo(pivot) < 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        E temp = get(i);
        set(i, get(j));
        set(j, temp);
    }

}
