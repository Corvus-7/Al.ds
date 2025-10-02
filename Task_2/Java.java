// Бинарная куча
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int[] data = {8, 3, 5, 1, 6, 2, 4, 7};
        for (int num : data) heap.offer(num);
        heap.offer(0);
        int minVal = heap.poll();
        System.out.println(minVal);
    }
}











// Биномиальная куча
class BinomialHeap {
    class Node {
        int key, degree;
        Node parent, child, sibling;
        
        Node(int k) {
            key = k;
            degree = 0;
            parent = child = sibling = null;
        }
    }
    
    private Node head;
    
    private Node merge(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        
        Node head, current;
        if (h1.degree <= h2.degree) {
            head = h1;
            h1 = h1.sibling;
        } else {
            head = h2;
            h2 = h2.sibling;
        }
        
        current = head;
        while (h1 != null && h2 != null) {
            if (h1.degree <= h2.degree) {
                current.sibling = h1;
                h1 = h1.sibling;
            } else {
                current.sibling = h2;
                h2 = h2.sibling;
            }
            current = current.sibling;
        }
        
        current.sibling = (h1 != null) ? h1 : h2;
        return head;
    }
    
    private void link(Node child, Node parent) {
        child.parent = parent;
        child.sibling = parent.child;
        parent.child = child;
        parent.degree++;
    }
    
    private void union() {
        if (head == null) return;
        
        Node prev = null, curr = head, next = curr.sibling;
        
        while (next != null) {
            if (curr.degree != next.degree || 
                (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else if (curr.key <= next.key) {
                curr.sibling = next.sibling;
                link(next, curr);
            } else {
                if (prev == null) head = next;
                else prev.sibling = next;
                link(curr, next);
                curr = next;
            }
            next = curr.sibling;
        }
    }
    
    public void insert(int key) {
        Node newNode = new Node(key);
        head = merge(head, newNode);
        union();
    }
    
    public int getMin() {
        if (head == null) return -1;
        
        Node min = head, curr = head.sibling;
        while (curr != null) {
            if (curr.key < min.key) min = curr;
            curr = curr.sibling;
        }
        return min.key;
    }
    
    public int extractMin() {
        if (head == null) return -1;
        
        Node min = head, prevMin = null;
        Node curr = head, prev = null;
        
        while (curr != null) {
            if (curr.key < min.key) {
                min = curr;
                prevMin = prev;
            }
            prev = curr;
            curr = curr.sibling;
        }
        
        if (prevMin != null) prevMin.sibling = min.sibling;
        else head = min.sibling;
        
        Node child = min.child;
        Node newHead = null;
        while (child != null) {
            Node next = child.sibling;
            child.sibling = newHead;
            child.parent = null;
            newHead = child;
            child = next;
        }
        
        head = merge(head, newHead);
        union();
        return min.key;
    }
    
    public static void main(String[] args) {
        BinomialHeap bh = new BinomialHeap();
        bh.insert(10);
        bh.insert(5);
        bh.insert(20);
        System.out.println(bh.getMin()); // 5
        System.out.println(bh.extractMin()); // 5
        System.out.println(bh.getMin()); // 10
    }
}








// Куча Фибоначчи
class FibonacciHeap {
    class Node {
        int key, degree;
        boolean mark;
        Node parent, child, left, right;
        
        Node(int k) {
            key = k;
            degree = 0;
            mark = false;
            left = right = this;
        }
    }
    
    private Node min;
    private int n;
    
    public void insert(int key) {
        Node x = new Node(key);
        if (min == null) {
            min = x;
        } else {
            x.right = min;
            x.left = min.left;
            min.left.right = x;
            min.left = x;
            if (key < min.key) min = x;
        }
        n++;
    }
    
    public int getMin() {
        return min != null ? min.key : -1;
    }
    
    public int extractMin() {
        Node z = min;
        if (z != null) {
            // Add children to root list
            if (z.child != null) {
                Node child = z.child;
                do {
                    Node next = child.right;
                    addToRootList(child);
                    child.parent = null;
                    child = next;
                } while (child != z.child);
            }
            
            // Remove z from root list
            z.left.right = z.right;
            z.right.left = z.left;
            
            if (z == z.right) {
                min = null;
            } else {
                min = z.right;
                consolidate();
            }
            n--;
            return z.key;
        }
        return -1;
    }
    
    private void addToRootList(Node x) {
        x.left = min.left;
        x.right = min;
        min.left.right = x;
        min.left = x;
        x.parent = null;
        x.mark = false;
    }
    
    private void consolidate() {
        int size = (int) (Math.log(n) / Math.log(2)) + 1;
        Node[] A = new Node[size];
        
        Node w = min;
        Node last = min.left;
        boolean stop = false;
        
        while (!stop) {
            if (w == last) stop = true;
            
            Node x = w;
            Node next = w.right;
            int d = x.degree;
            
            while (A[d] != null) {
                Node y = A[d];
                if (x.key > y.key) {
                    Node temp = x;
                    x = y;
                    y = temp;
                }
                link(y, x);
                A[d] = null;
                d++;
            }
            A[d] = x;
            w = next;
        }
        
        min = null;
        for (Node a : A) {
            if (a != null) {
                if (min == null) {
                    min = a;
                    a.left = a.right = a;
                } else {
                    a.left = min.left;
                    a.right = min;
                    min.left.right = a;
                    min.left = a;
                    if (a.key < min.key) min = a;
                }
            }
        }
    }
    
    private void link(Node y, Node x) {
        // Remove y from root list
        y.left.right = y.right;
        y.right.left = y.left;
        
        // Make y child of x
        y.parent = x;
        if (x.child == null) {
            x.child = y;
            y.left = y.right = y;
        } else {
            y.left = x.child.left;
            y.right = x.child;
            x.child.left.right = y;
            x.child.left = y;
        }
        x.degree++;
        y.mark = false;
    }
    
    public void decreaseKey(Node x, int newKey) {
        if (newKey > x.key) return;
        x.key = newKey;
        Node y = x.parent;
        
        if (y != null && x.key < y.key) {
            cut(x, y);
            cascadingCut(y);
        }
        if (x.key < min.key) min = x;
    }
    
    private void cut(Node x, Node y) {
        // Remove x from child list of y
        if (x.right == x) {
            y.child = null;
        } else {
            x.left.right = x.right;
            x.right.left = x.left;
            if (y.child == x) y.child = x.right;
        }
        y.degree--;
        addToRootList(x);
        x.mark = false;
    }
    
    private void cascadingCut(Node y) {
        Node z = y.parent;
        if (z != null) {
            if (!y.mark) {
                y.mark = true;
            } else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }
    
    public void delete(Node x) {
        decreaseKey(x, Integer.MIN_VALUE);
        extractMin();
    }
    
    public static void main(String[] args) {
        FibonacciHeap fh = new FibonacciHeap();
        fh.insert(7);
        fh.insert(26);
        fh.insert(30);
        fh.insert(39);
        fh.insert(10);
        
        System.out.println("Min: " + fh.getMin());
        System.out.println("Extracted: " + fh.extractMin());
        System.out.println("Extracted: " + fh.extractMin());
        System.out.println("Extracted: " + fh.extractMin());
    }
}









// Хеш-таблица
import java.util.HashMap;
public class Main {
    public static void main(String[] args) {
        HashMap<String, String> ht = new HashMap<>();
        
        ht.put("Alice", "January");
        
        if (ht.containsKey("Alice")) {
            System.out.println("Alice's birth month: " + ht.get("Alice"));
        }
        
        ht.remove("Alice");
        System.out.println("Alice exists: " + ht.containsKey("Alice"));
    }
}