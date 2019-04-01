// JS  find the missing number in array. and shows how long run
// need to put array for example solution ([1,2,3,4,6,7,8,9])
function solution(A) {
	console.time('profile');
    len = A.length;
	mode = len%4
	if(mode == 3) sum=len +1
	else if(mode == 0) sum=1
	else if(mode == 1) sum=len + 2
	else if(mode == 2) sum=0
    for (i=0;i<len;i++)
    {
        sum = sum ^ A[i]
    }
console.log(sum)
var time = console.timeEnd('profile');    
}
