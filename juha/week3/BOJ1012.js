/*
[풀이과정]
T (테스트 갯수)가 2개 이상일 경우? -> 변수(line) 추가
현재 위치에 1인 배추가 있으면 지렁이 수 1 증가
이 배추와 연결된(상하좌우) 배추들을 방문처리함 (0)

[시간 복잡도]
O(T * M * N)
- O(T): 가장 바깥쪽 for문 T번 실행
- O(M * N): for문이 M*N 크기의 배추밭 전체를 순회함
- 한번 방문한 칸은 다시 방문하지 않으므로 M * N에 비례
*/

function solution(input) {
  let line = 0;
  const T = Number(input[line++]);
  const results = [];
  for (let t = 0; t < T; t++) {
    const [M, N, K] = input[line++].split(" ").map(Number);
    const field = Array.from({ length: N }, () => Array(M).fill(0));
    let count = 0; //지렁이 수

    // 배추밭에 배추 위치 표시
    for (let i = 0; i < K; i++) {
      const [x, y] = input[line++].split(" ").map(Number);
      field[y][x] = 1;
    }

    //해당 위치의 상하좌우 배추
    const dx = [0, 0, 1, -1];
    const dy = [1, -1, 0, 0];

    const dfs = (y, x) => {
      field[y][x] = 0; //현재 위치 방문처리

      //상하좌우 배추 탐색
      for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];
        if (ny >= 0 && ny < N && nx >= 0 && nx < M && field[ny][nx] === 1) {
          dfs(ny, nx);
        }
      }
    };

    // 아직 방문하지 않은 배추라면
    for (let y = 0; y < N; y++) {
      for (let x = 0; x < M; x++) {
        if (field[y][x] == 1) {
          count++;
          dfs(y, x);
        }
      }
    }
    results.push(count);
  }
  return results;
}

// 테스트 코드
if (require.main === module) {
  const testInput = [
    "2",
    "10 8 17",
    "0 0",
    "1 0",
    "1 1",
    "4 2",
    "4 3",
    "4 5",
    "2 4",
    "3 4",
    "7 4",
    "8 4",
    "9 4",
    "7 5",
    "8 5",
    "9 5",
    "7 6",
    "8 6",
    "9 6",
    "10 10 1",
    "5 5",
  ];
  const result = solution(testInput);
  console.log("테스트 결과:", result); // [5, 1]이 나와야 함
}
