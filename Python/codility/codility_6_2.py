def solution(A): O(n*logn)
    A.sort(reverse = True)
    return max(A[0]*A[1]*A[2] , A[0]*A[len(A)-1]*A[len(A)-2])

def solution(A): #O(n)
    B =  [A[0] , A[1] , A[2]]
    B.sort()
    max1 = B[2]
    max2 = B[1]
    max3 = B[0]
    min1 = B[0]
    min2 = B[1]
    for num in A[3:]:
        if num >= max1:
            max3=max2
            max2=max1
            max1=num
        elif num >= max2:
            mac3=max2
            max2=num
        elif num > max3:
            max3 = num
        elif num < min1:
            min2=min1
            min1=num
        elif num < min2:
            min2=num
    return max(max1*max2*max3 , max1*min1*min2)
