def solution(A):
    total =0
    for num in A:
        total = total ^ num
    return total
