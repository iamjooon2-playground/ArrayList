package list;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T[] array) {
        SimpleArrayList<T> result = new SimpleArrayList<>();
        for (T t : array) {
            result.add(t);
        }
        return result;
    }

    static <T extends Number> double sum(SimpleList<T> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            T value = list.get(i);
            sum += value.doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> list) {
        SimpleList<T> result = new SimpleArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).doubleValue() > 0) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    static <T> void copy(SimpleList<? extends T> from, SimpleList<? super T> to) {
        while (!from.isEmpty()) {
            to.add(from.remove(0));
        }
    }

    boolean add(T value);

    void add(int index, T value);

    T set(int index, T value);

    T get(int index);

    boolean contains(T value);

    int indexOf(T value);

    int size();

    boolean isEmpty();

    boolean remove(T value);

    T remove(int index);

    void clear();
}
