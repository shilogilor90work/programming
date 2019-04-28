def get_max(A):
    if not A:
        return -1
    my_dict = {}
    my_max = 0
    for num in A:
        if not num in my_dict:
            my_dict[num] = 1
        else:
            my_dict[num] += 1
        if my_max<my_dict[num]:
            my_max = my_dict[num]
    if my_max<=len(A)/2:
        return [-1 , 0 , 0]
    else :
        for key, value in my_dict.items():
            if value == my_max:
                for loc in range(len(A)):
                    if A[loc] == key:
                        return [key, my_max , len(A)]

def solution(A):
    key , my_max , size = get_max(A)
    if key == -1:
        return 0
    counter = 0
    countera = 0
    counterb = my_max
    for loc in range(size):
        if A[loc] == key:
            countera+=1
            counterb-=1
        if countera > (loc+1)/2 and counterb>(size - loc-1)/2:
            counter+=1
    return counter
