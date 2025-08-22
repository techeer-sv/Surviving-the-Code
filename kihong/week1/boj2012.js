// 등수 매기기 문제 (백준 2012) JS 버전

function solve(n, students) {
  students.sort((a, b) => a - b);
  let result = 0;
  for (let i = 0; i < n; i++) {
    result += Math.abs((i + 1) - students[i]);
  }
  return result;
}

// GitHub Actions에서 node로 직접 실행할 때 테스트 케이스만 남김
const testCases = [
  { n: 5, students: [1, 5, 3, 1, 2], expected: 3 },
  { n: 2, students: [2, 2], expected: 1 }
];

for (const { n, students, expected } of testCases) {
  const result = solve(n, students);
  console.log(`n=${n}, students=${JSON.stringify(students)}, result=${result}, expected=${expected}`);
  if (result === expected) {
    console.log('✅ 통과');
  } else {
    console.log('❌ 실패');
  }
  console.log('---');
}
