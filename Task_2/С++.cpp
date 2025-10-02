// Бинарная куча
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int main() {
    vector<int> data = {8, 3, 5, 1, 6, 2, 4, 7};
    
    // Создание min-heap
    make_heap(data.begin(), data.end(), greater<int>());
    
    // Добавление нового элемента
    data.push_back(0);
    push_heap(data.begin(), data.end(), greater<int>());
    
    // Извлечение минимального элемента
    pop_heap(data.begin(), data.end(), greater<int>());
    int min_val = data.back();
    data.pop_back();
    cout << min_val;  // Выведет 0
    return 0;
}








// Биномиальная куча
#include <iostream>
using namespace std;

struct Node {
    int key, degree;
    Node *parent, *child, *sibling;
    Node(int k) : key(k), degree(0), parent(nullptr), child(nullptr), sibling(nullptr) {}
};

class BinomialHeap {
    Node* head = nullptr;
    
    Node* merge(Node* a, Node* b) {
        if (!a || !b) return a ? a : b;
        if (a->degree > b->degree) swap(a, b);
        Node *res = a;
        a = a->sibling;
        Node* cur = res;
        while (a && b) {
            if (a->degree <= b->degree) {
                cur->sibling = a;
                a = a->sibling;
            } else {
                cur->sibling = b;
                b = b->sibling;
            }
            cur = cur->sibling;
        }
        cur->sibling = a ? a : b;
        return res;
    }
    
    void link(Node* c, Node* p) {
        c->parent = p;
        c->sibling = p->child;
        p->child = c;
        p->degree++;
    }
    
    void consolidate() {
        if (!head) return;
        Node *prev = nullptr, *x = head, *next = x->sibling;
        while (next) {
            if (x->degree != next->degree || (next->sibling && next->sibling->degree == x->degree)) {
                prev = x;
                x = next;
            } else if (x->key <= next->key) {
                x->sibling = next->sibling;
                link(next, x);
            } else {
                if (!prev) head = next;
                else prev->sibling = next;
                link(x, next);
                x = next;
            }
            next = x->sibling;
        }
    }
    
public:
    void insert(int key) {
        head = merge(head, new Node(key));
        consolidate();
    }
    
    int getMin() {
        if (!head) return -1;
        int minKey = head->key;
        for (Node* cur = head; cur; cur = cur->sibling)
            if (cur->key < minKey) minKey = cur->key;
        return minKey;
    }
    
    int extractMin() {
        if (!head) return -1;
        Node *minNode = head, *prev = nullptr, *cur = head;
        while (cur->sibling) {
            if (cur->sibling->key < minNode->key) {
                minNode = cur->sibling;
                prev = cur;
            }
            cur = cur->sibling;
        }
        prev ? prev->sibling = minNode->sibling : head = minNode->sibling;
        
        Node* child = minNode->child;
        while (child) {
            Node* next = child->sibling;
            child->sibling = head;
            head = child;
            child->parent = nullptr;
            child = next;
        }
        int key = minNode->key;
        delete minNode;
        consolidate();
        return key;
    }
};

int main() {
    BinomialHeap bh;
    bh.insert(10);
    bh.insert(5);
    bh.insert(20);
    cout << bh.getMin() << endl;
    cout << bh.extractMin() << endl;
    cout << bh.getMin() << endl;
    return 0;
}







// Куча Фибоначчи
#include <iostream>
using namespace std;

struct Node {
    int key;
    Node *left, *right;
    Node(int k) : key(k) { left = right = this; }
};

class FibonacciHeap {
    Node* min = nullptr;
public:
    void insert(int key) {
        Node* n = new Node(key);
        if (!min) min = n;
        else {
            n->left = min;
            n->right = min->right;
            min->right->left = n;
            min->right = n;
            if (key < min->key) min = n;
        }
    }
    
    int getMin() { return min ? min->key : -1; }
    
    void print() {
        if (!min) return;
        Node* cur = min;
        do {
            cout << cur->key << " ";
            cur = cur->right;
        } while (cur != min);
        cout << endl;
    }
};

int main() {
    FibonacciHeap fh;
    fh.insert(10);
    fh.insert(5);
    fh.insert(20);
    fh.insert(3);
    
    fh.print();
    cout << fh.getMin() << endl;
    
    return 0;
}









# Хеш-таблица
#include <iostream>
#include <unordered_map>
using namespace std;

int main() {
    unordered_map<string, string> ht;
    ht["Alice"] = "January";
    cout << ht["Alice"] << endl;
    ht.erase("Alice");
    return 0;
}