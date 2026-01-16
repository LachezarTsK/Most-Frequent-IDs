
using System;

public class Solution
{
    private record Component(int ID, long frequency){}

    public long[] MostFrequentIDs(int[] IDs, int[] changeInFrequency)
    {
        int maxID = IDs.Max();
        long[] frequencyIDs = new long[maxID + 1];
        long[] resultMostFrequentIDsPerTimeStamp = new long[IDs.Length];
        PriorityQueue<Component, long> maxHeap = new PriorityQueue<Component, long>(Comparer<long>.Create((x, y) => y.CompareTo(x)));

        for (int i = 0; i < IDs.Length; ++i)
        {
            frequencyIDs[IDs[i]] += changeInFrequency[i];
            if (frequencyIDs[IDs[i]] > 0)
            {
                maxHeap.Enqueue(new Component(IDs[i], frequencyIDs[IDs[i]]), frequencyIDs[IDs[i]]);
            }

            while (maxHeap.Count > 0 && frequencyIDs[maxHeap.Peek().ID] != maxHeap.Peek().frequency)
            {
                maxHeap.Dequeue();
            }

            if (maxHeap.Count > 0)
            {
                resultMostFrequentIDsPerTimeStamp[i] = maxHeap.Peek().frequency;
            }
        }

        return resultMostFrequentIDsPerTimeStamp;
    }
}
