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
