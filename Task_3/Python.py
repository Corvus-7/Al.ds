### ** Сортировка обменом (пузырьком) (Bubble Sort) **

def bubble_sort(arr):
    # Проходим по всем элементам массива
    n = len(arr)
    for i in range(n-1):
        # Последующие элементы уже будут стоять на своём месте
        for j in range(n-i-1):
            # Сравниваем соседние элементы
            if arr[j] > arr[j+1]:
                # Меняем местами, если порядок неправильный
                arr[j], arr[j+1] = arr[j+1], arr[j]

# Исходный массив
arr = [64, 34, 25, 12, 22, 11, 90]

print("Исходный массив:", arr)
bubble_sort(arr)
print("Отсортированный массив:", arr)




## Сортировка Шелла (Shellsort) 

def shell_sort(arr):
    n = len(arr)
    # Начинаем с большого промежутка и уменьшаем его
    gap = n // 2
    while gap > 0:
        # Проходим по всем элементам с текущим промежутком
        for i in range(gap, n):
            # Сохраняем текущий элемент
            temp = arr[i]
            j = i
            # Сдвигаем элементы, которые больше temp, вправо
            while j >= gap and arr[j - gap] > temp:
                arr[j] = arr[j - gap]
                j -= gap
            # Вставляем temp на правильное место
            arr[j] = temp
        gap //= 2

# Функция для вывода массива
def print_array(arr):
    print(' '.join(map(str, arr)))

# Пример использования
if __name__ == "__main__":
    arr = [12, 34, 54, 2, 3]
    print("Исходный массив:")
    print_array(arr)
    shell_sort(arr)
    print("Отсортированный массив:")
    print_array(arr)






####### Алгоритмы поиска

## Последовательный (линейный) поиск 

# Функция линейного поиска
def linear_search(arr, target):
    # Проходим по всем элементам массива
    for index, element in enumerate(arr):
        # Если нашли искомый элемент
        if element == target:
            return index  # Возвращаем индекс найденного элемента
    return -1  # Возвращаем -1, если элемент не найден

# Основной блок программы
if __name__ == "__main__":
    # Создаем массив
    array = [3, 5, 2, 7, 9, 1, 4]
    target = 7  # Искомое значение
    # Вызываем функцию поиска
    result = linear_search(array, target)
    # Выводим результат
    if result != -1:
        print(f"Элемент найден на позиции: {result}")
    else:
        print("Элемент не найден")




## Поиск по Фибоначчи 

def fibonacci_search(arr, x):
def fib(n):
"Генерирует числа Фибоначчи"
a, b = 0, 1
for in range(n + 1):
      yield a
      a, b = b, a + b

 # Находим минимальное число Фибоначчи, превышающее длину массива
 fib_numbers = list(fib(len(arr) + 1))
 fib_m = next((f for f in reversed(fib_numbers) if f >= len(arr)), None)

 # Начальные маркеры
 fib_m2 = fib_numbers[-3]  # m-2-е число Фибоначчи
 fib_m1 = fib_numbers[-2]  # m-1-е число Фибоначчи
 offset = -1

  # Поиск
  while fib_m > 1:
        i = min(offset + fib_m2, len(arr)-1)
        if arr[i] < x:
            fib_m = fib_m1
            fib_m1 = fib_m2
            fib_m2 = fib_m - fib_m1
            offset = i
        elif arr[i] > x:
            fib_m = fib_m2
            fib_m1 = fib_m1 - fib_m2
            fib_m2 = fib_m - fib_m1
        else:
            return i

    # Проверка последнего возможного места
    if fib_m1 and offset + 1 < len(arr) and arr[offset + 1] == x:
        return offset + 1

    return -1

# Пример использования
arr = [10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100]
x = 85
result = fibonacci_search(arr, x)

if result != -1:
    print(f'Элемент найден на позиции: {result}')
else:
    print('Элемент не найден')


