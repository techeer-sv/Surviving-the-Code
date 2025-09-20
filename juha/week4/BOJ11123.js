// H 높이 W 너비

/*
[풀이과정]
1. T (테스트 갯수)만큼 반복하며 처리
2. HW크기의 보드를 순회하며 #이 있다면 dfs 함수 호출하여 양 sheep 증가

[시간복잡도]
H * W 크기 보드를 한번씩만 순회하므로  O(H * W)
*/

// const fs = require("fs");
// const input = fs.readFileSync(0).toString().trim().split("\n");

function solution(input) {
  let line = 0;
  const T = Number(input[line++]);
  const results = [];

  for (let t = 0; t < T; t++) {
    const [H, W] = input[line++].split(" ").map(Number);
    const board = Array.from({ length: H }, () => input[line++].split(""));
    const visited = Array.from({ length: H }, () => Array(W).fill(false));
    let sheep = 0;

    const dx = [0, 0, 1, -1];
    const dy = [1, -1, 0, 0];

    const dfs = (y, x) => {
      if (
        y < 0 ||
        y >= H ||
        x < 0 ||
        x >= W ||
        board[y][x] === "." ||
        visited[y][x]
      ) {
        return;
      }
      visited[y][x] = true;

      for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];
        dfs(ny, nx);
      }
    };

    for (let i = 0; i < H; i++) {
      for (let j = 0; j < W; j++) {
        if (board[i][j] === "#" && !visited[i][j]) {
          sheep++;
          dfs(i, j);
        }
      }
    }
    results.push(sheep);
  }
  return results;
}

// 테스트 코드
if (require.main === module) {
  const testInput = [
    "2",
    "4 4",
    "#.#.",
    ".#.#",
    "#.##",
    ".#.#",
    "3 5",
    "###.#",
    "..#..",
    "#.###",
  ];
  const result = solution(testInput);
  console.log("테스트 결과:", result); // [6, 3]이 나와야 함
}
