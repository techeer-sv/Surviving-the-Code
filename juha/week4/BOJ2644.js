// 부모와 자식 사이 1촌 나랑 할부지는 2촌
// 둘째줄에 촌수 계산 대상 셋째 줄에 부모자식 간의 관계 계수 m 넷째줄 부터는 부모자식관계 x는 y의 부모번호를 나타냄

// const fs = require("fs");
// const input = fs.readFileSync(0).toString().trim().split("\n");

function solution(input) {
  const n = Number(input[0]);
  const [target1, target2] = input[1].split(" ").map(Number);
  const m = Number(input[2]);

  // 무방향 그래프를 위한 인접 리스트
  const graph = Array.from({ length: n + 1 }, () => []);
  for (let i = 3; i < 3 + m; i++) {
    const [parent, child] = input[i].split(" ").map(Number);
    graph[parent].push(child);
    graph[child].push(parent);
  }

  // 스택과 방문 여부 배열
  const stack = [];
  const visited = new Array(n + 1).fill(false);

  // 시작 노드와 촌수(count)를 스택에 추가
  stack.push([target1, 0]);
  visited[target1] = true;

  // 스택을 이용한 DFS 탐색
  while (stack.length > 0) {
    // 스택의 가장 위(마지막) 요소를 꺼냄
    const [currentPerson, count] = stack.pop();

    // 현재 노드가 찾고자 하는 노드인지 확인
    if (currentPerson === target2) {
      return count;
    }

    // 현재 노드와 연결된 모든 이웃 노드(부모 또는 자식)를 확인
    for (const neighbor of graph[currentPerson]) {
      // 아직 방문하지 않았다면 스택에 추가
      if (!visited[neighbor]) {
        visited[neighbor] = true;
        stack.push([neighbor, count + 1]);
      }
    }
  }

  // 스택이 모두 비워졌는데도 목표 노드를 찾지 못한 경우
  return -1;
}

// 테스트 코드
if (require.main === module) {
  // 테스트 케이스 1: 기본 케이스 (직접 연결)
  const testInput1 = [
    "9",
    "7 3",
    "7",
    "1 2",
    "1 3",
    "2 7",
    "2 8",
    "2 9",
    "4 5",
    "4 6",
  ];

  // 테스트 케이스 2: 간접 연결 (3촌)
  const testInput2 = [
    "9",
    "7 5",
    "8",
    "1 2",
    "1 3",
    "2 7",
    "2 8",
    "2 9",
    "3 4",
    "4 5",
    "4 6",
  ];

  // 테스트 케이스 3: 연결되지 않은 경우
  const testInput3 = [
    "9",
    "7 4",
    "6",
    "1 2",
    "1 3",
    "2 7",
    "2 8",
    "2 9",
    "5 6",
  ];

  // 테스트 케이스 4: 같은 사람
  const testInput4 = [
    "9",
    "7 7",
    "7",
    "1 2",
    "1 3",
    "2 7",
    "2 8",
    "2 9",
    "4 5",
    "4 6",
  ];

  console.log("=== BOJ 2644 촌수 계산 테스트 ===");
  console.log("테스트 1 (7-3, 직접 연결):", solution(testInput1)); // 3
  console.log("테스트 2 (7-5, 간접 연결):", solution(testInput2)); // 3
  console.log("테스트 3 (7-4, 연결 안됨):", solution(testInput3)); // -1
  console.log("테스트 4 (7-7, 같은 사람):", solution(testInput4)); // 0
} else {
  console.log(solution(input));
}
