package list;


public class SimpleArrayList<T> implements SimpleList<T> {

    static final int CAPACITY_DEFAULT_SIZE = 10;

    private T[] elements;
    private int capacity;
    private int endIndex;

    public SimpleArrayList() {
        elements = (T[]) new Object[CAPACITY_DEFAULT_SIZE];
        capacity = CAPACITY_DEFAULT_SIZE;
        endIndex = 0;
    }

    public SimpleArrayList(T... values) {
        this();
        for (T value : values) {
            this.add(value);
        }
    }

    @Override
    public boolean add(T value) {
        if (elements.length < endIndex + 1) {
            elements = resize();
        }
        elements[endIndex] = value;
        endIndex++;
        return true;
    }

    private T[] resize() {
        T[] temp = elements;
        capacity *= 2;
        elements = (T[]) new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            elements[i] = temp[i];
        }
        return elements;
    }

    @Override
    public void add(int index, T value) {
        validateIndexRange(index);
        if (elements.length < endIndex + 1) {
            elements = resize();
        }
        for (int i = index; i < endIndex; i++) {
            elements[i + 1] = elements[i];
        }
        elements[index] = value;
        endIndex++;
    }

    @Override
    public T set(int index, T value) {
        validateIndexRange(index);
        Object before = elements[index];
        elements[index] = value;
        return (T) before;
    }

    @Override
    public T get(int index) {
        validateIndexRange(index);
        return elements[index];
    }

    private void validateIndexRange(int index) {
        if (index < 0 || index > endIndex) {
            throw new RuntimeException("인덱스 범위가 잘못되었어용");
        }
    }

    @Override
    public boolean contains(T value) {
        for (Object element : elements) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i <= endIndex; i++) {
            if (elements[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return endIndex;
    }

    @Override
    public boolean isEmpty() {
        return endIndex == 0;
    }

    @Override
    public boolean remove(T value) {
        int target = -1;
        for (int i = 0; i < endIndex; i++) {
            if (value.equals(elements[i])) {
                target = i;
            }
        }
        if (target == -1) {
            return false;
        }
        for (int i = target; i < endIndex; i++) {
            elements[i] = elements[i + 1];
        }
        endIndex--;
        return true;
    }

    @Override
    public T remove(int index) {
        validateIndexRange(index);
        Object target = elements[index];
        for (int i = index; i < endIndex; i++) {
            elements[i] = elements[i + 1];
        }
        endIndex--;
        return (T) target;
    }

    @Override
    public void clear() {
        elements = (T[]) new Object[CAPACITY_DEFAULT_SIZE];
        endIndex = 0;
        capacity = CAPACITY_DEFAULT_SIZE;
    }
}
