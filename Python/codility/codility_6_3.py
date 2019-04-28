def solution(A):
    A.sort()
    for loc in range(len(A)-2):
        if A[loc] + A[loc+1] >A[loc+2]:
            return 1
    return 0
