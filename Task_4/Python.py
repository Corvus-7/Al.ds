# Входные данные
projects = [
    {"resource": 10, "profit": 50},
    {"resource": 15, "profit": 80},
    {"resource": 20, "profit": 90},
    {"resource": 8, "profit": 40}
]
total_resource = 40

def greedy_algorithm(projects, total_resource):
    # Расчёт коэффициентов эффективности (прибыль / ресурс)
    for project in projects:
        project["efficiency"] = project["profit"] / project["resource"]
    
    # Сортируем проекты по коэффициенту эффективности в порядке убывания
    sorted_projects = sorted(projects, key=lambda x: x["efficiency"], reverse=True)
    
    selected_projects = []
    current_resource = 0
    
    # Выбор проектов, пока хватает ресурсов
    for project in sorted_projects:
        if current_resource + project["resource"] <= total_resource:
            selected_projects.append(project)
            current_resource += project["resource"]
            
    return selected_projects

# Запускаем алгоритм
selected_projects = greedy_algorithm(projects, total_resource)

# Подсчитываем общую прибыль
total_profit = sum([project["profit"] for project in selected_projects])

print("Выбранные проекты:")
for idx, proj in enumerate(selected_projects):
    print(f"{idx+1}. Ресурс={proj['resource']}, Прибыль={proj['profit']}")

print("\nОбщая прибыль:", total_profit)




# Выходные данные
Выбранные проекты:
1. Ресурс=15, Прибыль=80
2. Ресурс=10, Прибыль=50
3. Ресурс=8, Прибыль=40

Общая прибыль: 170