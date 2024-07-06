# Java를 이용해 멀티쓰레딩 프로그래밍을 연습하기 위한 프로젝트

본 프로젝트는 Java를 이용해 멀티쓰레딩 프로그래밍을 연습하기 위해 만든 프로젝트입니다.<br>
쓰레드에 대한 지식을 알고 있는 것과, 이를 활용해 프로그래밍하는 것은 전혀 다른 문제라고 느껴져 만들게 되었습니다.<br>
본 프로젝트에 있는 문제를 풀어봄으로써 Java 쓰레드에 대해 몸으로 이해할 수 있었으면 좋겠습니다.

# 연습하는 방법

`/src/main/java` 경로 아래에 문제별로 디렉터리가 나뉘어져 있습니다.<br>
각 문제에 대한 요구 사항은 md 파일에 작성해두었습니다.

문제 요구 사항을 읽고 `practice/Main.java`에 코드를 작성 한 후, `/src/test/java/` 경로에 해당하는 문제의 테스트 코드를 실행하면 결과를 확인할 수 있습니다.<br>
(`/src/main/java/q1/practice/Main.java` <--> `/src/main/java/Practice1Test.java`)

`answer/Main.java`에는 임의로 작성한 답안 코드가 있습니다. (아무것도 없는 건 차차 작성하겠습니다!)<br>
문제를 풀다가 막히거나, 이 사람은 어떻게 풀었는지 궁금하면 한번씩 보시라고 참고용으로 만들어 두었습니다.<br>
만약 문제를 풀다가 정답 코드를 보고 싶은 충동이 들어도, 적어도 세 번은 인내하고 스스로의 힘으로 해결하시길 추천드립니다.<br>
정답을 보고 이해하는 것과 스스로 고민해서 해결하는 것의 차이는 어마어마합니다!

# 참고하면 좋은 글

Java Thread에 대해 정리한 글입니다.<br>

- [쓰레드를 구현하고 실행하는 방법, 쓰레드 우선순위, 쓰레드 그룹, 데몬 쓰레드](https://kdkdhoho.github.io/thread-in-jvm/)
- [쓰레드 상태, 상태 관련 메서드 (sleep, interrupt, yield, join)](https://kdkdhoho.github.io/status-and-control-thread-in-java/)
- [쓰레드 동기화하기 (synchronized, wait()과 notify(), Lock과 Condition)](https://kdkdhoho.github.io/synchronize-threads/)

---

본 프로젝트를 만든 사람은 취준생이며 Java를 통달하진 못했습니다.<br>
따라서 Answer에 적어둔 답이 틀렸거나, 혹은 더 나은 방법이 있을 수 있습니다.<br>
만약 관련하여 수정해야 할 사항이 생긴다면 Issue나 PR, 또는 hkim4410@naver.com 으로 연락주시면 감사하겠습니다.
