def crazy_sort(arr):
    zeros = [i for i, x in enumerate(arr) if x == 0]
    ans = [x for x in arr if x != 0]
    comp = lambda x: (
        0 if x == 0 else (1 if x % 2 == 0 else 2),
        -x if x % 2 == 0 else x
    )
    ans.sort(key=comp)
    for index in zeros:
        ans.insert(index, 0)
    return ans

test_cases = [
    [4, 1, 3, 2, 0, 6, 5],
    [0, 0, 0, 1, 2, 3],
    [5, 3, 1, 8, 0, 6],
    [0, 2, 4, 6, 1, 3, 5],
    [1, 3, 5, 7, 2, 4, 6],
    [-3, -2, -1, 0, 1, 2, 3],  
    [4, -1, 3, -2, 0, -6, 5]   
]

for test in test_cases:
    print(f"Исходный массив: {test}")
    sorted_arr = crazy_sort(test)
    print(f"Отсортированный: {sorted_arr}\n")