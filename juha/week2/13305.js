// 첫 번째 줄에는 도시의 개수를 나타내는 정수 N(2 ≤ N ≤ 100,000)이 주어진다.
// 다음 줄에는 인접한 두 도시를 연결하는 도로의 길이가 제일 왼쪽 도로부터 N-1개의 자연수로 주어진다.
// 다음 줄에는 주유소의 리터당 가격이 제일 왼쪽 도시부터 순서대로 N개의 자연수로 주어진다.
// 제일 왼쪽 도시부터 제일 오른쪽 도시까지의 거리는 1이상 1,000,000,000 이하의 자연수이다. 리터당 가격은 1 이상 1,000,000,000 이하의 자연수이다.

// 모든 주유소의 리터당 가격은 1원이다.

//const fs = require("fs");
//const input = fs.readFileSync(0).toString().split("\n");

//const N = Number(input[0]);
//const distance = input[1].split(" ").map(Number);
//const price = input[2].split(" ").map(Number);

//let total = 0;
//let minPrice = price[0];

function solution(N, distance, price) {
  let total = 0;
  let minPrice = price[0];
  for (let i = 0; i < N - 1; i++) {
    if (price[i] <= minPrice) {
      minPrice = price[i];
    }
    total += minPrice * distance[i];
  }
  return total;
}

// console.log(solution(N, distance, price));

// ====== 테스트 코드 ======
// 예시 입력: 도시 4개, 거리 [2, 3, 1], 가격 [5, 2, 4, 1]
const testN = 4;
const testDistance = [2, 3, 1];
const testPrice = [5, 2, 4, 1];
console.log("테스트 결과:", solution(testN, testDistance, testPrice)); // 예상 결과: 18

// 추가 테스트: 도시 5개, 거리 [3, 3, 4, 1], 가격 [1, 1, 1, 1, 1]
const testN2 = 5;
const testDistance2 = [3, 3, 4, 1];
const testPrice2 = [1, 1, 1, 1, 1];
console.log("테스트 결과2:", solution(testN2, testDistance2, testPrice2)); // 예상 결과: 11
