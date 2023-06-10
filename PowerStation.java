/* You are given a 0-indexed integer array stations of length n, where stations[i] represents the number of power 
stations in the ith city. Each power station can provide power to every city in a fixed range. In other words, if the 
range is denoted by r, then a power station at city i can provide power to all cities j such that |i - j| <= r and 
0 <= i, j <= n - 1. Note that |x| denotes absolute value. For example, |7 - 5| = 2 and |3 - 10| = 7.
The power of a city is the total number of power stations it is being provided power from. The government has 
sanctioned building k more power stations, each of which can be built in any city, and have the same range as the 
pre-existing ones.
Given the two integers r and k, return the maximum possible minimum power of a city, if the additional power stations 
are built optimally. Note that you can build the k power stations in multiple cities.
* Eg 1 : stations = [1,2,4,5,0]     r = 1    k = 2       Min of Max = 5 
* Eg 2 : stations = [4,4,4,4]       r = 0    k = 3       Min of Max = 4 
*/
import java.util.*;
public class PowerStation
{
      public int MaximizeTheMinimumStation(int stations[], int r, int k)
      {
            int right[] = new int[stations.length];    //* Array Declaration to check Power in each City -> O(N)
            for(int i = 0; i <= r; i++)      // Storing the first city power...
                  right[0] = right[0] + stations[i];
            int index = 1;
            while(index != stations.length)     //! Storing Power in each City -> O(N)
            {
                  if((index > r) && (index + r < stations.length))    // If city power radius is complete...
                        right[index] = right[index - 1] - stations[index - r - 1] + stations[index + r];
                  else if(index + r < stations.length)     // If city power radius is lower from left...
                        right[index] = right[index - 1] + stations[index + r];
                  else            // If city power radius is lower from right...
                        right[index] = right[index - 1] - stations[index - r - 1];
                  index++;
            }
            int Min = Integer.MAX_VALUE;    // Storing the Maximum Value...  //* Variable -> O(1)
            while(k != 0)      //! Installing the Power Stations -> O(k)
            {
                  index = 0;
                  Min = Integer.MAX_VALUE;
                  for(int i = 0; i < right.length; i++)    //! Finding the lowest Power City -> O(N)
                  {
                        if(Min >= right[i])
                        {
                              Min = right[i];    // Getting the Minimum City...
                              index = i;         // Getting the Minimum City Index...
                        }
                  }
                  stations[index]++;      // Incrementing the stations in the lowest power city...
                  for(int i = index; i <= index + r && i < right.length; i++)   // Updating the right radius...
                        right[i]++;
                  for(int i = index - 1; i >= index - r && i >= 0; i--)   // Updating the left radius...
                        right[i]++;
                  k--;     // Decrementing the Power Stations left to be installed...
            }
            Min = Integer.MAX_VALUE;
            for(int i = 0; i < right.length; i++)
                  Min = Math.min(Min, right[i]);    // Getting the Maximum of the Minimum...
            return Min;
      }
      public static void main(String args[])
      {
            Scanner sc = new Scanner(System.in);
            int x, k;
            System.out.print("Enter the number of Cities : ");
            x = sc.nextInt();
            int array[] = new int[x];
            for(int i = 0; i < array.length; i++)
            {
                  System.out.print("Enter Power Stations in "+(i+1)+" th City : ");
                  array[i] = sc.nextInt();
            }
            System.out.print("Enter radius of Power Stations : ");
            x = sc.nextInt();
            System.out.print("Enter the value of K : ");
            k = sc.nextInt();
            PowerStation powerstation = new PowerStation();      // Object creation...
            System.out.print("Maximum of Minimum Powered City : "+powerstation.MaximizeTheMinimumStation
            (array, x, k));        // Function calling...
            sc.close();
      }
}



//! Time Complexity -> O(N x k)
//* Space Complexity -> O(N)