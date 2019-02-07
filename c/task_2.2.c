#include <stdio.h>
#include <stdlib.h>
 char * get_only_upper_case(char * arr)
{
    char *final = malloc(sizeof(arr)/sizeof(arr[0])+1) ; //borrow memory for the new array of char
    int  j = 0; // location of new array
    int i; // counter for loction of given array
    for (i=0; arr[i]!='\0';++i) //loop through array
    {
        if (arr[i]<='Z' && arr[i]>='A') //if upper
        {
            final[j] = arr[i]; //add upper
            j++; //move to next memory
        }
    }
    final[j] = '\0'; // add since this is the last memory
    return &(final[0]); //return array
}
int main() {
   char * s = "HELLOmy_MANname_HOWis_AREjohn_YOU__";
//   char * s = "jhvgHHGGj\\0njUHHhvi";
   char * temp = get_only_upper_case(s);
   int i;
    for (i=0 ; temp[i]!='\0';++i)
    {
        printf("%c",temp[i]); //print array
    }
    free(temp);
    return 0;
}
