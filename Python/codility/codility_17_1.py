def solution(A):
    maxarr = [A[0]] * (len(A) + 6)
    for index in range(1, len(A)):
        maxarr[index + 6] = max(maxarr[index : index + 6]) + A[index]
    return maxarr[-1]
