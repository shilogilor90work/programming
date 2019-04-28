def solution(A):
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
        return -1
    else :
        for key, value in my_dict.items():
            if value == my_max:
                for loc in range(len(A)):
                    if A[loc] == key:
                        return loc
