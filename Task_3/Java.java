/////////////////// Алгоритмы сортировки


///// Сортировка выбором (Selection Sort)

import java.util.Arrays;
	
public class SelectionSortExample {
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            }
            // Меняем местами минимальный элемент с текущим
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        
        selectionSort(arr); // вызываем сортировку
        
        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
    }
}





///// Сортировка слиянием (Merge Sort) 

public class MergeSort {
    // Главный метод сортировки
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return; // Базовый случай: массив длиной 0 или 1 уже отсортирован
        int mid = arr.length / 2; // Серединный индекс массива
        int[] left = new int[mid]; // Левая половина
        int[] right = new int[arr.length - mid]; // Правая половина
        
        // Заполняем левую половину
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        
        // Заполняем правую половину
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }
        
        // Рекурсивно сортируем обе половины
        mergeSort(left);
        mergeSort(right);
        
        // Объединяем отсортированные половинки обратно в исходный массив
        merge(arr, left, right);
    }

    // Метод объединения двух отсортированных частей
    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0; // Индексы левой, правой частей и итогового массива соответственно
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        
        // Добавляем оставшиеся элементы из левой части
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        
        // Добавляем оставшиеся элементы из правой части
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Метод для вывода массива
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Точка входа в программу
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        System.out.print("Исходный массив: ");
        printArray(array);
        
        mergeSort(array);
        
        System.out.print("Отсортированный массив: ");
        printArray(array);
    }
}




///// Пирамидальная сортировка

public class HeapSort {
    // Восстанавливает кучу сверху вниз (heapify)
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;              // Индекс максимального элемента
        int left = 2 * i + 1;          
        int right = 2 * i + 2;       
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // Если максимальный элемент изменился
        if (largest != i) {
            int temp = arr[i];        // Меняем местами
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest); // Рекурсивно восстанавливаем кучу снизу вверх
        }
    }

    // Основной метод сортировки кучей
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Строим max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Извлекаем элементы из кучи по одному
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];        // Меняем максимум с последним элементом
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);       // Восстанавливаем кучу заново
        }
    }

    // Демонстрационный метод
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Исходный массив: " + java.util.Arrays.toString(arr));
        heapSort(arr);
        System.out.println("Отсортированный массив: " + java.util.Arrays.toString(arr));
    }
}







/////////////////// Алгоритмы поиска


///// Бинарный (двоичный, дихотомический) поиск 

public class BinarySearch {
    // Бинарный поиск в отсортированном массиве
    public static int search(int[] arr, int target) {
        int left = 0;                      // Левая граница поиска
        int right = arr.length - 1;        // Правая граница поиска
        while (left <= right) {
            int mid = left + (right - left) / 2;  // Срединный элемент
            if (arr[mid] == target) return mid;   // Нашли элемент
            if (arr[mid] > target) right = mid - 1; // Идем влево
            else left = mid + 1;                   // Идем вправо
        }
        return -1;                               // Элемент не найден
    }

    // Пример использования
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 7;
        int result = search(arr, target);
        if (result != -1) {
            System.out.println("Элемент найден на позиции: " + result);
        } else {
            System.out.println("Элемент не найден");
        }
    }
}
