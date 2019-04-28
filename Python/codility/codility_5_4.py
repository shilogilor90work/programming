def solution(A, B, K):
    return B//K - A//K +1 if A%K==0 else B//K - A//K
