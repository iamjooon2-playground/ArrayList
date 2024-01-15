package arraylist;


public class SimpleArrayList implements SimpleList {

    static final int CAPACITY_DEFAULT_SIZE = 10;

    private String[] elements;
    private int capacity;
    private int endIndex;

    public SimpleArrayList() {
        elements = new String[CAPACITY_DEFAULT_SIZE];
        capacity = CAPACITY_DEFAULT_SIZE;
        endIndex = 0;
    }

    @Override
    public boolean add(String value) {
        if (elements.length < endIndex + 1) {
            elements = resize();
        }
        elements[endIndex] = value;
        endIndex++;
        return true;
    }

    @Override
    public void add(int index, String value) {
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
    public String set(int index, String value) {
        validateIndexRange(index);
        String before = elements[index];
        elements[index] = value;
        return before;
    }

    @Override
    public String get(int index) {
        validateIndexRange(index);
        return elements[index];
    }

    private void validateIndexRange(int index) {
        if (index < 0 || index > endIndex) {
            throw new RuntimeException("인덱스 범위가 잘못되었어용");
        }
    }

    @Override
    public boolean contains(String value) {
        for (String element : elements) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String value) {
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
    public boolean remove(String value) {
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
    public String remove(int index) {
        validateIndexRange(index);
        String target = elements[index];

        for (int i = index; i < endIndex; i++) {
            elements[i] = elements[i + 1];
        }
        endIndex--;

        return target;
    }

    @Override
    public void clear() {
        elements = new String[CAPACITY_DEFAULT_SIZE];
        endIndex = 0;
        capacity = CAPACITY_DEFAULT_SIZE;
    }

    private String[] resize() {
        String[] temp = elements;
        capacity *= 2;
        elements = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            elements[i] = temp[i];
        }
        return elements;
    }
}
