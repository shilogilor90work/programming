
def solution(A):
    v = []
    total = 0
    for x in range(len(A)):
        if A[x] == 0:
            v.append(x  + 1)
    for a in range(len(v)):
        total = total + (len(A) - a - v[a])
    if total > 10**9:
        return -1
    return total
