import math
def solution(N):
    if N==1:
        return 1
    counter =0
    for num in range(N)[1:int(math.sqrt(N))+1]:
        if N%num==0:
            counter+=2
    if int(math.sqrt(N))**2==N:
        counter-=1
    return counter
