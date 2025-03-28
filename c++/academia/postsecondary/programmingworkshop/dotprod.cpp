// Description: This program calculates the dot product of two 3D vectors.

#include <iostream>
#include <cassert>

using namespace std;

struct vec3 {
  int x;
  int y;
  double z;
  vec3(int i, int j, int k) : x(i), y(j), z(k) {}
};

int dot_product(vec3 &a, vec3 &b) {
  return (a.x * b.x) + (a.y * b.y) + (a.z * b.z);
}

int main() {
    vec3 v1(1, 3, 5);
    vec3 v2(2, 4, 6);
    
    cout << "v1 . v2 = " << dot_product(v1, v2) << endl;

  // do not change this line
  assert((dot_product(v1, v2) == 44) && "The dot product should be 44.");

  return 0;
}