def solution(A):
	if len(A)<=3:
	    return 0
	maxendhere = [0] *len(A)
	maxstarthere = [0] *len(A)
	for loc in range(len(A))[1:]:
		maxendhere[loc] = max(maxendhere[loc-1]+A[loc], 0)
		maxstarthere[len(A)-1-loc] = max(maxstarthere[len(A)-loc]+A[len(A)-1-loc], 0)
	final =0
	for loc in range(len(A))[1:-1]:
		final = max(maxendhere[loc-1]+maxstarthere[loc+1], final)
	return final 
