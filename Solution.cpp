
#include <ranges>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {

    struct Component {
        int ID{};
        long long frequency{};
        Component(int ID, long long frequency) :ID{ ID }, frequency{ frequency } {}
    };

    struct ComparatorComponent {
        bool operator()(const Component& first, const Component& second) const {
            return first.frequency < second.frequency;
        }
    };

public:
    vector<long long> mostFrequentIDs(const vector<int>& IDs, const vector<int>& changeInFrequency) const {
        int maxID = *ranges::max_element(IDs);
        vector<long long> frequencyIDs(maxID + 1);
        vector<long long> resultMostFrequentIDsPerTimeStamp(IDs.size());
        priority_queue<Component, vector<Component>, ComparatorComponent> maxHeap;

        for (int i = 0; i < IDs.size(); ++i) {

            frequencyIDs[IDs[i]] += changeInFrequency[i];
            if (frequencyIDs[IDs[i]] > 0) {
                maxHeap.emplace(IDs[i], frequencyIDs[IDs[i]]);
            }

            while (!maxHeap.empty() && frequencyIDs[maxHeap.top().ID] != maxHeap.top().frequency) {
                maxHeap.pop();
            }

            if (!maxHeap.empty()) {
                resultMostFrequentIDsPerTimeStamp[i] = maxHeap.top().frequency;
            }
        }

        return resultMostFrequentIDsPerTimeStamp;
    }
};
