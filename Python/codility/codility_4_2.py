def solution(X, A):
    lst = [None] * X
    for loc , num in enumerate(A, start=0):
        if lst[num-1]==None:
            X-=1
            lst[num-1]=1
        if X==0:
            return loc
    return -1
