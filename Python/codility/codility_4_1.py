
def solution(A):
    lst = [None] * len(A)
    for num in A:
        if num>len(A) or num<1:
            return 0
        if lst[num-1] == None:
            lst[num-1]=1
        else:
            return 0
    return 1
