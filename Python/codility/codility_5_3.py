def solution(A):
    mini = (A[0]+ A[1])/2
    val = 0
    for loc in range(len(A)-2):
        if (A[loc]+ A[loc+1])/2 <mini:
           mini = (A[loc]+ A[loc+1])/2
           val = loc
        if (A[loc]+ A[loc+1] + A[loc+2])/3 < mini:
            mini = (A[loc]+ A[loc+1] + A[loc+2])/3
            val = loc
    if  (A[len(A)-2] + A[len(A)-1])/2< mini:
        val = len(A) - 2
    return val
