import math
def solution(N):
    if N==1:
        return 4
    per = 1
    if int(math.sqrt(N))**2==N:
        return 4*int(math.sqrt(N))
    for num in range(N)[2:int(math.sqrt(N))+1]:
        if N%num==0:
            per = num
    return 2*(per + int(N/per))
