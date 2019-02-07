#include <stdio.h>
#include <math.h>

struct Tuple {
    double average;
    double accuracy;
};

struct Tuple calculate_average_and_accuracy(int * arr, int size)
{
    int i; //counter
    int sum =0; //sum of array
    double average; // average of array
    double  accuracy_sum =0.0; // accuracy_sum of array
    double  accuracy ; //accuracy of array
    for(i = 0; i < size; i++) //loop through array
    {
        sum += arr[i]; // calculate sum
    }
    average = (double)sum/size; //calculate average
     for(i = 0; i < size; i++) // loop through array
    {
        accuracy_sum += (arr[i]-average) * (arr[i]-average)/size; // calculate accuracy
    }
    accuracy = sqrt(accuracy_sum);// calculate accuracy
    struct Tuple temp = { average, accuracy };// calculate accuracy
    return temp; //return struct
}


int main() {
    int myArray[5] = { 10, 20,30,40,50 };
    struct Tuple temp =calculate_average_and_accuracy(myArray , sizeof(myArray)/sizeof(myArray[0]));
    printf("average: %f\n",temp.average);
    printf("accuracy: %f\n",temp.accuracy);
    return 0;
}
