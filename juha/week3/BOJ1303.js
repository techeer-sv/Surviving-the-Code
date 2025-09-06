/*
[풀이과정]
1. 전쟁터의 크기와 병사 색깔 정보를 board 배열에 저장
2. board의 모든 칸을 순회하며 방문하지않은 병사(1)이 있다면 dfs 함수를 호출하여 병사 수(count) 색깔(color)정보를 반환
   -> 방문처리 (0)함
3. 흰색 팀과 파란 팀의 총 위력(count^2)을 출력함

[시간 복잡도 계산]
O(M * N)
- 이중for 반복문: M*N번의 연산 필요
- dfs 함수: 한 칸단 한번만 방문 (탐색 횟수가 M*N을 넘지 않음)
*/

// const fs = require("fs");
// const input = fs.readFileSync(0).toString().trim().split("\n");

// // N 가로, M 세로

// const [N, M] = input[0].split(" ").map(Number);
// const board = input.slice(1).map((row) => row.split(""));

// 기존 solution 함수는 board를 인자로 받도록 수정
function solve(board) {
  const M = board.length;
  const N = board[0].length;
  let whiteTeam = 0;
  let blueTeam = 0;

  const dx = [1, -1, 0, 0];
  const dy = [0, 0, 1, -1];

  const dfs = (y, x, color) => {
    let count = 1;
    board[y][x] = 0;

    // 상하좌우 탐색
    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (ny >= 0 && ny < M && nx >= 0 && nx < N && board[ny][nx] === color) {
        count += dfs(ny, nx, color);
      } // 다음 위치인 ny, nx가 전쟁터 안에 있고 색깔이 같다면 dfs 함수를 다시 호출해 그 값을 count에 더함
    }
    return count;
  };
  // 팀별 위력 계산
  for (let y = 0; y < M; y++) {
    for (let x = 0; x < N; x++) {
      if (board[y][x] !== 0) {
        //방문하지 않은 병사가 있다면
        const color = board[y][x];
        const count = dfs(y, x, color);

        if (color === "W") {
          whiteTeam += count ** 2;
        } else {
          blueTeam += count ** 2;
        }
      }
    }
  }
  return [whiteTeam, blueTeam];
}

// 테스트 케이스
const testCases = [
  {
    board: [
      ["W", "B", "W", "W"],
      ["W", "W", "B", "B"],
      ["B", "B", "W", "W"],
      ["W", "W", "B", "B"],
    ],
    expected: [21, 13],
  },
  {
    board: [
      ["W", "W", "W"],
      ["B", "B", "B"],
      ["W", "W", "W"],
    ],
    expected: [18, 9],
  },
  {
    board: [
      ["W", "W"],
      ["W", "W"],
    ],
    expected: [16, 0],
  },
  {
    board: [
      ["B", "B"],
      ["B", "B"],
    ],
    expected: [0, 16],
  },
];

for (const { board, expected } of testCases) {
  // deep copy board (dfs에서 값이 바뀌므로)
  const input = board.map((row) => row.slice());
  const result = solve(input);
  console.log(
    `board=\n${board
      .map((row) => row.join(" "))
      .join("\n")}\nresult=${result}, expected=${expected}`,
  );
  if (result[0] === expected[0] && result[1] === expected[1]) {
    console.log("✅ 통과");
  } else {
    console.log("❌ 실패");
  }
  console.log("---");
}
