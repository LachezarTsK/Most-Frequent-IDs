
class Solution {

    private data class Component(val ID: Int, val frequency: Long) {}

    fun mostFrequentIDs(IDs: IntArray, changeInFrequency: IntArray): LongArray {
        val maxID = IDs.max()
        val frequencyIDs = LongArray(maxID + 1)
        val resultMostFrequentIDsPerTimeStamp = LongArray(IDs.size)
        val maxHeap = PriorityQueue<Component>() { x, y -> y.frequency.compareTo(x.frequency) }

        for (i in IDs.indices) {

            frequencyIDs[IDs[i]] += changeInFrequency[i]
            if (frequencyIDs[IDs[i]] > 0) {
                maxHeap.add(Component(IDs[i], frequencyIDs[IDs[i]]))
            }

            while (!maxHeap.isEmpty() && frequencyIDs[maxHeap.peek().ID] != maxHeap.peek().frequency) {
                maxHeap.poll()
            }

            if (!maxHeap.isEmpty()) {
                resultMostFrequentIDsPerTimeStamp[i] = maxHeap.peek().frequency
            }
        }

        return resultMostFrequentIDsPerTimeStamp
    }
}
