/////////////////// Алгоритмы сортировки


///// Сортировка вставками (Insertion Sort) 

#include <iostream>
using namespace std;

void insertionSort(int arr[], int n) {
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

void printArray(int arr[], int n) {
    for (int i = 0; i < n; i++) cout << arr[i] << " ";
    cout << endl;
}

int main() {
    int arr[] = {12, 11, 13, 5, 6};
    int n = sizeof(arr) / sizeof(arr[0]);
    printArray(arr, n);
    insertionSort(arr, n);
    printArray(arr, n);
    return 0;
}




///// Быстрая сортировка (Quick Sort)

#include <iostream>
using namespace std;

// Вспомогательная функция для разделения массива
int partition(int arr[], int low, int high) {
    int pivot = arr[high]; // Опорный элемент
    int i = low - 1;       // Индекс для меньших элементов
    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;                 // Смещаемся к следующему месту для малого элемента
            swap(arr[i], arr[j]); // Меняем местами, если текущий элемент меньше опорного
        }
    }
    swap(arr[i + 1], arr[high]); // Помещаем опорный элемент на свое место
    return i + 1;               // Возвращаем новый индекс опорного элемента
}

// Быстрая сортировка
void quickSort(int arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high); // Разделение массива
        quickSort(arr, low, pi - 1);       // Сортировка левой части
        quickSort(arr, pi + 1, high);      // Сортировка правой части
    }
}

// Вывод массива
void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

// Основная функция
int main() {
    int arr[] = {10, 7, 8, 9, 1, 5};
    int n = sizeof(arr) / sizeof(arr[0]);
    cout << "Исходный массив: ";
    printArray(arr, n);
    quickSort(arr, 0, n - 1);
    cout << "\nОтсортированный массив: ";
    printArray(arr, n);
    return 0;
}








/////////////////// Алгоритмы поиска


///// Интерполирующий поиск 

#include <iostream>
using namespace std;

// Функция интерполяционного поиска
int interpolationSearch(int arr[], int lo, int hi, int x) {
if (lo <= hi && x >= arr[lo] && x <= arr[hi]) {
// Вычисляем приблизительную позицию
int pos = lo + ((double)(hi-lo)/(arr[hi]-arr[lo])*(x-arr[lo]));
        
// Если элемент найден
if (arr[pos] == x) return pos;
        
// Если элемент меньше, искать в левой части
if (arr[pos] < x) return interpolationSearch(arr, pos + 1, hi, x);
        
// Иначе искать в правой части
return interpolationSearch(arr, lo, pos - 1, x);
}
return -1; // Элемент не найден
}
// Пример использования
int main() {
int arr[] = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
int n = sizeof(arr) / sizeof(arr[0]);
int x = 18;
int idx = interpolationSearch(arr, 0, n-1, x);
if (idx != -1) {
    cout << "Элемент найден на позиции: " << idx << endl;
} else {
    cout << "Элемент не найден." << endl;
}
    return 0;
}
