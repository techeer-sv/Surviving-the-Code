// 하루에 한 시간 단위로 일을 하거나 일을 쉬어도 된다. 하루에 한 시간 일하면 피로도는
// $A$만큼 쌓이고 일은 $B$만큼 처리할 수 있다.

// 만약에 한 시간을 쉰다면 피로도는 $C$만큼 줄어든다. 단, 피로도가 음수로 내려가면 $0$으로 바뀐다. 당연히 일을 하지 않고 쉬었기 때문에 처리한 일은 없다.

// 피로도를 최대한 $M$을 넘지 않게 일을 하려고 한다. $M$을 넘기면 일하는데 번아웃이 와서 이미 했던 일들도 다 던져버리고 일을 그만두게 된다.

//번아웃이 되지 않도록 일을 할때 하루에 최대 얼마나 일을 할 수 있는지 구해보자. 하루는 24시간이다.

//첫 번째 줄에 네 정수 A, B, C, M이 공백으로 구분되어 주어진다.

// 맨 처음 피로도는 0이다.

function solve(A, B, C, M) {
  let tired = 0;
  let work = 0;
  for (let i = 0; i < 24; i++) {
    if (tired + A <= M) {
      tired += A;
      work += B;
    } else {
      tired -= C;
    }
    if (tired < 0) tired = 0;
  }
  return work;
}

// 테스트 케이스
const testCases = [
  // [A, B, C, M, expected]
  { input: [5, 3, 2, 10], expected: solve(5, 3, 2, 10) },
  { input: [2, 1, 2, 5], expected: solve(2, 1, 2, 5) },
  { input: [10, 5, 1, 9], expected: solve(10, 5, 1, 9) },
  { input: [1, 2, 1, 5], expected: solve(1, 2, 1, 5) },
  { input: [4, 4, 3, 20], expected: solve(4, 4, 3, 20) },
];

testCases.forEach(({ input, expected }, idx) => {
  const result = solve(...input);
  console.log(
    `TestCase #${idx + 1}: input=${input.join(
      ", ",
    )}, result=${result}, expected=${expected}`,
  );
  if (result === expected) {
    console.log("✅ 통과");
  } else {
    console.log("❌ 실패");
  }
  console.log("---");
});

// 아래 코드는 백준 제출용(주석처리 또는 삭제)
// const fs = require("fs");
// const input = fs.readFileSync(0).toString().trim();
// const [A, B, C, M] = input.split(" ").map(Number);
// console.log(solve(A, B, C, M));
