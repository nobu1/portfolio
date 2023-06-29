#include <bits/stdc++.h>
using namespace std;

int main() {
    bool Answer = false;
    int N;
    cin >> N;

    int A[N];
    for (int i = 0; i < N; i++) cin >> A[i];

    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            for (int z = 0; z < N; z++) {
                if (x != y && y != z && z != x) {
                    if (A[x] + A[y] + A[z] == 1000) {
                        Answer = true;
                        break;
                    }
                }
            }
            if (Answer) {
                break;
            }
        }
        if (Answer) {
            break;
        }
    }

    if (Answer) {
        cout << "Yes" << endl;
    } else {
        cout << "No" << endl;
    }
}