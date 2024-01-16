package arraylist;

public class SimpleLinkedList implements SimpleList {

    private Node head;
    private int size;

    public SimpleLinkedList() {
        head = new Node();
        size = 0;
    }

    static class Node {

        private String value;
        private Node next;

        public Node() {
        }

        public Node(String value) {
            this.value = value;
        }
    }

    @Override
    public boolean add(String value) {
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }

        node.next = new Node(value);
        size++;
        return true;
    }

    @Override
    public void add(int index, String value) {
        validateIndexRange(index);

        Node before = getBeforeNodeBy(index);
        Node after = before.next;

        Node newNode = new Node(value);
        before.next = newNode;
        newNode.next = after;

        size++;
    }

    private Node getBeforeNodeBy(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void validateIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("인덱스 범위가 잘못됐어용");
        }
    }

    @Override
    public String set(int index, String value) {
        validateIndexRange(index);

        Node now = getNodeBy(index);
        String beforeValue = now.value;

        now.value = value;
        return beforeValue;
    }

    private Node getNodeBy(int index) {
        Node node = head;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String get(int index) {
        validateIndexRange(index);

        Node now = getNodeBy(index);
        return now.value;
    }

    @Override
    public boolean contains(String value) {
        Node node = head;
        while (node.next != null) {
            node = node.next;
            if (node.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String value) {
        Node node = head;
        for (int i = 0; i < size; i++) {
            node = node.next;
            if (value.equals(node.value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(String value) {
        Node node = head;
        while (node.next != null) {
            if (value.equals(node.next.value)) {
                node.next = node.next.next;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public String remove(int index) {
        validateIndexRange(index);

        Node node = getBeforeNodeBy(index);
        String target = node.next.value;
        node.next = node.next.next;

        size--;
        return target;
    }

    @Override
    public void clear() {
        head = new Node();
        size = 0;
    }
}
