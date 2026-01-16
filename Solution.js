
// const {PriorityQueue} = require('@datastructures-js/priority-queue');
/*
 PriorityQueue is internally included in the solution file on leetcode.
 When running the code on leetcode it should stay commented out. 
 It is mentioned here just for information about the external library 
 that is applied for this data structure.
 */

/**
 * @param {number[]} IDs
 * @param {number[]} changeInFrequency
 * @return {number[]}
 */
var mostFrequentIDs = function (IDs, changeInFrequency) {
    const maxID = Math.max(...IDs);
    const frequencyIDs = new Array(maxID + 1).fill(0);
    const resultMostFrequentIDsPerTimeStamp = new Array(IDs.length).fill(0);
    const maxHeap = new PriorityQueue((x, y) => y.frequency - x.frequency);

    for (let i = 0; i < IDs.length; ++i) {

        frequencyIDs[IDs[i]] += changeInFrequency[i];
        if (frequencyIDs[IDs[i]] > 0) {
            maxHeap.enqueue(new Component(IDs[i], frequencyIDs[IDs[i]]));
        }

        while (!maxHeap.isEmpty() && frequencyIDs[maxHeap.front().ID] !== maxHeap.front().frequency) {
            maxHeap.dequeue();
        }

        if (!maxHeap.isEmpty()) {
            resultMostFrequentIDsPerTimeStamp[i] = maxHeap.front().frequency;
        }
    }

    return resultMostFrequentIDsPerTimeStamp;
};

class Component {

    /**
     * @param {number} ID
     * @param {number} frequency
     */
    constructor(ID, frequency) {
        this.ID = ID;
        this.frequency = frequency;
    }
}
