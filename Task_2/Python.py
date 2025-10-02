Бинарная куча
import heapq
Numbers = [8, 3, 5, 1, 6, 2, 4, 7] // создание списка с исходными данными для кучи
heapq.heapify(Numbers) // превращение списка в кучу
heapq.heappush(Numbers, 0) // добавление нового элемента в кучу
minn = heapq.heappop(Numbers) // извлечение минимального элемента из кучи
print(minn)




Биномиальная куча
class BinomialHeap:
    class Node:
        slots = ('key', 'degree', 'child', 'sibling')
        def init(self, key):
            self.key, self.degree, self.child, self.sibling = key, 0, None, None

    def init(self): self.head = None

    def insert(self, key):
        temp = BinomialHeap()
        temp.head = self.Node(key)
        self.union(temp)

    def get_min(self):
        return min((x.key for x in self.nodes()), default=None)

    def extract_min(self):
        if not self.head: return None
        min_node = min(self.nodes(), key=lambda x: x.key)
        
        # Remove min and add children
        nodes = [x for x in self.nodes() if x != min_node]
        child = min_node.child
        while child:
            nodes.append(child)
            child.child, child = None, child.sibling
        
        # Rebuild heap
        self.head = None
        for node in nodes:
            node.sibling, self.head = self.head, node
        self._consolidate()
        
        return min_node.key

    def nodes(self):
        curr = self.head
        while curr: yield curr; curr = curr.sibling

    def consolidate(self):
        by_degree = {}
        for node in self.nodes():
            while node.degree in by_degree:
                other = by_degree.pop(node.degree)
                node = self.link(node, other)
            by_degree[node.degree] = node
        self.head = None
        for node in reversed(by_degree.values()):
            node.sibling, self.head = self.head, node

    def link(self, a, b):
        if a.key > b.key: a, b = b, a
        b.sibling, a.child, a.degree = a.child, b, a.degree + 1
        return a

    def union(self, other):
        self.head = self.merge(self.head, other.head)
        self.consolidate()

    def merge(self, a, b):
        if not a or not b: return a or b
        if a.degree <= b.degree:
            head, a = a, a.sibling
        else:
            head, b = b, b.sibling
        curr = head
        while a and b:
            if a.degree <= b.degree:
                curr.sibling, a = a, a.sibling
            else:
                curr.sibling, b = b, b.sibling
            curr = curr.sibling
        curr.sibling = a or b
        return head

bh = BinomialHeap()
for x in [10, 5, 20]: bh.insert(x)
print(bh.get_min(), bh.extract_min(), bh.get_min())  # 5 5 10






Куча Фибоначчи
import math
class FibonacciHeap:
    def init(self):
        self.trees = []
        self.min = None

    def insert(self, value):
        self.trees.append([value])
        if self.min is None or value < self.min[0]:
            self.min = self.trees[-1]

    def get_min(self):
        return self.min[0] if self.min else None

    def extract_min(self):
        if not self.min: return None
        min_val = self.min[0]
        self.trees.remove(self.min)
        self.trees.extend(self.min[1:])
        self.min = min(self.trees, key=lambda x: x[0]) if self.trees else None
        return min_val

fibonacci_heap = FibonacciHeap()

fibonacci_heap.insert(7)
fibonacci_heap.insert(3)
fibonacci_heap.insert(17)
fibonacci_heap.insert(24)

print(fibonacci_heap.get_min())  # 3
print(fibonacci_heap.extract_min())  # 3
print(fibonacci_heap.get_min())  # 7





Хеш-таблица
class HashTable:
    def init(self, size):
        self.size = size
        self.table = [None] * size

    def _hash(self, key):
        return hash(key) % self.size

    def set(self, key, value):
        idx = self._hash(key)
        self.table[idx] = (key, value)

    def get(self, key):
        idx = self._hash(key)
        if self.table[idx] and self.table[idx][0] == key:
            return self.table[idx][1]
        raise KeyError(key)

    def remove(self, key):
        idx = self._hash(key)
        if self.table[idx] and self.table[idx][0] == key:
            self.table[idx] = None
        else:
            raise KeyError(key)

ht = HashTable(10)
ht.set("Alice", "January")
ht.set("Bob", "December")
print(ht.get("Alice"))  # January
ht.remove("Bob")

print(ht.get("Bob"))    # KeyError: Bo
