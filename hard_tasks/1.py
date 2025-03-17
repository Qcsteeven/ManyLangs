def rot(matrix, i, j):
    matrix[i][j], matrix[i + 1][j] = matrix[i + 1][j], matrix[i][j]
    matrix[i][j + 1], matrix[i + 1][j] = matrix[i][j + 1], matrix[i + 1][j]
    matrix[i + 1][j], matrix[i + 1][j + 1] = matrix[i + 1][j + 1], matrix[i + 1][j]
    
def swapH(matrix, i, j):
    
matrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
]