#include <bits/stdc++.h>
using namespace std;

int main() {
    int T, N;
    cin >> T >> N;

    int L[600009], R[600009], Sum[600009], Ans[600009];

    for (int i = 0; i < N; i++) {
        cin >> L[i] >> R[i];
    }

    for (int i = 0; i < N; i++) {
        Sum[L[i]] += 1;
        Sum[R[i]] -= 1;
    }

    Ans[0] = Sum[0];
    for (int j = 1; j <= T; j++) {
        Ans[j] = Ans[j-1] + Sum[j];
    }
    for (int j = 0; j < T; j++) {
        cout << Ans[j] << endl;
    }
    return 0;
}