def crazy_sort(arr):
    evens = [(i, x) for i, x in enumerate(arr) if x != 0 and x % 2 == 0]
    odds = [(i, x) for i, x in enumerate(arr) if x % 2 != 0]
    
    sorted_evens = sorted(evens, key=lambda x: -x[1])
    sorted_odds = sorted(odds, key=lambda x: x[1])
    
    ans = arr.copy()
    
    for i in range(len(evens)):
        ans[evens[i][0]] = sorted_evens[i][1]
    
    for i in range(len(odds)):
        ans[odds[i][0]] = sorted_odds[i][1]
    
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
    print(f"Исходный: {test}")
    print(f"Результат: {crazy_sort(test)}\n")
