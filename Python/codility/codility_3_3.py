def solution(A):
    suma = []
    sumb = []
    totala = 0
    totalb = 0
    for loc in range(len(A)):
        totala = totala + A[loc]
        totalb = totalb + A[len(A) -loc-1]
        suma.append(totala)
        sumb.append(totalb)
    final = abs(suma[0] - sumb[len(A)-2])
    for num in range(len (A)-1):
        if final> abs(suma[num] - sumb[len(A) - num - 2]):
            final = abs(suma[num] - sumb[len(A) - num - 2])

    return final
