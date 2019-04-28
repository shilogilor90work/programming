
def solution(A):
    length = len(A) + 1
    total = 0
    if(length%4 == 0):
        total=length
    if(length%4 == 1):
        total=1
    if(length%4 == 2):
        total=length + 1
    if(length%4 == 3) :
        total=0
    for num in A:
        total = total ^ num
    return total
