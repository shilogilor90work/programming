def solution(N, A):
    max1 = 0
    lst = [0] * N
    flag = False
    for num in A:
        if num > N:
            if flag:
                lst = [max1] * N
                flag= False
        else:
            lst[num-1] += 1
            if lst[num-1] > max1:
                max1=lst[num-1]
                flag = True
    return lst
