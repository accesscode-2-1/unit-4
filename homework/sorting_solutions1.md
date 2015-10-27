##Selection Sort
#Show the steps in sorting this list with Selection Sort: (4, 3, 22, 1, 8, 5) *
4, 3, 22, 1, 8, 5
1, 3, 22, 4, 8 ,5
1, 3, 22, 4, 8 ,5 
1, 3, 4, 22, 8, 5
1, 3, 4, 5, 8, 22
1, 3, 4, 5, 8, 22
1, 3, 4, 5, 8, 22

#Why is selection sort's efficiency independent of the data being sorted? *
Selection sort has no way to short circuit its operation if the list is in order before the algorithm has gone all the way through it. For a list of a given length, selection sort will perform the same number of comparisons regardless of whether the list is in order or hopelessly out of order.

#How many passes/scans will go through a list of 10 elements? *
9

#How many passes (or "scans") will there be through a list being sorted using a selection sort? *
array_size - 1

##Insertion Sort
#Show the steps in sorting this list with Insertion Sort: (4, 6, 7, 4, 2, 3, 9) *
4, 6, 7, 4, 2, 3, 9
4, 4, 6, 7, 2, 3, 9
2, 4, 4, 6, 7, 3, 9
2, 3, 4, 4, 6, 7, 9

#Consider the following lists of partially sorted numbers. The double bars represent the sort marker. How many comparisons and swaps are needed to sort the next number. [1 3 4 8 9 || 5 2] *
3 comparisons, 2 swaps

#If all the elements in an input array is equal for example {1,1,1,1,1,1}, What would be the running time of the Insertion Algorithm? *
O(n2)

#What operation does the Insertion Sort use to move numbers from the unsorted section to the sorted section of the list? *
Swapping

##Merge Sort
In the course of a merge sort on a 64 element list, how many times must the list be split? *
6 times, or log2(64) 

#Consider the following pairs of lists: A. (3, 5, 7, 9) and (2, 4, 6, 8) B. (1, 2, 3, 4) and (5, 6, 7, 8) Which pair is more efficiently merged? *
Pair B can be merged more efficiently because after four comparisons the first list is empty. Since we know that the second list is already in order, we simply insert the elements one by one into the final list without making any more comparisons.

#In general, how many merges will be performed in an n element list (for the sake of simplicity, assume that n is a power of 2)? *
n-1

#An array has indices ranging from x to x+n; the merge sort would be applied from c to x+n, where c
Is the integer part of x/2
