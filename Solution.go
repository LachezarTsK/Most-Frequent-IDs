
package main

import (
    "container/heap"
    "slices"
)

func mostFrequentIDs(IDs []int, changeInFrequency []int) []int64 {
    maxID := slices.Max(IDs)
    frequencyIDs := make([]int64, maxID + 1)
    resultMostFrequentIDsPerTimeStamp := make([]int64, len(IDs))
    maxHeap := PriorityQueue{}

    for i := range IDs {

        frequencyIDs[IDs[i]] += int64(changeInFrequency[i])
        if frequencyIDs[IDs[i]] > 0 {
            heap.Push(&maxHeap, NewComponent(IDs[i], frequencyIDs[IDs[i]]))
        }

        for !maxHeap.IsEmpty() && frequencyIDs[maxHeap.Peek().(Component).ID] != maxHeap.Peek().(Component).frequency {
            heap.Pop(&maxHeap)
        }

        if !maxHeap.IsEmpty() {
            resultMostFrequentIDsPerTimeStamp[i] = maxHeap.Peek().(Component).frequency
        }
    }

    return resultMostFrequentIDsPerTimeStamp
}

type Component struct {
    ID        int
    frequency int64
}

func NewComponent(ID int, frequency int64) Component {
    component := Component{
        ID:        ID,
        frequency: frequency,
    }
    return component
}

type PriorityQueue []Component

func (pq PriorityQueue) Len() int {
    return len(pq)
}

func (pq PriorityQueue) Less(first int, second int) bool {
    return int(pq[first].frequency) > int(pq[second].frequency)
}

func (pq PriorityQueue) Swap(first int, second int) {
    pq[first], pq[second] = pq[second], pq[first]
}

func (pq *PriorityQueue) Push(object any) {
    *pq = append(*pq, object.(Component))
}

func (pq *PriorityQueue) Pop() any {
    component := (*pq)[pq.Len() - 1]
    *pq = (*pq)[0 : pq.Len() - 1]
    return component
}

func (pq *PriorityQueue) IsEmpty() bool {
    return pq.Len() == 0
}

func (pq *PriorityQueue) Peek() any {
    return (*pq)[0]
}
