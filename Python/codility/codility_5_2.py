def solution(S, P, Q):
    A = [0] * (len(S)+1)
    C = [0] * (len(S)+1)
    G = [0] * (len(S)+1)
    acounter = 0
    ccounter = 0
    gcounter = 0
    final = []
    for loc , letter in enumerate(S):
        if letter == 'A':
            acounter +=1
        elif letter == 'C':
            ccounter +=1
        elif letter == 'G':
            gcounter +=1
        A[loc+1] = acounter
        C[loc+1] = ccounter
        G[loc+1] = gcounter
    for start , end in zip(P , Q):
        if A[end+1] - A[start] > 0:
            final.append(1)
        elif C[end+1] - C[start] > 0:
            final.append(2)
        elif G[end+1] - G[start] > 0:
            final.append(3)
        else:
            final.append(4)
    return final
