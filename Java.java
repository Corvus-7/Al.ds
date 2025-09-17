public class Main 
{
    public static void main(String[] args)   
    {
        String str_1 = "AaBbCcDd";   // Исходная строка
        StringBuilder uppercaseStr = new StringBuilder();  // Заглавные
        StringBuilder lowercaseStr = new StringBuilder();  // Строчные
        
        // Получаем заглавные буквы (четные индексы: 0, 2, 4, 6)
        for (int i = 0; i < str_1.length(); i += 2) 
           {uppercaseStr.append(str_1.charAt(i));}
        
        // Получаем строчные буквы (нечетные индексы: 1, 3, 5, 7)
        for (int i = 1; i < str_1.length(); i += 2) 
           {lowercaseStr.append(str_1.charAt(i));}
        
        // Выводим результаты
        System.out.println("Заглавные буквы: " + uppercaseStr.toString());
        System.out.println("Строчные буквы: " + lowercaseStr.toString());
    }
}







import java.util.ArrayList;
import java.util.List;

public class Main 
{
    public static void main(String[] args) 
    {
        List<String> li = new ArrayList<>();   // Создание пустого списка строк
        li.add("a");
        li.add("1");
        li.add("b");
        li.add("2");
        li.add("c");
        li.add("3");
        
        List<String> li_1 = new ArrayList<>();  // Буквы
        List<String> li_2 = new ArrayList<>();  // Числа
        
        // Разделяем список на буквы и числа
        for (int i = 0; i < li.size(); i++)   // Цикл по всем элементам списка
        {
            if (i % 2 == 0)
                {li_1.add(li.get(i));}  // Четные индексы - буквы
            else 
                {li_2.add(li.get(i));}  // Нечетные индексы - числа
        }
        
        li.clear();  // Очищаем исходный список
        
        // Выводим полученные списки
        System.out.println("Буквы: " + li_1);
        System.out.println();   // Пустая строка для разделения
        System.out.println("Числа: " + li_2);
    }
}