def solution(A):
    total =0
    start = [0]*len(A)
    end = [0]*len(A)
    on = 0
    for loc in range(len(A)):
        if A[loc]>=loc:
            start[0] += 1
        else :
            start[loc-A[loc]]+=1
        if A[loc]+loc >= len(A):
            end[len(A)-1] +=1
        else:
            end[loc+A[loc]]+=1
    for loc in range(len(A)):
        total += on * start[loc] + (start[loc] * (start[loc] - 1)) // 2
        on += start[loc] - end[loc]
        if 10**7 < total:
            return -1
    return total
