def solution(A):
    mini=1
    A = [x for x in A if x > 0]
    A.sort()
    for num in A:
        if num ==mini:
            mini+=1
        elif num>mini:
            return mini
    return mini
