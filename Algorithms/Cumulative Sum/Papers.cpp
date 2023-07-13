#include <bits/stdc++.h>
using namespace std;

int N;
int A[100009], B[100009], C[100009], D[100009];
int Z[1509][1509];

int main() {
    cin >> N;
    
    for (int i = 1; i <= N; i++) {
        cin >> A[i] >> B[i] >> C[i] >> D[i];
    }

    for (int i = 0; i <= 1500; i++) {
        for (int j = 0; j <= 1500; j++) {
            Z[i][j] = 0;
        }
    }

    for (int i = 1; i <= N; i++) {
        Z[A[i]][B[i]] += 1;
        Z[A[i]][D[i]] -= 1;
        Z[C[i]][B[i]] -= 1;
        Z[C[i]][D[i]] += 1;
    }

    for (int i = 0; i <= 1500; i++) {
        for (int j = 1; j <= 1500; j++) {
            Z[i][j] = Z[i][j -1] + Z[i][j];
        }
    } 

    for (int i = 1; i <= 1500; i++) {
        for (int j = 0; j <= 1500; j++) {
            Z[i][j] = Z[i-1][j] + Z[i][j];
        }
    }

    int count = 0;
    for (int i = 0; i <= 1500; i++) {
        for (int j = 0; j <= 1500; j++) {
            if (Z[i][j] >= 1) {
                count += 1;
            }
        }
    }
    cout << count << endl;
    return 0;
}