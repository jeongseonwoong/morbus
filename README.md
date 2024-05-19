# morbus
<b>질병을 파악하고 해결책을 제시해주는 서비스</b>
<p><br></p>

# 설치 방법
```sh
1. java 설치
~$ sudo apt update //시작하기에 앞서 아래의 코드로 패키지를 최신화한다.
~$ java --version //현재 java가 깔려있는지 확인한다.
~$ sudo apt install openjdk-21-jdk openjdk-21-jre -y // java가 깔려있지 않다면 21 버전으로 jdk와 jre를 모두 설치해준다.
~$ vi ~/.bashrc //환경변수 파일 실행
export JAVA_HOME="/usr/lib/jvm/java-21-openjdk-amd64" //맨 아래 줄에 추
~$ source ~/.bashrc // 적용 및 확인

2.h2 DB 설치
https://www.h2database.com 에서 모든 os용 h2 다운로드
~$ chmod 755 h2.sh //권한 주기(windwow는 x)
~$./h2.sh //실행 (window는 h2.bat)
JDBC URL:jdbc:h2:~/test (최초 한번)
~$ls -al
~/test.mv.db 파일 생성 확인
이후부터는 jdbc:h2:tcp://localhost/~/test 이렇게 접속
스프링 부트 2.x를 사용하면 1.4.200 버전을 다운로드 받으면 된다.
스프링 부트 3.x를 사용하면 2.1.214 버전 이상 사용해야 한다.




```

# 의존성
<h4>OS</h4>
windows Mac Linux

<h4>Library</h4>
Spring Boot 3.2.5 (Spring Web thymeleaf) / java 21.0.2 / Node.js

<h4>DB</h4>
h2/ MySql

# 실행 방법
<ol>
  <li>코드를 실행(실행 버튼이 클릭이 안될 MorbusApplication java파일로 이동하여 실행버튼이 클릭되는지 확인)</li>
  <li>localhost:8080/morbus.html로 접속하여 사용</li>
</ol>
<p><br></p>

# 라이선스
MIT License

Copyright (c) 2024 정선웅

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
<p><br></p>

# 멤버
<ul>
  <li>정선웅</li>
  <a href="https://github.com/jeongseonwoong">GitHub 주소</a>
  <br>01027993550
  <li>조진우</li>
  <p> @JJW1223 <br>01087713302 </p>
  <li>양희창</li>
  <p>@YangHeeChang <br>01024733055 </p>
</ul>
