
def solution(N):
    counter = 0
    total = 0
    for num in bin(N)[2:]:
        if num == '1':
            if counter > total:
                total = counter
            counter = 0
        elif num == '0' :
            counter +=1
    return total
