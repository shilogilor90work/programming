
def solution(A, K):
    if len(A)==0:
        return A
    tmp = K % len(A)
    return A[-tmp:] + A[:-tmp]
