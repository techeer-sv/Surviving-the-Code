/*
풀이과정
1) 시작점 (0,0)에서 출발하여 스택에 넣는다.
2) 스택이 빌 때까지 다음을 반복한다:
    - 스택에서 좌표(y, x)를 꺼낸다.
    - 해당 칸의 값(jump)을 확인한다.
    - jump가 -1이면 도착 성공("HaruHaru" 반환)
    - jump가 0이면 아무것도 하지 않고 다음 반복
    - jump가 1 이상이면:
        a) 오른쪽(x + jump)이 범위 내고, 방문하지 않았으면 스택에 넣고 방문 처리
        b) 아래쪽(y + jump)이 범위 내고, 방문하지 않았으면 스택에 넣고 방문 처리
3) 스택이 모두 비었는데도 도착하지 못하면 실패("Hing" 반환)

시간복잡도: O(N^2) (모든 칸을 한 번씩 방문)
*/

function solve(board) {
  const N = board.length;
  const stack = [[0, 0]];
  const visited = Array.from({ length: N }, () => Array(N).fill(false));
  visited[0][0] = true;

  while (stack.length > 0) {
    const [y, x] = stack.pop(); //가볼 길을 스택에서 꺼내기

    const jump = board[y][x];

    if (jump === -1) {
      return "HaruHaru";
    }
    if (jump === 0) continue;

    // 오른쪽 점프
    const nx = x + jump;
    if (nx < N && !visited[y][nx]) {
      stack.push([y, nx]); // 스택에 넣기
      visited[y][nx] = true; //방문 처리
    }
    // 아래쪽 점프
    const ny = y + jump;
    if (ny < N && !visited[ny][x]) {
      stack.push([ny, x]);
      visited[ny][x] = true;
    }
  }
  return "Hing";
}

// 테스트 케이스
const testCases = [
  {
    board: [
      [2, 3, 3, 1],
      [1, 2, 1, 3],
      [1, 2, 3, 1],
      [3, 1, 1, -1],
    ],
    expected: "HaruHaru",
  },
  {
    board: [
      [2, 3, 3, 1],
      [1, 2, 1, 3],
      [1, 2, 3, 1],
      [3, 1, 1, 0],
    ],
    expected: "Hing",
  },
];

for (const { board, expected } of testCases) {
  const result = solve(board);
  console.log(
    `board=\n${board
      .map((row) => row.join(" "))
      .join("\n")}\nresult=${result}, expected=${expected}`,
  );
  if (result === expected) {
    console.log("✅ 통과");
  } else {
    console.log("❌ 실패");
  }
  console.log("---");
}
