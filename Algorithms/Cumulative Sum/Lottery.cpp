#include <bits/stdc++.h>
using namespace std;

int main() {
    int N, Q;
    int A[100009], L[100009], R[100009], Win[100009], Lose[100009];
    cin >> N;

    for (int i = 1; i <= N; i++) {
        cin >> A[i];
    }

    cin >> Q;

    for (int j = 1; j <= Q; j++) {
        cin >> L[j] >> R[j];
    }

    Win[0] = 0;
    Lose[0] = 0;
    for (int i = 1; i <= N; i++) {
        if (A[i] == 1) {
            Win[i] = Win[i - 1] + 1;
            Lose[i] = Lose[i - 1];
        } else if (A[i] == 0) {
            Win[i] = Win[i - 1];
            Lose[i] = Lose[i - 1] + 1;
        }
    }

    for (int j = 1; j <= Q; j++) {
        if ((Win[R[j]] - Win[L[j] - 1]) > (Lose[R[j]] - Lose[L[j] - 1])) {
            cout << "win" << endl;
        } else if ((Win[R[j]] - Win[L[j] - 1]) < (Lose[R[j]] - Lose[L[j] - 1])) {
            cout << "lose" << endl;
        } else {
            cout << "draw" << endl;
        } 
    }
    return 0;
}