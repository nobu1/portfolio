#include <bits/stdc++.h>
using namespace std;

int main() {
    string N;
    cin >> N;
    reverse(N.begin(), N.end());
    int num = 0;

    for (int i = 0; i < N.size(); i++) {
        num += (N[i] - '0') * pow(2, i);
    }
    cout << num << endl;
    return 0;
}