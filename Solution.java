
import java.util.PriorityQueue;

public class Solution {

    private record Component(int ID, long frequency) {}

    public long[] mostFrequentIDs(int[] IDs, int[] changeInFrequency) {
        int maxID = 0;
        for (int ID : IDs) {
            maxID = Math.max(maxID, ID);
        }

        long[] frequencyIDs = new long[maxID + 1];
        long[] resultMostFrequentIDsPerTimeStamp = new long[IDs.length];
        PriorityQueue<Component> maxHeap = new PriorityQueue<>((x, y) -> Long.compare(y.frequency, x.frequency));

        for (int i = 0; i < IDs.length; ++i) {

            frequencyIDs[IDs[i]] += changeInFrequency[i];
            if (frequencyIDs[IDs[i]] > 0) {
                maxHeap.add(new Component(IDs[i], frequencyIDs[IDs[i]]));
            }

            while (!maxHeap.isEmpty() && frequencyIDs[maxHeap.peek().ID] != maxHeap.peek().frequency) {
                maxHeap.poll();
            }

            if (!maxHeap.isEmpty()) {
                resultMostFrequentIDsPerTimeStamp[i] = maxHeap.peek().frequency;
            }
        }

        return resultMostFrequentIDsPerTimeStamp;
    }
}
